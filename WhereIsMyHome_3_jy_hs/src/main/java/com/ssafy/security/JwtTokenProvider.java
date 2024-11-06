package com.ssafy.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration_time}")
	private long expirationTime;

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}

	// JWT 생성
	public String generateToken(String memberId) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("mid", memberId);

		// 필요한 경우 claims에 추가적인 정보 설정
		return Jwts.builder()
				.claims(claims)
				.subject(memberId)
				.signWith(Jwts.SIG.HS512.key().build()) // 서명 JWS
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expirationTime))
				.compact();
	}

	// 토큰에서 사용자 ID 추출
	public String getUserIdFromToken(String token) {
		return Jwts.parser()
				.verifyWith(Jwts.SIG.HS512.key().build())
				.build()
				.parseSignedClaims(token)
				.toString();
	}

//	// 토큰 유효성 검증
//	public boolean validateToken(String token) {
//		try {
//			Jwts.parserBuilder()
//					.setSigningKey(getSigningKey())
//					.build()
//					.parseClaimsJws(token);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
}
