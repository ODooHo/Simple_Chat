package com.global.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.global.exception.ApplicationError;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
	private final LocalDateTime localDateTime;
	private final Integer code;
	private final String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final T data;

	public static ApiResponse<Void> ok() {
		return new ApiResponse<>(LocalDateTime.now(), HttpStatus.OK.value(), "SUCCESS", null);
	}

	public static <T> ApiResponse<T> ok(T data) {
		return new ApiResponse<>(LocalDateTime.now(), HttpStatus.OK.value(), "SUCCESS", data);
	}

	public static ApiResponse<ApplicationError> error(ApplicationError errorCode) {
		return new ApiResponse<>(
			LocalDateTime.now(), errorCode.getStatus().value(), errorCode.getMessage(), null);
	}

	public static ApiResponse<ApplicationError> error(ApplicationError errorCode, String message) {
		return new ApiResponse<>(
			LocalDateTime.now(), errorCode.getStatus().value(), message, null);
	}
}
