package com.octopus.mygamesbackend.config;

import com.octopus.mygamesbackend.pojo.game.GobangGame;
import com.octopus.mygamesbackend.pojo.game.OddEvenGame;
import com.octopus.mygamesbackend.utils.manager.GameManager;
import com.octopus.mygamesbackend.utils.properties.OProperties;
import com.octopus.mygamesbackend.utils.websocket.WebSocketSessionPoolUtils;
import com.octopus.mygamesbackend.websocket.ChatRoomWebsocketHandler;
import com.octopus.mygamesbackend.websocket.GobangWebSocketHandler;
import com.octopus.mygamesbackend.websocket.OddEvenWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatRoomWebsocketHandler chatRoomWebsocketHandler;
    @Autowired
    private OddEvenWebSocketHandler oddEvenWebSocketHandler;
    @Autowired
    private GobangWebSocketHandler gobangWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatRoomWebsocketHandler, ChatRoomWebsocketHandler.HANDLE_URI)
                .addHandler(oddEvenWebSocketHandler, OddEvenWebSocketHandler.HANDLE_URI)
                .addHandler(gobangWebSocketHandler, GobangWebSocketHandler.HANDLE_URI)
                .setAllowedOriginPatterns("*")
                .addInterceptors(defaultWebsocketInterceptor());
    }

    @Bean
    public DefaultWebsocketInterceptor defaultWebsocketInterceptor() {
        return new DefaultWebsocketInterceptor();
    }

    private static class DefaultWebsocketInterceptor implements HandshakeInterceptor {

        @Override
        public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
            if (!(request instanceof ServletServerHttpRequest)) return true;
            HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
            HttpSession session = req.getSession();
            String username = (String) session.getAttribute(OProperties.SESSION_USERNAME_KEY);
            // 已有websocket连接
            if (WebSocketSessionPoolUtils.pool.containsKey(username)) {
                // 关掉原来的游戏
                WebSocketSessionPoolUtils.close(username);
            }
            attributes.put(OProperties.SESSION_USERNAME_KEY, username);
            if (OddEvenWebSocketHandler.HANDLE_URI.equals(req.getRequestURI())
                    || GobangWebSocketHandler.HANDLE_URI.equals(req.getRequestURI())) {
                String room = (String) session.getAttribute(OProperties.SESSION_ROOM_KEY);
                GameManager manager = null;
                switch (req.getRequestURI()) {
                    case OddEvenWebSocketHandler.HANDLE_URI:
                        manager = GameManager.manager(OddEvenGame.GAME_NAME, room);
                        break;
                    case GobangWebSocketHandler.HANDLE_URI:
                        manager = GameManager.manager(GobangGame.GAME_NAME, room);
                        break;
                }
                assert manager != null;
                if (!manager.existsGame()) {
                    return false;
                }
                attributes.put(OProperties.SESSION_ROOM_KEY, session.getAttribute(OProperties.SESSION_ROOM_KEY));
            }
            return true;
        }

        @Override
        public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

        }
    }
}