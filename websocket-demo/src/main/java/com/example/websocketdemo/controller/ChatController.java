/*
package com.example.websocketdemo.controller;

import com.example.websocketdemo.domain.User;
import com.example.websocketdemo.model.ChatMessage;
import com.example.websocketdemo.service.UserService;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller

public class ChatController {
	
	 //Client -> Server Stomp 요청시
	 //MessageMapping 사용해서 받는다.
	 //어떤 방에 구독했는지 / 어떤 방에 말했는지가 SendTo경로에 드러남.
	 //
	
	@Autowired UserService userService; 
	@Autowired SimpMessagingTemplate simpMessagingTemplate;
	// @Autowired MessageService userService; 
	// @Autowired ChatroomService userService; 
	
    @MessageMapping("/chat.sendMessage/{roomId}") // /app/chat.sendMessage
    //@SendTo("/topic/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable long roomId
    		//ChatMessage sendMessage
    		//@Payload ChatMessage chatMessage) {
    	simpMessagingTemplate.convertAndSend("/topic/" + roomId, new Simple(roomId));
    }
	
 
}
*/