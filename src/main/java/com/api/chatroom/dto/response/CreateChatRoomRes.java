package com.api.chatroom.dto.response;

import com.api.chatroom.entity.ChatRoom;

public record CreateChatRoomRes(Long chatRoomId) {
	public static CreateChatRoomRes of(ChatRoom entity) {
		return new CreateChatRoomRes(entity.getId());
	}
}
