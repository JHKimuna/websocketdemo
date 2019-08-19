package com.example.websocketdemo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.example.websocketdemo.domain.Chatroom;
import com.example.websocketdemo.domain.Message;
import com.example.websocketdemo.domain.User;

@Entity
@Table(name = "user")
public class UserEntity {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@OrderBy
	private Long id; // database에서 sequence number, primary key 역할
	
	@Column(nullable=false, length=20, unique=true) // null 하용 안함, 유일키
	private String userId;
	@Column(nullable=false)
	private String userPw;
	@Column(nullable=false)
	private String name;
	
	@OneToMany(mappedBy="user")
	private List<MessageEntity> messages = new ArrayList<MessageEntity>();
	
	@ManyToMany(mappedBy="users")
	private Set<ChatroomEntity> chatrooms = new HashSet<ChatroomEntity>();
	
	// 얼굴 사진 profile 필드, 생략
	// 친구 기능 friend List<User> 생략
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public List<MessageEntity> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}
	public Set<ChatroomEntity> getChatrooms() {
		return chatrooms;
	}
	public void setChatrooms(Set<ChatroomEntity> chatrooms) {
		this.chatrooms = chatrooms;
	}
    
	public User buildDomain() {
		User user = new User();
		user.setId(id);
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setName(name);
		
		List<Message> userMessages = new ArrayList<Message>();
		for(MessageEntity message : messages) {
			userMessages.add(message.buildDomain());
			// user.getMessages().add(message.buildDomain());
		}
		user.setMessages(userMessages);
		
		List<Chatroom> userChatrooms = new ArrayList<Chatroom>();
		for(ChatroomEntity chatroom : chatrooms) {
			// user.getChatrooms().add(chatroom.buildDomain());
			userChatrooms.add(chatroom.buildDomain());
		}
		user.setChatrooms(userChatrooms);
		
		return user;
	}
	public void buildEntity(User user) {
		id = user.getId();
		userId = user.getUserId();
		userPw = user.getUserPw();
		name = user.getName();
		
		MessageEntity messageEntity = new MessageEntity();
		// Message -> MessageEntity
		for(int i=0;i<user.getMessages().size();i++) {
			messageEntity.buildEntity(user.getMessages().get(i));
			messages.add(messageEntity);
		}
		
		ChatroomEntity chatroomEntity = new ChatroomEntity();
		// Message -> MessageEntity
		for(int i=0;i<user.getChatrooms().size();i++) {
			chatroomEntity.buildEntity(user.getChatrooms().get(i));
			chatrooms.add(chatroomEntity);
		}
	}
}