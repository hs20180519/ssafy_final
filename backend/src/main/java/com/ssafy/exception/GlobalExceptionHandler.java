package com.ssafy.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ApiException.class)
	protected ResponseEntity<ErrorResponse> handleApiException(final ApiException e) {
		log.error("handleApiException: {}", e.getMessage());
		
		ErrorCode errorCode = e.getErrorCode();
		
		return ResponseEntity
				.status(errorCode.getStatus().value())
				.body(ErrorResponse.builder()
						.status(errorCode.getStatus().value())
						.error(errorCode.getStatus().name())
						.code(errorCode.name())
						.message(errorCode.getMessage())
						.detailMessage(e.getMessage())
						.build()
					);
	}
	
	/**
	 * controller에서 @Valid 유효성 검증을 통과하지 못했을 경우 발생
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		log.error("handleMethodArgumentNotValidException", ex);
		
		StringBuilder sb = new StringBuilder();
		
		for(FieldError fe : ex.getBindingResult().getFieldErrors()) {
			sb
			.append(fe.getField())
			.append(":")
			.append(fe.getDefaultMessage())
			.append(", ");
		}
		
		return ResponseEntity
				.status(status)
				.body(ErrorResponse.builder()
						.status(status.value())
						.error(HttpStatus.valueOf(status.value()).name())
						.code(ex.getBindingResult().getFieldError().getCode())
						.message(sb.toString())
						.detailMessage(ex.getMessage())
						.build()
					);
	}
}
