package com.api.message.dto.response;

import java.time.LocalDateTime;

import com.api.message.entity.Message;

public record ReadMessageRes(
	Long messageId,
	String content,
	LocalDateTime sentAt
) {
	public static ReadMessageRes of(Message message) {
		return new ReadMessageRes(
			message.getId(),
			message.getContent(),
			message.getSentAt()
		);
	}
}
