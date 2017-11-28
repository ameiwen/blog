package com.blog.socket.handle;

import com.blog.socket.request.ChannelRequest;
import com.blog.utils.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransportHandler implements WebSocketHandler {

    private static final Logger logger = LogManager.getLogger(TransportHandler.class);

    private static final Map<String, CopyOnWriteArrayList<WebSocketSession>> channelMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<WebSocketSession, String> userChannelMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<WebSocketSession, Long> allConnectedSessions = new ConcurrentHashMap<>();

    private static final TextMessage heartBeatMessage = new TextMessage("1");

    private static final Lock lock = new ReentrantLock();

//    public TransportHandler() {
//        // 心跳时间监测
//        HeartBeatCheckThread heartBeatCheckThread = new HeartBeatCheckThread();
//        heartBeatCheckThread.setDaemon(true);
//        heartBeatCheckThread.setName(HeartBeatCheckThread.class.getSimpleName());
//        heartBeatCheckThread.start();
//    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.info("Transport established, session id:"
                + webSocketSession.getId() + " remoteIp:" + webSocketSession.getRemoteAddress().getAddress());
        Map<String, Object> map = new HashMap<>();
        map.put("event", "connected");
        webSocketSession.sendMessage(new TextMessage(JsonUtil.objectToJson(map)));
        allConnectedSessions.put(webSocketSession, System.currentTimeMillis());
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String msg = webSocketMessage.getPayload().toString();

        // session的心跳信息
        if ("1".equals(msg)) {

            // 收到之后返回一个信息
            SocketHandler.sendMessage(webSocketSession, heartBeatMessage);
            allConnectedSessions.remove(webSocketSession);
            allConnectedSessions.put(webSocketSession, System.currentTimeMillis());
            return;
        }

        ChannelRequest channelRequest = JsonUtil.jsonToObject(msg, ChannelRequest.class);
        SocketHandler.handle(channelRequest, webSocketSession, webSocketMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        logger.error("Transport error, session id:" + webSocketSession.getId()
                + " remoteIp:" + webSocketSession.getRemoteAddress().getAddress(), throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.info("Transport closed, session id:"
                + webSocketSession.getId() + " remoteIp:" + webSocketSession.getRemoteAddress().getAddress());
        removeSession(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public static Map<String, CopyOnWriteArrayList<WebSocketSession>> getChannelMap() {
        return channelMap;
    }


    public static ConcurrentHashMap<WebSocketSession, String> getUserChannelMap() {
        return userChannelMap;
    }

    public static void addSession(String channel, WebSocketSession socketSession) {
        lock.lock();
        try {
            CopyOnWriteArrayList<WebSocketSession> sessions = channelMap.get(channel);
            if (sessions == null) {
                sessions = new CopyOnWriteArrayList<>();
                sessions.add(socketSession);
                channelMap.put(channel, sessions);
            } else {
                if (!sessions.contains(socketSession)) {
                    sessions.add(socketSession);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void addUserChannelMap(WebSocketSession webSocketSession, String uid) {
        userChannelMap.putIfAbsent(webSocketSession, uid);
    }

    public static void removeSession(String channel, WebSocketSession socketSession) {
        lock.lock();
        try {
            CopyOnWriteArrayList<WebSocketSession> sessions = channelMap.get(channel);
            if (sessions != null) {
                sessions.remove(socketSession);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void removeUserSession(WebSocketSession webSocketSession) {
        userChannelMap.remove(webSocketSession);
    }

    private void removeSession(WebSocketSession webSocketSession) {
        allConnectedSessions.remove(webSocketSession);
        removeUserSession(webSocketSession);
        Iterator<CopyOnWriteArrayList<WebSocketSession>> iterator = channelMap.values().iterator();
        while (iterator.hasNext()) {
            CopyOnWriteArrayList<WebSocketSession> sessions = iterator.next();
            sessions.remove(webSocketSession);
        }
    }

    class HeartBeatCheckThread extends Thread {

        @Override
        public void run() {
            while (true) {
                Iterator<Map.Entry<WebSocketSession, Long>> iterator = allConnectedSessions.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<WebSocketSession, Long> entry = iterator.next();

                    // 60秒未收到心跳,则关闭连接
                    if (System.currentTimeMillis() - entry.getValue() >= 60000) {
                        WebSocketSession session = entry.getKey();
                        removeSession(session);
                        try {
                            session.close();
                        } catch (Exception e) {
                        }
                    }
                }
                try {
                    Thread.sleep(20000l);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}