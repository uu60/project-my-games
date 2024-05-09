package com.octopus.mygamesbackend.handler.http.game;

import com.octopus.mygamesbackend.business.game.Game;
import com.octopus.mygamesbackend.business.RoomService;
import com.octopus.mygamesbackend.utils.http.Resp;
import com.octopus.mygamesbackend.business.holder.DuoRoomHolder;
import com.octopus.mygamesbackend.business.holder.GameHolder;
import com.octopus.mygamesbackend.utils.properties.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/game/{game}")
public class RoomController {

    @Autowired
    private RoomService service;

    @GetMapping("/create_room")
    public Resp createRoom(String room, HttpSession session, @PathVariable("game") String game) {
        DuoRoomHolder holder = DuoRoomHolder.get(game, room);
        boolean repeated = holder.existsRoom();
        if (repeated) {
            return Resp.error(MyConstants.HTTP_ROOM_ERROR, "房间名重复了，换一个试试吧");
        } else if (room == null || "".equals(room)) {
            return Resp.error(MyConstants.HTTP_ROOM_ERROR, "房间名不能为空！");
        }
        session.setAttribute(MyConstants.SESSION_GAME_KEY, game);
        session.setAttribute(MyConstants.SESSION_ROOM_KEY, room);
        holder.createRoom((String) session.getAttribute(MyConstants.SESSION_USERNAME_KEY));
        return Resp.ok().put("room", room);
    }

    @GetMapping("/wait_player")
    public DeferredResult<Resp> waitPlayer(HttpSession session, @PathVariable("game") String game) {
        String room = (String) session.getAttribute(MyConstants.SESSION_ROOM_KEY);
        DuoRoomHolder holder = DuoRoomHolder.get(game, room);
        long timeOut = 2 * 60 * 1000L;
        DeferredResult<Resp> result = new DeferredResult<>(timeOut, () -> {
            holder.remove();
            return Resp.error(MyConstants.HTTP_ROOM_WAIT_TIMEOUT);
        });
        service.waitPlayer(game, room, (String) session.getAttribute(MyConstants.SESSION_USERNAME_KEY), result,
                timeOut);
        return result;
    }

    @GetMapping("/enter_room")
    public Resp enterRoom(String room, HttpSession session, @PathVariable("game") String game) {
        DuoRoomHolder holder = DuoRoomHolder.get(game, room);
        if (!holder.existsRoom() || !holder.isRoomAvailable()) {
            return Resp.error(MyConstants.HTTP_ROOM_ERROR, "房间不存在。");
        }
        boolean success = holder.addPlayer((String) session.getAttribute(MyConstants.SESSION_USERNAME_KEY),
                Game.targetPlayerNumMap.get(game));
        if (!success) {
            return Resp.error(MyConstants.HTTP_ROOM_ERROR, "无法加入房间。");
        }
        session.setAttribute(MyConstants.SESSION_GAME_KEY, game);
        session.setAttribute(MyConstants.SESSION_ROOM_KEY, room);
        String opponent = service.getOpponentsByRoom(game, room,
                (String) session.getAttribute(MyConstants.SESSION_USERNAME_KEY)).get(0);
        return Resp.ok().put("opponent", opponent);
    }

    @GetMapping("/stop_waiting")
    public Resp stopWaiting(HttpSession session, @PathVariable("game") String game) {
        String room = (String) session.getAttribute(MyConstants.SESSION_ROOM_KEY);
        DuoRoomHolder duoRoomHolder = DuoRoomHolder.get(game, room);
        GameHolder gameHolder = GameHolder.get(game, room);
        // 如果还在等待，直接打断就可以了
        if (duoRoomHolder.getWaitingThread() != null) {
            duoRoomHolder.getWaitingThread().interrupt();
        } else {
            // 获取到锁一定在Game创建完成之后，一定没有建立websocket
            // 移除游戏使玩家无法加入，并在websocket判断
            duoRoomHolder.lock();
            try {
                gameHolder.remove();
            } finally {
                duoRoomHolder.unlock();
            }
        }
        return Resp.ok();
    }
}
