package com.ssafy.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {
	// 쿠키 생성
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String path) {
		Cookie cookie = new Cookie(name, value);
		cookie.setHttpOnly(true);
		cookie.setPath(path != null ? path : "/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	// 쿠키 삭제
	public static void deleteCookie(HttpServletResponse response, String name, String path) {
		Cookie cookie = new Cookie(name, null);
		cookie.setHttpOnly(true);
		cookie.setPath(path != null ? path : "/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
