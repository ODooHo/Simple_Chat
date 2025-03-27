package com.global.exception;

import org.springframework.http.HttpStatus;

public interface ApplicationError {
	HttpStatus getStatus();

	String getMessage();
}
