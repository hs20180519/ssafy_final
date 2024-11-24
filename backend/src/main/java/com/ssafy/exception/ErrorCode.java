package com.ssafy.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	/*
	 * 400 BAD_REQUEST: 잘못된 요청
	 */
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request."),

	/*
	 * 401 UNAUTORIZED: 인증되지 않은 사용자의 요청
	 */
	UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED, "Unauthorized."),

	/*
	 * 403 FORBIDDEN: 권한이 없는 사용자의 요청
	 */
	FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "Forbidden."),

	/*
	 * 404 NOT_FOUND: 리소스를 찾을 수 없음
	 */
	NOT_FOUND(HttpStatus.NOT_FOUND, "Not found."),
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),

	/*
	 * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
	 */
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),

	/*
	 * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Server error."),
	FAILED_SEND_EMAIL(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 전송에 실패했습니다."),
	INVALID_PASSWORD(HttpStatus.INTERNAL_SERVER_ERROR, "비밀번호가 틀렸습니다"),
	FAILED_KAKAO_GEOCODE(HttpStatus.INTERNAL_SERVER_ERROR, "도로명주소를 좌표로 변환하는 데 실패했습니다.");

	private final HttpStatus status;
	private final String message;

}
