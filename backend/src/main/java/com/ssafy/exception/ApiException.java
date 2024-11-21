package com.ssafy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {
	private final ErrorCode errorCode;
	
	public ApiException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
