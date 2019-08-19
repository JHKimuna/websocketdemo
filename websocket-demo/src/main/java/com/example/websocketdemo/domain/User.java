package com.example.websocketdemo.domain;

import java.util.ArrayList;
import java.util.List;

import com.example.websocketdemo.entity.ChatroomEntity;
import com.example.websocketdemo.entity.MessageEntity;

public class User {
	private long id; // primary key
	private String userId; // unique key
	private String userPw;	
	private String name;
	private List<Message> messages = new ArrayList<Message>();
	private List<Chatroom> chatrooms = new ArrayList<Chatroom>();
	
	public User()  {}
	public User(String userId, String userPw, String name) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
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
	public List<Chatroom> getChatrooms() {
		return chatrooms;
	}
	public void setChatrooms(List<Chatroom> chatrooms) {
		this.chatrooms = chatrooms;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
}
