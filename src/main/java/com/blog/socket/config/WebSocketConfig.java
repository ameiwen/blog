package com.blog.socket.config;

import com.blog.common.Constants;
import com.blog.socket.handle.TransportHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket 	//开启websocket
public class WebSocketConfig implements WebSocketConfigurer {
    public WebSocketConfig() {
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(transportHandler(), "/websocket").setAllowedOrigins(Constants.WEBSOCKET_ALLOW_ORIGINS.split(","));
        registry.addHandler(transportHandler(), "/sockjs/websocket").setAllowedOrigins(Constants.WEBSOCKET_ALLOW_ORIGINS.split(","));
    }

    @Bean
    public WebSocketHandler transportHandler() {
        return new TransportHandler();
    }
}