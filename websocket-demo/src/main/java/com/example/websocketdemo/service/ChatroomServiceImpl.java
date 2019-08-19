package com.example.websocketdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.websocketdemo.domain.Chatroom;
import com.example.websocketdemo.domain.Message;
import com.example.websocketdemo.domain.User;
import com.example.websocketdemo.entity.ChatroomEntity;
import com.example.websocketdemo.entity.MessageEntity;
import com.example.websocketdemo.entity.UserEntity;
import com.example.websocketdemo.exception.ResourceNotFoundException;
import com.example.websocketdemo.repository.ChatroomRepository;
import com.example.websocketdemo.repository.UserRepository;

@Service("ChatroomService")
public class ChatroomServiceImpl implements ChatroomService {

	@Autowired ChatroomRepository repository;

	@Override
	public List<Message> getMessagesByRoomId(long roomId) {
		// TODO Auto-generated method stub
		List<Message> messages = new ArrayList<Message>();
		List<MessageEntity> messageEntities = repository.findByIdOrderByIdAsc(roomId);
		
		for(MessageEntity entity : messageEntities) {
			Message message = entity.buildDomain();
			messages.add(message);
		}
		
		return messages;
	}

	@Override
	public void saveChatroom(Chatroom chatroom) {
		// TODO Auto-generated method stub
		ChatroomEntity entity = new ChatroomEntity();
		entity.buildEntity(chatroom);
		
		repository.saveAndFlush(entity);
	}
	
	@Override
	public void saveChatroom2(ChatroomEntity chatroomEntity) {
		// TODO Auto-generated method stub
		// ChatroomEntity entity = new ChatroomEntity();
		// entity.buildEntity(chatroom);
		
		repository.saveAndFlush(chatroomEntity);
	}

	@Override
	public void updateChatroom(Chatroom chatroom) {
		// TODO Auto-generated method stub
		ChatroomEntity entity = new ChatroomEntity();
		entity.buildEntity(chatroom);
		
		repository.save(entity);
	}

	// 채팅방 인원 중 한명이 나갔을 때.
	@Override
	public void exitChatroom(Chatroom chatroom) {
		// TODO Auto-generated method stub
		ChatroomEntity entity = new ChatroomEntity();
		entity.buildEntity(chatroom);
		
		repository.delete(entity);
	}
	
	// 채팅방의 모든 인원이 나갔을 때.
	@Override
	public void deleteChatroom(Chatroom chatroom) {
		// TODO Auto-generated method stub
		ChatroomEntity entity = new ChatroomEntity();
		entity.buildEntity(chatroom);
		
		repository.delete(entity);
	}

	@Override
	public Chatroom getChatroomByName(String name) {
		// TODO Auto-generated method stub
		return repository.findByName(name).buildDomain();
	}

	
}