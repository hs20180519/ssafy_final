package com.ssafy.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// 비밀번호 검증 메소드
	public static boolean verifyPassword(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	// 비밀번호 인코딩 메소드
	public static String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
