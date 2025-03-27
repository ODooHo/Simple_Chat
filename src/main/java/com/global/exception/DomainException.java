package com.global.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
	private final ApplicationError error;

	public DomainException(ApplicationError error, String message) {
		super(message);
		this.error = error;
	}
}
