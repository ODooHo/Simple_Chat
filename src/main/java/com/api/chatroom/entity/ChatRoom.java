package com.api.chatroom.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.api.member.entity.Member;

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
@Table(name = "chat_rooms")
public class ChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "first_member_id", nullable = false)
	private Member firstMember; // 순서는 의미 없다.

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "second_member_id", nullable = false)
	private Member secondMember;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdAt;

	public static ChatRoom create(Member firstMember, Member secondMember) {
		return ChatRoom.builder()
			.firstMember(firstMember)
			.secondMember(secondMember)
			.build();
	}
}
