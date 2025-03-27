package com.api.message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.message.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findByChatRoomIdOrderBySentAt(Long chatRoomId);
}
