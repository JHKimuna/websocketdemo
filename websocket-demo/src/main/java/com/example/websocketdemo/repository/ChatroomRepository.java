package com.example.websocketdemo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.websocketdemo.entity.ChatroomEntity;
import com.example.websocketdemo.entity.MessageEntity;
import com.example.websocketdemo.entity.UserEntity;

@Repository
public interface ChatroomRepository	extends JpaRepository<ChatroomEntity, Long> {
	ChatroomEntity findByName(String name);
	List<MessageEntity> findByIdOrderByIdAsc(long roomId);
}
