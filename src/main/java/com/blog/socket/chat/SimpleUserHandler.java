package com.blog.socket.chat;

import com.blog.cache.RedisCache;
import com.blog.socket.request.ChannelRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 从redis sub获取数据，获取该用户自己的数据
 */
public class SimpleUserHandler extends P2PSocketHandler {
    private static final Logger logger = LogManager.getLogger(SimpleUserHandler.class);

    private ConcurrentHashMap<String, SimpleUserSub> simpleSubMap = new ConcurrentHashMap<>();

    private static final Lock lock = new ReentrantLock();

    @Override
    public void handleMessage(ChannelRequest channelRequest, WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String channel = new StringBuffer(channelRequest.getChannel()).append("_").append(channelRequest.getOid()).append("_").append(channelRequest.getUid()).toString();
        logger.info("Channel [" + channelRequest.getChannel() + "] subscribed, sessionId:" + session.getId());
        lock.lock();
        try {
            SimpleUserSub simpleSub = simpleSubMap.get(channel);
            if (simpleSub == null) {
                simpleSub = new SimpleUserSub(channelRequest);
                UserSubscribeThread subscribeThread = new UserSubscribeThread(simpleSub, channel);
                subscribeThread.start();
                simpleSubMap.putIfAbsent(channel, simpleSub);
            }
        } finally {
            lock.unlock();
        }
    }

    class UserSubscribeThread extends Thread {
        private SimpleUserSub simpleSub;
        private String channel;

        public UserSubscribeThread(SimpleUserSub simpleSub, String channel) {
            this.simpleSub = simpleSub;
            this.channel = channel;
        }

        @Override
        public void run() {
            RedisCache.getInstance().subscribe(simpleSub, channel);
        }
    }
}
