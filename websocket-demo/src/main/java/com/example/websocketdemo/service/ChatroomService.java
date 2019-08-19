package com.example.websocketdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.websocketdemo.domain.Chatroom;
import com.example.websocketdemo.domain.Message;
import com.example.websocketdemo.domain.User;
import com.example.websocketdemo.entity.ChatroomEntity;

public interface ChatroomService {
	// 해당 채팅방의 메시지 내용을 가져올 수 있어야 한다. get
	// 채팅방을 생성할 수 있어야 한다. post
	// 채팅방으로 사람을 초대할 수 있어야 한다. put
	// 채팅방에서 나갈 수 있어야 한다. delete
	
	List<Message> getMessagesByRoomId(long roomId);
	
	Chatroom getChatroomByName(String name);
	void saveChatroom(Chatroom chatroom); // 생성
	void updateChatroom(Chatroom chatroom); // 수정
	void exitChatroom(Chatroom chatroom); // 나가기
	void deleteChatroom(Chatroom chatroom); // 삭제
	
	void saveChatroom2(ChatroomEntity chatroomEntity); // 생성
}