package com.octopus.mygamesbackend.utils.websocket;

import com.google.gson.Gson;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketSessionPoolUtils {
    public static final Map<String, WebSocketSession> pool = new ConcurrentHashMap<>();
    private static Gson gson = new Gson();

    public static void close(String username) {
        WebSocketSession session = pool.get(username);
        if (session != null && session.isOpen()) {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pool.remove(username);
    }

    public static void sendMessage(String username, Object message) {
        WebSocketSession session = pool.get(username);
        if (session != null && session.isOpen()) {
            synchronized (session) {
                if (session != null && session.isOpen()) {
                    try {
                        session.sendMessage(new TextMessage(gson.toJson(message)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void sendMessage(Object message) {
        for (String username : pool.keySet()) {
            sendMessage(username, message);
        }
    }
}
