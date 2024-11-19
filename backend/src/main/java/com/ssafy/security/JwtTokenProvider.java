package com.ssafy.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration_time}")
	private long expirationTime;

	private final SecretKey key = Jwts.SIG.HS256.key().build();

	// JWT 생성
	public String generateToken(String memberId, String role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);

		// 필요한 경우 claims에 추가적인 정보 설정
		return Jwts.builder().claims(claims).subject(memberId).signWith(key) // 서명 JWS
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + expirationTime)).compact();
	}

	public String getMemberIdFromToken(String token) {
		Jws<Claims> jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

		Claims claims = jws.getPayload();
		return claims.getSubject();
	}

	public String getMemberRoleFromToken(String token) {
		Jws<Claims> jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

		Claims claims = jws.getPayload();
		return claims.get("role", String.class);
	}

	// 토큰 유효성 검증
	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
