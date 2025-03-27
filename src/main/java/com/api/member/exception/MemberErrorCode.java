package com.api.member.exception;

import org.springframework.http.HttpStatus;

import com.global.exception.ApplicationError;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberErrorCode implements ApplicationError {
	MEMBER_NOT_FOUND("사용자 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
	INVALID_LOGIN_REQUEST("아이디 혹은 비밀번호가 잘못되었습니다.", HttpStatus.UNAUTHORIZED),
	ALREADY_EXISTS_EMAIL("이미 존재하는 이메일입니다.", HttpStatus.CONFLICT);

	private final String message;
	private final HttpStatus status;

	@Override
	public HttpStatus getStatus() {
		return this.status;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
