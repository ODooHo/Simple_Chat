package com.api.message.dto.request;

public record CreateMessageReq(
	Long chatRoomId,
	Long senderId,
	String content
) {
}
