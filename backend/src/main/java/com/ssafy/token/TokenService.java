package com.ssafy.token;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
	private final String VERIFICATION_REFRESH_TOKEN = "refreshToken:";

	@Value("${jwt.refresh_expiration_time}")
	private long refreshTokenExpirationTime;

	@Autowired
	private StringRedisTemplate redisTemplate;

	public void storeRefreshToken(String memberId, String refreshToken) {
		redisTemplate.opsForValue().set(VERIFICATION_REFRESH_TOKEN + memberId, refreshToken,
			Duration.ofMillis(refreshTokenExpirationTime));
	}

	public String getRefreshToken(String memberId) {
		return redisTemplate.opsForValue().get(VERIFICATION_REFRESH_TOKEN + memberId);
	}

	public void deleteRefreshToken(String memberId) {
		redisTemplate.delete(VERIFICATION_REFRESH_TOKEN + memberId);
	}
}
