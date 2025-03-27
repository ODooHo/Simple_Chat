package com.api.chatroom.dto.request;

public record CreateChatRoomReq(
	Long firstMemberId,
	Long secondMemberId
) {
}
