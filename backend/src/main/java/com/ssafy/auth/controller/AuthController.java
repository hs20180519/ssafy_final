package com.ssafy.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.ssafy.security.JwtTokenProvider;

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

	private final JwtTokenProvider jwtTokenProvider;

	@Operation(summary = "로그인", description = "사용자 로그인 처리")
	@ApiResponses({@ApiResponse(responseCode = "200", description = "로그인 성공"),
		@ApiResponse(responseCode = "401", description = "로그인 실패 - 인증되지 않은 사용자"),
		@ApiResponse(responseCode = "500", description = "서버 오류")})
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody
	LoginRequest request) {
		try {
			MemberDto memberDto = authService.login(request);
			if (memberDto != null) {
				String token = jwtTokenProvider.generateToken(memberDto.getId(), memberDto.getRole());
				System.out.println(jwtTokenProvider.getMemberRoleFromToken(token));

				HttpHeaders headers = new HttpHeaders();
				headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);

				return ResponseEntity.ok().headers(headers).body(memberDto.getName());
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
			}
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
