package com.ssafy.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.ssafy.member.model.MemberDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;
	
	@Value("${jwt.expiration_time}")
	private long accessTokenExpTime;

	// JWT 토큰 생성
	public String createToken(MemberDto memberDto) {
		Claims claims = Jwts.claims().setSubject(memberDto.getId()); // JWT payload에 저장되는 정보 단위
		claims.put("id", memberDto.getId()); // 역할 정보 추가
		Date now = new Date();
		return Jwts.builder().setClaims(claims) // 정보 저장
				.setIssuedAt(now) // 토큰 발행 시간
				.setExpiration(new Date(now.getTime() + accessTokenExpTime)) // 만료시간
				.signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 secret값 세팅
				.compact();
	}

//	// JWT 토큰에서 인증 정보 조회
//	public Authentication getAuthentication(String token) {
//		String userId = getUserIdFromToken(token);
//		List<GrantedAuthority> authorities = getAuthoritiesFromToken(token);
//		return new UsernamePasswordAuthenticationToken(new MemberDto(userId), "", authorities);
//	}
//
//	// 토큰의 유효성 + 만료일자 확인
//	public boolean validateToken(String jwtToken) {
//		try {
//			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
//			return !claims.getBody().getExpiration().before(new Date());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	// 토큰에서 사용자 정보 추출
//	private String getUserIdFromToken(String token) {
//		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//	}
//
//	// 토큰에서 권한 정보 추출
//	private List<GrantedAuthority> getAuthoritiesFromToken(String token) {
//		// roles를 Claims에서 추출하고 GrantedAuthority 리스트로 변환
//		// 예를 들어, roles가 List<String> 형식이라면 아래와 같이 처리
//		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//		List<String> roles = (List<String>) claims.get("id");
//		return roles.stream().map(SimpleGrantedAuthority::new).toList();
//	}

}
