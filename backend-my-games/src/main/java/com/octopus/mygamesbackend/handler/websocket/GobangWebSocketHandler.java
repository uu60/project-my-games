package com.octopus.mygamesbackend.handler.websocket;

import com.octopus.mygamesbackend.business.game.Game;
import com.octopus.mygamesbackend.business.game.GobangGame;
import com.octopus.mygamesbackend.business.game.PlayerData;
import com.octopus.mygamesbackend.business.RoomService;
import com.octopus.mygamesbackend.business.holder.GameHolder;
import com.octopus.mygamesbackend.utils.properties.MyConstants;
import com.octopus.mygamesbackend.utils.websocket.WebSocketSessionPoolUtils;
import com.octopus.mygamesbackend.utils.websocket.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@Controller
public class GobangWebSocketHandler implements WebSocketHandler {
    public static final String HANDLE_URI = "/ws/game/gobang/gaming";

    @Autowired
    private RoomService service;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String username = (String) session.getAttributes().get(MyConstants.SESSION_USERNAME_KEY);
        String room = (String) session.getAttributes().get(MyConstants.SESSION_ROOM_KEY);
        WebSocketSessionPoolUtils.pool.put(username, session);
        GameHolder gameHolder = GameHolder.get(GobangGame.GAME_NAME, room);
        // 告诉玩家身份和对手的名字
        Game game = gameHolder.getGame();
        Map<String, PlayerData> playerDataMap = game.getPlayerDataMap();
        WebSocketSessionPoolUtils.sendMessage(username, Resp.ok(3).put("opponent",
                service.getOpponentsByRoom(GobangGame.GAME_NAME, room, username).get(0))
                .put("data", playerDataMap.get(username)));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String username = (String) session.getAttributes().get(MyConstants.SESSION_USERNAME_KEY);
        String room = (String) session.getAttributes().get(MyConstants.SESSION_ROOM_KEY);
        // 使用的筹码数量
        String newPiece = message.getPayload().toString();
        int x = Integer.parseInt(newPiece.split(",")[0]);
        int y = Integer.parseInt(newPiece.split(",")[1]);
        GameHolder gameHolder = GameHolder.get(GobangGame.GAME_NAME, room);
        ((GobangGame) gameHolder.getGame()).handle(x, y, username);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String username = (String) session.getAttributes().get(MyConstants.SESSION_USERNAME_KEY);
        String room = (String) session.getAttributes().get(MyConstants.SESSION_ROOM_KEY);
        GameHolder gameHolder = GameHolder.get(GobangGame.GAME_NAME, room);
        // 通知另一个玩家对方已经离开
        if (gameHolder.getGame() != null) {
            String opponent = service.getOpponentsByRoom(GobangGame.GAME_NAME, room, username).get(0);
            WebSocketSessionPoolUtils.sendMessage(opponent, Resp.ok(2));
        }
        gameHolder.remove();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
