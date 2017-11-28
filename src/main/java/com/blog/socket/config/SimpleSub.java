package com.blog.socket.config;

import com.blog.socket.handle.SocketHandler;
import com.blog.socket.request.ChannelRequest;
import com.blog.utils.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import redis.clients.jedis.JedisPubSub;

import java.util.HashMap;
import java.util.Map;

public class SimpleSub extends JedisPubSub {
    private static final Logger logger = LogManager.getLogger(SimpleSub.class);

    private ChannelRequest channelRequest;

    public SimpleSub(ChannelRequest channelRequest) {
        this.channelRequest = channelRequest;
    }

    @Override
    public void onMessage(String channel, String message) {
        if (channel.equals(channelRequest.getChannel())) {
            Map<String, Object> map = new HashMap<>();
            TextMessage textMessage;
            Map<String, Object> dataMap = JsonUtil.jsonToMap(message);
            if (!dataMap.containsKey("data")) {
                dataMap.remove("channel");
                map.put("channel", channel);
                map.put("data", dataMap);
                textMessage = new TextMessage(JsonUtil.objectToJson(map));
            } else {
                textMessage = new TextMessage(JsonUtil.objectToJson(dataMap));
            }
            SocketHandler.sendMessage(channel, textMessage);
        }
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
    }
}
