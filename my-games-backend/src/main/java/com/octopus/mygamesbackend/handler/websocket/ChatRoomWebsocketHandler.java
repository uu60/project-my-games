package com.octopus.mygamesbackend.handler.websocket;

import com.octopus.mygamesbackend.business.mapper.ChatRoomRecordMapper;
import com.octopus.mygamesbackend.business.pojo.dao.ChatRoomRecord;
import com.octopus.mygamesbackend.business.pojo.vo.ChatRoomRecordVo;
import com.octopus.mygamesbackend.utils.properties.MyConstants;
import com.octopus.mygamesbackend.utils.websocket.WebSocketSessionPoolUtils;
import com.octopus.mygamesbackend.utils.websocket.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class ChatRoomWebsocketHandler implements WebSocketHandler {

    public static final String HANDLE_URI = "/ws/game/chat_room/chatting";

    @Autowired
    private ChatRoomRecordMapper mapper;
    @Autowired
    private SimpleDateFormat simpleDateFormat;

    private final CopyOnWriteArraySet<String> onlineUsers = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        // 把会话存入到连接池
        String username = (String) session.getAttributes().get(MyConstants.SESSION_USERNAME_KEY);
        WebSocketSessionPoolUtils.pool.put(username, session);
        onlineUsers.add(username);
        WebSocketSessionPoolUtils.sendMessage(Resp.ok(1).put("onlineUsers", onlineUsers));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String username = (String) session.getAttributes().get(MyConstants.SESSION_USERNAME_KEY);
        String content = message.getPayload().toString();
        Date now = new Date();
        mapper.addRecord(new ChatRoomRecord(null, content, username, now));
        String formatTime = simpleDateFormat.format(now);
        WebSocketSessionPoolUtils.sendMessage(Resp.ok(2).put("message", new ChatRoomRecordVo(content, username, formatTime)));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String username = (String) session.getAttributes().get(MyConstants.SESSION_USERNAME_KEY);
        WebSocketSessionPoolUtils.close(username);
        onlineUsers.remove(username);
        WebSocketSessionPoolUtils.sendMessage(Resp.ok(1).put("onlineUsers", onlineUsers));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
