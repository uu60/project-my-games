package com.octopus.mygamesbackend.handler.http.game;

import com.octopus.mygamesbackend.business.pojo.game.Game;
import com.octopus.mygamesbackend.business.RoomService;
import com.octopus.mygamesbackend.utils.http.Resp;
import com.octopus.mygamesbackend.business.manager.DuoRoomManager;
import com.octopus.mygamesbackend.business.manager.GameManager;
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
        DuoRoomManager manager = DuoRoomManager.manager(game, room);
        boolean repeated = manager.existsRoom();
        if (repeated) {
            return Resp.error(MyConstants.HTTP_ROOM_ERROR, "房间名重复了，换一个试试吧");
        } else if (room == null || "".equals(room)) {
            return Resp.error(MyConstants.HTTP_ROOM_ERROR, "房间名不能为空！");
        }
        session.setAttribute(MyConstants.SESSION_GAME_KEY, game);
        session.setAttribute(MyConstants.SESSION_ROOM_KEY, room);
        manager.createRoom((String) session.getAttribute(MyConstants.SESSION_USERNAME_KEY));
        return Resp.ok().put("room", room);
    }

    @GetMapping("/wait_player")
    public DeferredResult<Resp> waitPlayer(HttpSession session, @PathVariable("game") String game) {
        String room = (String) session.getAttribute(MyConstants.SESSION_ROOM_KEY);
        DuoRoomManager manager = DuoRoomManager.manager(game, room);
        long timeOut = 2 * 60 * 1000L;
        DeferredResult<Resp> result = new DeferredResult<>(timeOut, () -> {
            manager.remove();
            return Resp.error(MyConstants.HTTP_ROOM_WAIT_TIMEOUT);
        });
        service.waitPlayer(game, room, (String) session.getAttribute(MyConstants.SESSION_USERNAME_KEY), result,
                timeOut);
        return result;
    }

    @GetMapping("/enter_room")
    public Resp enterRoom(String room, HttpSession session, @PathVariable("game") String game) {
        DuoRoomManager manager = DuoRoomManager.manager(game, room);
        if (!manager.existsRoom() || !manager.isRoomAvailable()) {
            return Resp.error(MyConstants.HTTP_ROOM_ERROR, "房间不存在。");
        }
        boolean success = manager.addPlayer((String) session.getAttribute(MyConstants.SESSION_USERNAME_KEY),
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
        DuoRoomManager duoRoomManager = DuoRoomManager.manager(game, room);
        GameManager gameManager = GameManager.manager(game, room);
        // 如果还在等待，直接打断就可以了
        if (duoRoomManager.getWaitingThread() != null) {
            duoRoomManager.getWaitingThread().interrupt();
        } else {
            // 获取到锁一定在Game创建完成之后，一定没有建立websocket
            // 移除游戏使玩家无法加入，并在websocket判断
            duoRoomManager.lock();
            try {
                gameManager.remove();
            } finally {
                duoRoomManager.unlock();
            }
        }
        return Resp.ok();
    }
}
