package com.api.member.exception;

import org.springframework.http.HttpStatus;

import com.global.exception.ApplicationError;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberErrorCode implements ApplicationError {
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 정보를 찾을 수 없습니다."),
	INVALID_LOGIN_REQUEST(HttpStatus.UNAUTHORIZED, "아이디 혹은 비밀번호가 잘못되었습니다."),
	ALREADY_EXISTS_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.");

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
