package com.api.message.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.message.dto.request.CreateMessageReq;
import com.api.message.dto.response.ReadMessageRes;
import com.api.message.service.MessageService;
import com.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/{chatRoomId}/message")
@RestController
public class MessageController {
	private final MessageService messageService;

	@PostMapping("/send")
	public ApiResponse<ReadMessageRes> sendMessage(CreateMessageReq req) {
		return ApiResponse.ok(messageService.sendMessage(req));
	}

	@GetMapping("/readAll")
	public ApiResponse<List<ReadMessageRes>> readAllMessages(@PathVariable Long chatRoomId) {
		return ApiResponse.ok(messageService.readAllMessages(chatRoomId));
	}
}
