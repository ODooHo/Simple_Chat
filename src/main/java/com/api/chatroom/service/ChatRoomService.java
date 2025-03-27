package com.api.chatroom.service;

import com.api.chatroom.dto.request.CreateChatRoomReq;
import com.api.chatroom.dto.response.CreateChatRoomRes;

public interface ChatRoomService {
	CreateChatRoomRes createOrGetChatRoom(CreateChatRoomReq createChatRoomReq);
}
