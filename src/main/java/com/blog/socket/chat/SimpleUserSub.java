package com.blog.socket.chat;

import com.blog.socket.config.SimpleSub;
import com.blog.socket.request.ChannelRequest;
import com.blog.utils.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import redis.clients.jedis.JedisPubSub;

import java.util.HashMap;
import java.util.Map;

public class SimpleUserSub extends JedisPubSub {
    private static final Logger logger = LogManager.getLogger(SimpleSub.class);

    private ChannelRequest channelRequest;

    public SimpleUserSub(ChannelRequest channelRequest) {
        this.channelRequest = channelRequest;
    }

    @Override
    public void onMessage(String channel, String message) {
        String requestChannet = new StringBuffer(channelRequest.getChannel()).append("_").append(channelRequest.getOid()).append("_").append(channelRequest.getUid()).toString();
        if(channel.startsWith(requestChannet)){
            //获取接收消息的用户id和订单id  channel:xxxxx_oid_uid   xxx:channelRequest中的channel
            String socketSessionKey = channel.substring(channel.indexOf("p2p_") + 4);//oid_uid

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
            P2PSocketHandler.sendMessage(socketSessionKey,textMessage);

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
