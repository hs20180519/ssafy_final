package com.ssafy.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String SECRET_KEY;

	@Value("${jwt.expiration_time}")
	private long EXPIRATION_TIME;

	 // JWT 생성
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        // 필요한 경우 claims에 추가적인 정보 설정
        return Jwts.builder()
                .setClaims(claims) // claims를 설정하는 메서드로 변경
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

	// 토큰에서 사용자 ID 추출
	public String getUserIdFromToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	// 토큰 유효성 검증
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}