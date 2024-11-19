package com.ssafy.auth.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.auth.model.request.LoginRequest;
import com.ssafy.auth.model.request.SignUpVerificationRequest;
import com.ssafy.auth.service.AuthService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.token.TokenProvider;
import com.ssafy.token.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;
	private final TokenProvider jwtTokenProvider;
	private final TokenService tokenService;

	@Operation(summary = "로그인", description = "사용자 로그인 처리")
	@ApiResponses({@ApiResponse(responseCode = "200", description = "로그인 성공"),
		@ApiResponse(responseCode = "401", description = "로그인 실패 - 인증되지 않은 사용자"),
		@ApiResponse(responseCode = "500", description = "서버 오류")})
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody
	LoginRequest request) {
		try {
			MemberDto memberDto = authService.login(request);
			if (memberDto != null) {
				// Access Token 생성
				String accessToken = jwtTokenProvider.generateAccessToken(memberDto.getId(), memberDto.getRole());

				// Refresh Token 생성 및 Redis 저장
				String refreshToken = jwtTokenProvider.generateRefreshToken(memberDto.getId());
				//				System.out.println("레디스에 저장되는 값" + refreshToken + " " + memberDto.getId());
				tokenService.storeRefreshToken(memberDto.getId(), refreshToken);

				// 응답 헤더에 Access Token 포함, Refresh Token은 Body에 포함
				HttpHeaders headers = new HttpHeaders();
				headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

				return ResponseEntity.ok().headers(headers)
					.body(Map.of("name", memberDto.getName(), "refreshToken", refreshToken));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
		}
	}

	@Operation(summary = "Access Token 재발급", description = "Refresh Token으로 Access Token을 재발급합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Access Token 재발급 성공"),
		@ApiResponse(responseCode = "403", description = "Refresh Token이 유효하지 않음"),
		@ApiResponse(responseCode = "500", description = "서버 오류")
	})
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshAccessToken(@RequestBody
	Map<String, String> body) {
		try {
			String refreshToken = body.get("refreshToken");

			// Refresh Token 에서 사용자 ID 추출
			String memberId = jwtTokenProvider.getMemberIdFromToken(refreshToken);

			// Refresh Token 검증
			String storedRefreshToken = tokenService.getRefreshToken(memberId);

			// Refresh Token 유효성 검사
			if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("유효하지 않은 Refresh Token");
			}

			// Refresh Token이 유효하면, 새로운 Access Token을 발급
			String role = jwtTokenProvider.getMemberRoleFromToken(refreshToken);
			String newAccessToken = jwtTokenProvider.generateAccessToken(memberId, role);

			// 새 Access Token을 HTTP 응답 헤더에 추가
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + newAccessToken);

			return ResponseEntity.ok().headers(headers).body("새로운 Access Token이 발급되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
		}
	}

	@Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
	@ApiResponses({@ApiResponse(responseCode = "201", description = "회원 가입 성공"),
		@ApiResponse(responseCode = "500", description = "서버 오류 - 회원 가입 중 오류 발생")})
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody
	MemberDto memberDto) {
		try {
			authService.signUp(memberDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입이 완료되었습니다. 로그인 해주세요.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입 중 오류가 발생했습니다.");
		}
	}

	@PostMapping("/signup-mail")
	public String sendSignUpMail(String mail, HttpSession session) throws MessagingException {
		return authService.sendSignUpMail(mail, session) + "";
	}

	@PostMapping("/signup-verification")
	public ResponseEntity<Boolean> verifySignUpCode(@RequestBody
	SignUpVerificationRequest request,
		HttpSession session) {
		return ResponseEntity.ok(authService.verifySignUpCode(request, session));
	}

	@GetMapping("/id")
	public ResponseEntity<String> findId(@RequestParam("name")
	String name, @RequestParam("email")
	String email) {
		return ResponseEntity.ok(authService.findId(name, email));
	}
}
