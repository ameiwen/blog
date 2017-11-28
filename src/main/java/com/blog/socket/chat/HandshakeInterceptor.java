package com.blog.socket.chat;

import com.blog.utils.Misc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Socket建立连接（握手）和断开
 */
public class HandshakeInterceptor implements org.springframework.web.socket.server.HandshakeInterceptor {
    private static final Logger logger = LogManager.getLogger(HandshakeInterceptor.class);
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        logger.info(">>>>>>>>>>>into beforeHandshake");
        if (request instanceof ServletServerHttpRequest) {
            logger.info(">>>>>>>>>>>into beforeHandshake do work");
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest request1 = servletRequest.getServletRequest();
            // 标记用户
            String sessionUserId = null ;//CookieInfo.getCustomerIdByCookie(request1);
            sessionUserId = request1.getParameter("uid");//临时，上线需注释掉
            String oid = request1.getParameter("oid");
            logger.info(">>>>>>>>>>>into beforeHandshake oid :"+oid+"  sessionUserId :"+sessionUserId+"<<<");
            if(!Misc.isStringEmpty(sessionUserId)){
                map.put("sessionUserId", new StringBuffer(oid).append("_").append(sessionUserId).toString());
            }else{
                return true;
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
