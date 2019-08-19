package com.example.websocketdemo.domain;

import java.time.LocalDateTime;

import com.example.websocketdemo.entity.ChatroomEntity;

public class Message {
	private long id; // primary key
	private User user; 
	private String contents;
	private Chatroom chatroom;
	private LocalDateTime chatTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Chatroom getChatroom() {
		return chatroom;
	}
	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}
	public LocalDateTime getChatTime() {
		return chatTime;
	}
	public void setChatTime(LocalDateTime chatTime) {
		this.chatTime = chatTime;
	}
	
	public Message()  {}
	public Message(User user, String contents, Chatroom chatroom) {
		super();
		this.user = user;
		this.contents = contents;
		this.chatroom = chatroom;
		this.chatTime = LocalDateTime.now();
	}
	
}
