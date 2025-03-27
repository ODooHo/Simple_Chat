package com.api.chatroom.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.chatroom.dto.request.CreateChatRoomReq;
import com.api.chatroom.dto.response.CreateChatRoomRes;
import com.api.chatroom.service.ChatRoomService;
import com.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/chatroom")
@RestController
public class ChatRoomController {

	private final ChatRoomService chatRoomService;

	@PostMapping("/createOrGet")
	public ApiResponse<CreateChatRoomRes> createOrGet(CreateChatRoomReq req) {
		return ApiResponse.ok(chatRoomService.createOrGetChatRoom(req));
	}
}
