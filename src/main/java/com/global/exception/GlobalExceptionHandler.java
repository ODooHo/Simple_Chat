package com.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.global.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DomainException.class)
	public ResponseEntity<ApiResponse<ApplicationError>> handleDomainException(DomainException e) {
		log.error(
			"domain exception occurred: {} - {} <- {}",
			e.getError().getStatus(),
			e.getError().getMessage(),
			e.getMessage());
		return ResponseEntity.status(e.getError().getStatus())
			.body(ApiResponse.error(e.getError()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse<ApplicationError>> handleRuntimeException(RuntimeException e) {
		log.error("runtime error occurred: {}", e.getMessage());
		log.error("{}", (Object)e.getStackTrace());
		return ResponseEntity.status(GlobalErrorCode.INTERNAL_SERVER_ERROR.getStatus())
			.body(ApiResponse.error(GlobalErrorCode.INTERNAL_SERVER_ERROR));
	}
}
