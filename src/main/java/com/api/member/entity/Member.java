package com.api.member.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.api.member.dto.request.CreateMemberReq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String nickname;

	private String password;

	@CreationTimestamp
	private LocalDateTime createdAt;

	public static Member create(CreateMemberReq req, String encryptedPassword) {
		return builder()
			.email(req.email())
			.nickname(req.nickname())
			.password(encryptedPassword)
			.build();
	}
}
