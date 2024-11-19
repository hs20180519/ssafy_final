package com.ssafy.token;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class TokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.access_expiration_time}")
	private long accessTokenexpirationTime;

	@Value("${jwt.refresh_expiration_time}")
	private long refreshTokenExpirationTime;

	private final SecretKey key = Jwts.SIG.HS256.key().build();

	// Access Token 생성
	public String generateAccessToken(String memberId, String role) {
		return generateToken(memberId, role, accessTokenexpirationTime);
	}

	// Refresh Token 생성
	public String generateRefreshToken(String memberId) {
		return generateToken(memberId, null, refreshTokenExpirationTime);
	}

	// JWT 생성
	public String generateToken(String memberId, String role, long expirationTime) {
		Map<String, Object> claims = new HashMap<>();
		if (role != null) {
			claims.put("role", role);
		}
		return Jwts.builder().claims(claims).subject(memberId).signWith(key) // 서명 JWS
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + expirationTime)).compact();
	}

	public String getMemberIdFromToken(String token) {
		try {
			System.out.println("들어는오니");
			Jws<Claims> jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

			Claims claims = jws.getPayload();
			System.out.println(claims);
			return claims.getSubject();
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println("토큰 파싱 오류: " + e.getMessage());
			return null;
		}
	}

	public String getMemberRoleFromToken(String token) {
		Jws<Claims> jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
		return jws.getPayload().get("role", String.class);
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
