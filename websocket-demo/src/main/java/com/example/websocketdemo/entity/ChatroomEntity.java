package com.example.websocketdemo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.example.websocketdemo.domain.Chatroom;
import com.example.websocketdemo.domain.Message;

@Entity
@Table(name = "chatroom")
public class ChatroomEntity {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@OrderBy
	private Long id; // database에서 sequence number, primary key 역할
	
	@Column(nullable=false, unique=true)
	private String name;
	
	/*
	// kind : 오픈채팅/비밀채닝/etc... 생략
	 * 
	 */
	@OneToMany(mappedBy="chatroom")
	private List<MessageEntity> messages = new ArrayList<MessageEntity>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="User_Chatroom",
			joinColumns = @JoinColumn(name = "chatroom_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<UserEntity> users = new HashSet<UserEntity>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Set<UserEntity> getUsers() {
		return users;
	}
	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
	public void addUser(UserEntity user) {
		users.add(user); // 채팅방의 users(채팅방에 들어와있는 유저들)에 user의 정보를 추가
        user.getChatrooms().add(this); // user에 있는 chatrooms 정보를 추가
    }
    public void removeUser(UserEntity user) {
    	users.remove(user);
        user.getChatrooms().remove(this);
    }
    
	
	public Chatroom buildDomain() {
		Chatroom chatroom = new Chatroom();
		chatroom.setId(id);
		chatroom.setName(name);
		for(MessageEntity entity : messages) {
			// List 인터페이스의 size()메서드는 List 내부의 요소들의 갯수를 의미한다.
			chatroom.getMessages().add(entity.buildDomain());
			// List 인터페이스의 add 메서드는 List에 요소를 추가한다.
			// List 인터페이스의 get 메서드는 List에서 i번째 요소를 return 한다.
		}
		for(UserEntity entity : users) {
			// List 인터페이스의 size()메서드는 List 내부의 요소들의 갯수를 의미한다.
			chatroom.getUsers().add(entity.buildDomain());
			// List 인터페이스의 add 메서드는 List에 요소를 추가한다.
			// List 인터페이스의 get 메서드는 List에서 i번째 요소를 return 한다.
		}
		return chatroom;
	}	
	public void buildEntity(Chatroom chatroom) {
		id = chatroom.getId();
		name = chatroom.getName();
		
		MessageEntity messageEntity = new MessageEntity();
		// Message -> MessageEntity
		for(int i=0;i<chatroom.getMessages().size();i++) {
			messageEntity.buildEntity(chatroom.getMessages().get(i));
			messages.add(messageEntity);
		}
		UserEntity userEntity = new UserEntity();
		for(int i=0;i<chatroom.getUsers().size();i++) {
			userEntity.buildEntity(chatroom.getUsers().get(i));
			messages.add(messageEntity);
		}
	}
}