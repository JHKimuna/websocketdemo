package com.example.websocketdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.websocketdemo.domain.User;

public interface UserService {
	
	List<User> getUsers();
	User getUserByUserId(String userId); // primary key에 해당하는 id로  조회
	void saveUser(User user); // 생성
	void updateUser(User user); // 수정
	void deleteUser(User user); // 삭제
}
