package com.blog.socket.chat;

import com.blog.socket.request.ChannelRequest;
import com.blog.utils.JsonUtil;
import com.blog.utils.Misc;
import com.blog.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChatHandler extends P2PSocketHandler {
    private static final Logger logger = LogManager.getLogger(ChatHandler.class);
    private static final String IMGHOST = PropertyReader.get("img.host","filePath.properties");
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(100);
    private static final Lock lock = new ReentrantLock();

//    private BitUserChatService bitUserChatService = ContextUtil.getBean(BitUserChatService.class);

    @Override
    public void handleMessage(ChannelRequest channelRequest, WebSocketSession session, WebSocketMessage<?> message){
        String oid = channelRequest.getOid();
        String to = channelRequest.getTouid();
        if (Misc.isStringEmpty(channelRequest.getUid()) || Misc.isStringEmpty(to) || Misc.isStringEmpty(oid)) {
            logger.info("fromuid or touid or oid  is empty for chat handler, channel:" + channelRequest.getChannel());
            return;
        }
        lock.lock();
        try {
            ChatMessageTread messageTread = new ChatMessageTread(channelRequest);
            Future future = threadPool.submit(messageTread);
            future.get();
        } catch (Exception e) {
            logger.error("chat thread run error", e);
        } finally {
            lock.unlock();
        }
    }

    class ChatMessageTread implements Callable {
        private ChannelRequest request;
        private WebSocketSession webSocketSession;//接收消息人的session

        public ChatMessageTread(ChannelRequest _request) {
            this.request = _request;

            Map<String, WebSocketSession> chatSocketSessionMap = ChatTransportHandler.getChatSocketSessionMap();
            if(!chatSocketSessionMap.isEmpty() && _request!=null){
                String oid = _request.getOid();
                String to = _request.getTouid();
                if(!Misc.isStringEmpty(oid) && !Misc.isStringEmpty(to)) {
                    webSocketSession = chatSocketSessionMap.get(new StringBuffer(oid).append("_").append(to).toString());
                }
            }
        }

        @Override
        public Object call() throws Exception {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("channel", "chat");
            ChatMessage message = new ChatMessage() ;
            message.setFrom(Misc.parseLong(request.getUid()));
            message.setTo(Misc.parseLong(request.getTouid()));
            message.setOrderid(request.getOid());
            message.setImgFlag(request.isImgFlag());
            if(request.isImgFlag()){
                message.setContent(IMGHOST+request.getContent());//如果是图片，则保存的是图片路径
            }else{
                message.setContent(request.getContent());
            }
            message.setType("comm");//普通消息
            message.setDate(Misc.convertTimestamp2StringTime(new Date(),"HH:mm"));
//            message = bitUserChatService.handleChannelRequest(message);
            dataMap.put("data", message);

            if (webSocketSession != null && webSocketSession.isOpen()) {
                TextMessage textMessage = new TextMessage(JsonUtil.objectToJson(dataMap));
                try {
                    synchronized (webSocketSession) {
                        try {
                            webSocketSession.sendMessage(textMessage);
                        } catch (IOException ex) {
                        }
                    }
                } catch (Exception e) {
                    logger.error("Chat message thread run error", e);
                }
            }else{
//                BitNotice bitNotice = new BitNotice();
//                bitNotice.setContents(new StringBuffer(message.getName()).append("发来交易信息").toString());
//                bitNotice.setFromuid(message.getFrom());
//                bitNotice.setTouid(message.getTo());
//                bitNotice.setIsread("0");//未读
//                bitNotice.setOid(message.getOrderid());
//                bitUserChatService.saveBitNotice(bitNotice);
            }
            return null;
        }
    }
}
