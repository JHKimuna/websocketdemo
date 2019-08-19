package com.example.websocketdemo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.websocketdemo.entity.UserEntity;

@Repository
public interface UserRepository	extends JpaRepository<UserEntity, Long> {
	List<UserEntity> findAll();
	UserEntity findByUserId(String userId);
	List<UserEntity> findByNameOrderByIdAsc(String name);
	UserEntity findById(long id);
}
