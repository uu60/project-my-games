package com.octopus.mygamesbackend.websocket;

import com.octopus.mygamesbackend.pojo.game.Game;
import com.octopus.mygamesbackend.pojo.game.GobangGame;
import com.octopus.mygamesbackend.pojo.game.PlayerData;
import com.octopus.mygamesbackend.service.RoomService;
import com.octopus.mygamesbackend.utils.manager.GameManager;
import com.octopus.mygamesbackend.utils.properties.OProperties;
import com.octopus.mygamesbackend.utils.websocket.WebSocketSessionPoolUtils;
import com.octopus.mygamesbackend.utils.websocket.WSR;
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
        String username = (String) session.getAttributes().get(OProperties.SESSION_USERNAME_KEY);
        String room = (String) session.getAttributes().get(OProperties.SESSION_ROOM_KEY);
        WebSocketSessionPoolUtils.pool.put(username, session);
        GameManager gameManager = GameManager.manager(GobangGame.GAME_NAME, room);
        // 告诉玩家身份和对手的名字
        Game game = gameManager.getGame();
        Map<String, PlayerData> playerDataMap = game.getPlayerDataMap();
        WebSocketSessionPoolUtils.sendMessage(username, WSR.ok(3).put("opponent",
                service.getOpponentsByRoom(GobangGame.GAME_NAME, room, username).get(0))
                .put("data", playerDataMap.get(username)));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String username = (String) session.getAttributes().get(OProperties.SESSION_USERNAME_KEY);
        String room = (String) session.getAttributes().get(OProperties.SESSION_ROOM_KEY);
        // 使用的筹码数量
        String newPiece = message.getPayload().toString();
        int x = Integer.parseInt(newPiece.split(",")[0]);
        int y = Integer.parseInt(newPiece.split(",")[1]);
        GameManager gameManager = GameManager.manager(GobangGame.GAME_NAME, room);
        ((GobangGame) gameManager.getGame()).handle(x, y, username);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String username = (String) session.getAttributes().get(OProperties.SESSION_USERNAME_KEY);
        String room = (String) session.getAttributes().get(OProperties.SESSION_ROOM_KEY);
        GameManager gameManager = GameManager.manager(GobangGame.GAME_NAME, room);
        // 通知另一个玩家对方已经离开
        if (gameManager.getGame() != null) {
            String opponent = service.getOpponentsByRoom(GobangGame.GAME_NAME, room, username).get(0);
            WebSocketSessionPoolUtils.sendMessage(opponent, WSR.ok(2));
        }
        gameManager.remove();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
