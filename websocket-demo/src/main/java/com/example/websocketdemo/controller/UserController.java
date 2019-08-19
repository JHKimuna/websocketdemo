package com.example.websocketdemo.controller;

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
import com.example.websocketdemo.domain.User;
import com.example.websocketdemo.service.UserService;
import com.example.websocketdemo.HttpSessionUtils;

@Controller
@RequestMapping("")
public class UserController {
	@Autowired UserService userService; 
	// 의존성 주입(Dependency Injection)
	// @Component, @Controller, @Repository, @Service 표시된 클래스형 빈 객체를 스프링이 스캔하여 등록하고, @Autowired 등 요청시 주입 	
	
	// 유저 로그인
	@GetMapping("")
	public String loginUser(Model model, HttpSession session, User formUser) { // formUser는 가변 매개변수 사용.
		if (HttpSessionUtils.isLoginUser(session)) {
			// 로그인 되어있는 경우 localhost:8080에서는 사용자 정보를 출력한다.
			User user = HttpSessionUtils.getUserFromSession(session);
			model.addAttribute("user", user);
			return "redirect:/chat"; //원래 user였음
		} else {
			// 로그인이 되어있지 않을 때
			if(formUser.getUserId() == null) {
				// form에 입력된 정보도 없었다면
				return "login";
			} else {
				// form에 입력된 정보가 있었다면
				User user = userService.getUserByUserId(formUser.getUserId());
				 
				if(user == null) {
					System.out.println("id error : ");
					return "redirect:/";
				} else if(!user.getUserPw().equals(formUser.getUserPw())) {
					System.out.println("pw error : ");
					return "redirect:/";
				}
				
				session.setAttribute("user", user);
				return "redirect:/";
			}
		}
	}
	
	@PostMapping("")
	public String registerUser(Model model, User formUser, HttpSession session) {
		if (HttpSessionUtils.isLoginUser(session)) {
			// 로그인 되어있는 경우 localhost:8080에서는 사용자 정보를 출력한다.
			User user = HttpSessionUtils.getUserFromSession(session);
			model.addAttribute("user", user);
			
			return "user";
		} else {
			// 로그인이 되어있지 않을 때
			if(formUser.getUserId().equals("") || formUser.getUserPw().equals("") || formUser.getName().equals("")) {
				// form에 입력된 정보도 없었다면
				return "register";
			} else {
				// form에 입력된 정보가 있었다면
				User user = userService.getUserByUserId(formUser.getUserId());
				if(user == null) {
					userService.saveUser(formUser);
					// return "redirect:/";
				} else {
					// return "redirect:/"; // POST방식(현재 메서드) 리턴하는 방법 알아보기
				}
				return "redirect:/";
			}
		}
	}
	
	// 유저 수정
	@PutMapping("/user")
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
	@DeleteMapping("/user")
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
	
	//chatlist에서 회원정보 버튼 클릭시 user로 가게
	@GetMapping("/user")
	public String menuUser(Model model, HttpSession session, User formUser) { // formUser는 가변 매개변수 사용.
		if (HttpSessionUtils.isLoginUser(session)) {
			// 로그인 되어있는 경우 localhost:8080에서는 사용자 정보를 출력한다.
			User user = HttpSessionUtils.getUserFromSession(session);
			model.addAttribute("user", user);
			return "user"; //원래 user였음
		}
		return "chatlist";
	}
	
}