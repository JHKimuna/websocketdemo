package com.example.websocketdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.websocketdemo.domain.User;
import com.example.websocketdemo.entity.UserEntity;
import com.example.websocketdemo.exception.ResourceNotFoundException;
import com.example.websocketdemo.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired UserRepository repository;
	
	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		List<UserEntity> userentities = repository.findAll();
		
		for(UserEntity entity : userentities) {
			User user = entity.buildDomain();
			users.add(user);
		}
		return users;
	}
	
	@Override
	public User getUserByUserId(String userId) {
		UserEntity userEntity = repository.findByUserId(userId);
		if(userEntity == null)
			return null;
		else
			return userEntity.buildDomain();
	}
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		UserEntity entity = new UserEntity();
		entity.buildEntity(user);
		
		repository.save(entity);
	}
	@Override
	public void updateUser(User user) {
		UserEntity entity = new UserEntity();
		entity.buildEntity(user);
		
		repository.save(entity);
	}

	@Override
	public void deleteUser(User user) {
		UserEntity entity = new UserEntity();
		entity.buildEntity(user);
		
		repository.delete(entity);
	}
}
