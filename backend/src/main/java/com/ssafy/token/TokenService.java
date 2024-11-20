package com.ssafy.token;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	@Value("${jwt.refresh_expiration_time}")
	private long refreshTokenExpirationTime;

	@Autowired
	private StringRedisTemplate redisTemplate;

	// Refresh Token 저장
	public void storeRefreshToken(String memberId, String refreshToken) {
		// Key: refreshToken:{memberId}, Value: refreshToken, Expiration: 7일
		redisTemplate.opsForValue().set("refreshToken:" + memberId, refreshToken,
			Duration.ofMillis(refreshTokenExpirationTime));
	}

	// Refresh Token 조회
	public String getRefreshToken(String memberId) {
		return redisTemplate.opsForValue().get("refreshToken:" + memberId);
	}

	// Refresh Token 삭제
	public void deleteRefreshToken(String memberId) {
		redisTemplate.delete("refreshToken:" + memberId);
	}
}
