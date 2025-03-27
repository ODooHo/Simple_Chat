package com.api.message.exception;

import org.springframework.http.HttpStatus;

import com.global.exception.ApplicationError;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageErrorCode implements ApplicationError {
	MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "메시지 정보를 찾을 수 없습니다.");

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
