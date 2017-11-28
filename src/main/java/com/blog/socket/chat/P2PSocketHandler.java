package com.blog.socket.chat;

import com.blog.socket.request.ChannelRequest;
import com.blog.utils.Misc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 点对点通信 通用处理
 */
public abstract class P2PSocketHandler {
    private static final Logger logger = LogManager.getLogger(P2PSocketHandler.class);

    private static final Map<String, P2PSocketHandler> handlerMap = new ConcurrentHashMap<>();

    private static final ChatHandler chatHandler = new ChatHandler();
    private static final SimpleUserHandler simpleUserHandler = new SimpleUserHandler();

    static {
        handlerMap.put("chat",chatHandler);//聊天
//        handlerMap.put("show_appeal_p2p",simpleUserHandler);//申诉按钮显示通知
        handlerMap.put("transaction_p2p",simpleUserHandler);//交易完成通知
    }

    public static void handle(ChannelRequest channelRequest, WebSocketSession session, WebSocketMessage<?> message) {
        String channel = channelRequest.getChannel();
        if (Misc.isStringEmpty(channel)) {
            logger.warn("Channel is empty, sessionId:" + session.getId()
                    + " remoteAddress:" + session.getRemoteAddress().getAddress());
            return;
        }

        P2PSocketHandler socketDataHandler = handlerMap.get(channel);
        if (socketDataHandler == null) {
            logger.warn("Not found channel handler for channel [" + channel + "], sessionId:"
                    + session.getId() + " remoteAddress:" + session.getRemoteAddress().getAddress());
            return;
        }

        try {
            socketDataHandler.handleMessage(channelRequest, session, message);
        } catch (Exception e) {
            logger.error("Handle message error, channel:" +
                    channel + " sessionId:" + session.getId() + " remoteAddress:" + session.getRemoteAddress().getAddress(), e);
        }

    }

    public abstract void handleMessage(ChannelRequest channelRequest, WebSocketSession session, WebSocketMessage<?> message) throws Exception;

    public static void sendMessage(String socketSessionKey, TextMessage textMessage) {
        Map<String, WebSocketSession> chatSocketSessionMap = ChatTransportHandler.getChatSocketSessionMap();
        if(!chatSocketSessionMap.isEmpty()){
            try {
                chatSocketSessionMap.get(socketSessionKey).sendMessage(textMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
