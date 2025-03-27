package com.api.chatroom.exception;

import org.springframework.http.HttpStatus;

import com.global.exception.ApplicationError;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChatRoomErrorCode implements ApplicationError {
	CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "채팅방 정보를 찾을 수 없습니다.");

	private final HttpStatus status;
	private final String message;

	@Override
	public HttpStatus getStatus() {
		return this.status;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
