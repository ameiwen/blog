package com.blog.socket.handle;


import com.blog.socket.request.ChannelRequest;
import com.blog.utils.Misc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 通用处理, 从redis获取数据,不需要额外处理
 */
public abstract class SocketHandler {

    private static final Logger logger = LogManager.getLogger(SocketHandler.class);

    private static final Map<String, SocketHandler> handlerMap = new ConcurrentHashMap<>();

//    private static final SimpleHandler simpleHandler = new SimpleHandler();
//
//    private static final NoticeHandler noticeHandler = new NoticeHandler();
//
//    private static final LatestPriceHandler latestPriceHandler = new LatestPriceHandler();
//
//    static {
//        handlerMap.put("lastbtcprice",latestPriceHandler);//比特币最新价格
//        handlerMap.put("lastethprice",latestPriceHandler);//以太坊最新价格
//        handlerMap.put("sub_customer_notice", noticeHandler);// 用户消息通知
//    }

    public static void handle(ChannelRequest channelRequest, WebSocketSession session, WebSocketMessage<?> message) {

        String channel = channelRequest.getChannel();
        if (Misc.isStringEmpty(channel)) {
            logger.warn("Channel is empty, sessionId:" + session.getId()
                    + " remoteAddress:" + session.getRemoteAddress().getAddress());
            return;
        }
        SocketHandler socketDataHandler = handlerMap.get(channel);
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

    public static void sendMessage(String channel, WebSocketMessage webSocketMessage) {
        CopyOnWriteArrayList<WebSocketSession> sessions = TransportHandler.getChannelMap().get(channel);
        try {
            if (sessions != null) {
                Iterator<WebSocketSession> sessionIterator = sessions.iterator();
                while (sessionIterator.hasNext()) {
                    WebSocketSession webSocketSession = sessionIterator.next();
                    if (webSocketSession != null && webSocketSession.isOpen()) {
                        synchronized (webSocketSession) {
                            try {
                                webSocketSession.sendMessage(webSocketMessage);
                            } catch (IOException ex) {
                                try {
                                    webSocketSession.close();
                                } catch (IOException ie) {
                                }
                                sessions.remove(webSocketSession);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Send message error, channel[" + channel + "]", e);
        }
    }

    public static void sendMessage(WebSocketSession webSocketSession, WebSocketMessage webSocketMessage) {
        try {
            if (webSocketSession != null && webSocketSession.isOpen()) {
                synchronized (webSocketSession) {
                    try {
                        webSocketSession.sendMessage(webSocketMessage);
                    } catch (IOException ex) {
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Send message error", e);
        }
    }

    public abstract void handleMessage(ChannelRequest channelRequest, WebSocketSession session, WebSocketMessage<?> message) throws Exception;
}
