package com.example.websocketdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.websocketdemo.domain.Chatroom;
import com.example.websocketdemo.domain.Message;
import com.example.websocketdemo.domain.User;
import com.example.websocketdemo.entity.ChatroomEntity;
import com.example.websocketdemo.entity.UserEntity;
import com.example.websocketdemo.repository.ChatroomRepository;
import com.example.websocketdemo.service.ChatroomService;
import com.example.websocketdemo.service.UserService;
import com.example.websocketdemo.HttpSessionUtils;

@Controller
@RequestMapping("/chat")
public class ChatroomController {
	@Autowired UserService userService;
	@Autowired ChatroomService chatroomService;
	@Autowired ChatroomRepository chatroomRepository;
	// 의존성 주입(Dependency Injection)
	// @Component, @Controller, @Repository, @Service 표시된 클래스형 빈 객체를 스프링이 스캔하여 등록하고, @Autowired 등 요청시 주입 	
	
	// 유저 로그인
	@GetMapping("")
	public String getChatroom(Model model, HttpSession session) { 
		// 기본적으로는 참여하고 있는 채팅방 리스트가 보인다.
		// 한 채팅방을 클릭했을 때는 해당 채팅방으로 이동한다.
		// 방을 들어갈 때는 session에 해당 roomId를 넣고, 새로고침(리다이렉트)
		// 방을 나갈 때는 session에 roomId값을 삭제하고, 새로고침(리다이렉트)
		if (HttpSessionUtils.isLoginUser(session)) {
			// 로그인 되어있는 경우 localhost:8080에서는 사용자 정보를 출력한다.
			if(HttpSessionUtils.getRoomIdFromSession(session) == null) {
				User user = HttpSessionUtils.getUserFromSession(session);
				model.addAttribute("user", user);
				
				for(Chatroom chatroom : user.getChatrooms()) {
					System.out.println(chatroom.getId());
					System.out.println(chatroom.getName());
					System.out.println(chatroom.getMessages());
					System.out.println(chatroom.getUsers());
				}
				
				return "chatlist";
			} else {
				long roomId = HttpSessionUtils.getRoomIdFromSession(session);
				List<Message> messages = chatroomService.getMessagesByRoomId(roomId);
				model.addAttribute("messages", messages);
				return "chatroom";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("")
	public String registerChatroom(HttpServletRequest request, @Valid Chatroom formChatroom, Model model, HttpSession session) {
		
		if (HttpSessionUtils.isLoginUser(session)) {
			// 로그인 되어있는 경우 localhost:8080에서는 사용자 정보를 출력한다.
			if(formChatroom.getName() == null) {
				// form에 입력된 정보도 없었다면
				User user = HttpSessionUtils.getUserFromSession(session);
				
				model.addAttribute("user", user);
				List<User> userlist = userService.getUsers();
				model.addAttribute("userlist", userlist);
				return "registerchatroom";
			} else {
				
				User user = HttpSessionUtils.getUserFromSession(session);
				// 현재 유저 정보는 session, 다른 유저정보랑 채팅방 이름 form에서 들고오고
				// 각각 repository에 적용시킨다. 
				String otherUserId = request.getParameter("otherUserId");
				User otherUser = new User();
				
				otherUser = userService.getUserByUserId(otherUserId);
				
				chatroomService.saveChatroom(formChatroom);
				ChatroomEntity savedChatroom = new ChatroomEntity();
				savedChatroom.buildEntity(chatroomService.getChatroomByName(formChatroom.getName()));
				
				UserEntity userentity = new UserEntity();
				userentity.buildEntity(user);
				savedChatroom.addUser(userentity);
				// chatroomRepository.save(savedChatroom);
				chatroomService.saveChatroom2(savedChatroom);
				// userService.updateUser(userentity.buildDomain());
				// chatroomRepository.flush();
				
				UserEntity userentity2 = new UserEntity();
				userentity2.buildEntity(otherUser);
				savedChatroom.addUser(userentity2);
				chatroomService.saveChatroom2(savedChatroom);
				// userService.updateUser(userentity.buildDomain());
				
				// chatroomRepository.flush();
				// chatroomService.updateChatroom(savedChatroom.buildDomain());
				
				return "redirect:/chat";
			}
			
			
		} else {
			return "redirect:/";
		}
	}
	/*
	// 유저 수정
	@PutMapping("")
	public String updateUserById(@Valid User formUser, Model model, HttpSession session) {
		User user = HttpSessionUtils.getUserFromSession(session);
		user.setUserPw(formUser.getUserPw());
		user.setName(formUser.getName());
		
		System.out.println(user.getId());
		System.out.println(user.getUserId());
		System.out.println(user.getUserPw());
		System.out.println(user.getName());
		
		userService.updateUser(user);		
		model.addAttribute("user", user);
		session.setAttribute("user", user);
		return "redirect:/";
	}
	
	// 유저 삭제
	@DeleteMapping("")
	public String deleteUserById(Model model, HttpSession session) {
		userService.deleteUser(HttpSessionUtils.getUserFromSession(session));
		
		session.invalidate();
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	*/
}