package com.octopus.mygamesbackend.websocket;

import com.google.gson.Gson;
import com.octopus.mygamesbackend.pojo.game.Game;
import com.octopus.mygamesbackend.pojo.game.OddEvenGame;
import com.octopus.mygamesbackend.pojo.game.PlayerData;
import com.octopus.mygamesbackend.pojo.vo.OddOrEvenGuesserVo;
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

import java.io.IOException;
import java.util.Map;

@Controller
public class OddEvenWebSocketHandler implements WebSocketHandler {

    public final static String HANDLE_URI = "/ws/game/odd_even/gaming";
    @Autowired
    private Gson gson;
    @Autowired
    private RoomService service;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        // 存储会话
        String username = (String) session.getAttributes().get(OProperties.SESSION_USERNAME_KEY);
        String room = (String) session.getAttributes().get(OProperties.SESSION_ROOM_KEY);
        WebSocketSessionPoolUtils.pool.put(username, session);
        GameManager gameManager = GameManager.manager(OddEvenGame.GAME_NAME, room);
        // 找到游戏对象
        Game game = gameManager.getGame();
        Map<String, PlayerData> playerDataMap = game.getPlayerDataMap();
        WebSocketSessionPoolUtils.sendMessage(username, WSR.ok(3).put("opponent",
                service.getOpponentsByRoom(OddEvenGame.GAME_NAME, room, username).get(0)));
        for (String player : playerDataMap.keySet()) {
            WebSocketSessionPoolUtils.sendMessage(player, WSR.ok(1).put("data", playerDataMap.get(player)));
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String username = (String) session.getAttributes().get(OProperties.SESSION_USERNAME_KEY);
        String room = (String) session.getAttributes().get(OProperties.SESSION_ROOM_KEY);
        // 使用的筹码数量
        String content = message.getPayload().toString();
        int usedChip = 0;
        OddOrEvenGuesserVo oddOrEvenGuesserVo = null;
        try {
            usedChip = Integer.parseInt(content);
        } catch (NumberFormatException e) {
            // 是json
            oddOrEvenGuesserVo = gson.fromJson(content, OddOrEvenGuesserVo.class);
        }
        GameManager gameManager = GameManager.manager(OddEvenGame.GAME_NAME, room);
        ((OddEvenGame) gameManager.getGame()).handle(username, oddOrEvenGuesserVo == null ? usedChip :
                oddOrEvenGuesserVo.getUsedChip(), oddOrEvenGuesserVo == null ? -1 : oddOrEvenGuesserVo.getOddOrEven());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String username = (String) session.getAttributes().get(OProperties.SESSION_USERNAME_KEY);
        String room = (String) session.getAttributes().get(OProperties.SESSION_ROOM_KEY);
        GameManager gameManager = GameManager.manager(OddEvenGame.GAME_NAME, room);
        // 通知另一个玩家对方已经离开
        if (gameManager.getGame() != null) {
            String opponent = service.getOpponentsByRoom(OddEvenGame.GAME_NAME, room, username).get(0);
            WebSocketSessionPoolUtils.sendMessage(opponent, WSR.ok(2));
        }
        gameManager.remove();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
