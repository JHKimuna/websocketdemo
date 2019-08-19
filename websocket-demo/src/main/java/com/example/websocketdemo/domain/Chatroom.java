package com.example.websocketdemo.domain;

import java.util.ArrayList;
import java.util.List;

import com.example.websocketdemo.entity.MessageEntity;
import com.example.websocketdemo.entity.UserEntity;

public class Chatroom {
	private long id; // primary key
	private String name;
	private List<Message> messages = new ArrayList<Message>();
	private List<User> users = new ArrayList<User>();
	
	public Chatroom()  {}
	public Chatroom(String userId, String name) {
		super();
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void addUser(User user) {
		users.add(user); // 채팅방의 users(채팅방에 들어와있는 유저들)에 user의 정보를 추가
        user.getChatrooms().add(this); // user에 있는 chatrooms 정보를 추가
    }
    public void removeUser(User user) {
    	users.remove(user);
        user.getChatrooms().remove(this);
    }
}
