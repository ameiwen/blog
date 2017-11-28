package com.blog.socket.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket 	//开启websocket
public class ChatSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Resource
    ChatTransportHandler chatTransportHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatTransportHandler, "/chat").setAllowedOrigins("*").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(chatTransportHandler, "/sockjs/chat").setAllowedOrigins("*").addInterceptors(new HandshakeInterceptor()).withSockJS();
    }


}
