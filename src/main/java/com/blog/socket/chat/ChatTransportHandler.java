package com.blog.socket.chat;

import com.blog.socket.request.ChannelRequest;
import com.blog.utils.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class ChatTransportHandler implements WebSocketHandler {
    private static final Logger logger = LogManager.getLogger(ChatTransportHandler.class);
//    private static final ConcurrentHashMap<WebSocketSession, ChannelRequest> chatChannelMap = new ConcurrentHashMap<>();

    public static final Map<String, WebSocketSession> chatSocketSessionMap ;
    static {
        chatSocketSessionMap = new HashMap<>();
    }

    /**
     * 建立连接后
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        String sessionUserId = (String) webSocketSession.getAttributes().get("sessionUserId");
        if (chatSocketSessionMap.get(sessionUserId) == null) {
            chatSocketSessionMap.put(sessionUserId, webSocketSession);
        }
    }

    /**
     * 消息处理
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String msg = webSocketMessage.getPayload().toString();
        ChannelRequest channelRequest = JsonUtil.jsonToObject(msg, ChannelRequest.class);
        P2PSocketHandler.handle(channelRequest,webSocketSession,webSocketMessage);
    }

    /**
     * 消息传输错误是处理
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        Iterator<Map.Entry<String, WebSocketSession>> it = chatSocketSessionMap
                .entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Map.Entry<String, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(webSocketSession.getId())) {
                chatSocketSessionMap.remove(entry.getKey());
                logger.info("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
    }

    /**
     * 关闭连接后
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.info("Websocket:" + webSocketSession.getId() + "已经关闭");
        Iterator<Map.Entry<String, WebSocketSession>> it = chatSocketSessionMap
                .entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Map.Entry<String, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(webSocketSession.getId())) {
                chatSocketSessionMap.remove(entry.getKey());
                logger.info("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    public static Map<String, WebSocketSession> getChatSocketSessionMap(){
        return chatSocketSessionMap;
    }


}
