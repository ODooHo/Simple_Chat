package com.api.message.entity;

import java.time.LocalDateTime;

import com.api.chatroom.entity.ChatRoom;
import com.api.member.entity.Member;
import com.api.message.dto.request.CreateMessageReq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "messages")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_room_id", nullable = false)
	private ChatRoom chatRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id", nullable = false)
	private Member sender;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private LocalDateTime sentAt;

	public static Message create(CreateMessageReq req, ChatRoom chatRoom, Member sender) {
		return Message.builder()
			.chatRoom(chatRoom)
			.sender(sender)
			.content(req.content())
			.sentAt(LocalDateTime.now())
			.build();
	}
}
