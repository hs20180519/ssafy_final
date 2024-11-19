package com.ssafy.auth.service;

import java.sql.SQLException;
import java.time.Duration;

import com.ssafy.auth.model.request.ResetPasswordRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ssafy.auth.model.request.LoginRequest;
import com.ssafy.auth.model.request.SignUpVerificationRequest;
import com.ssafy.email.service.PasswordResetEmailService;
import com.ssafy.email.service.SignUpEmailService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.member.model.service.MemberService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final SignUpEmailService signUpEmailService;
	private final PasswordResetEmailService passwordResetEmailService;
	private final MemberService memberService;
	private final MemberMapper memberMapper;
	private final StringRedisTemplate redisTemplate;

	private final String VERIFICATION_SIGNUP_CODE = "verificationSignUpCode";
	private final String VERIFICATION_SIGNUP_EMAIL = "verificationSignUpEmail";
	private final String VERIFICATION_PASSWORD_RESET = "passwordReset: ";

	@Value("${smtp.reset_password.expiration_time}")
	private long expirationTime;

	@Override
	public MemberDto login(LoginRequest loginInfo) throws SQLException {
		return memberMapper.getMember(loginInfo);
	}

	@Override
	public void signUp(MemberDto memberDto) throws SQLException {
		memberMapper.insertMember(memberDto);
	}

	@Override
	public String sendSignUpMail(String mail, HttpSession session) throws MessagingException {
		String code = signUpEmailService.send(mail);
		session.setAttribute(VERIFICATION_SIGNUP_CODE, code);
		session.setAttribute(VERIFICATION_SIGNUP_EMAIL, mail);
		return code;
	}

	@Override
	public boolean verifySignUpCode(SignUpVerificationRequest request, HttpSession session) {
		String stored = session.getAttribute(VERIFICATION_SIGNUP_CODE).toString();
		String email = (String)session.getAttribute(VERIFICATION_SIGNUP_EMAIL);

		if ((stored != null && stored.equals(request.getCode())) &&
			(email != null && email.equals(request.getEmail()))) {
			session.removeAttribute(VERIFICATION_SIGNUP_CODE);
			session.removeAttribute(VERIFICATION_SIGNUP_EMAIL);
			return true;
		}
		return false;
	}

	@Override
	public String findId(String name, String email) {
		MemberDto member = memberMapper.getMemberByNameAndEmail(name, email);
		return member.getId();
	}

	@Override
	public String sendResetPasswordEmail(String email) throws MessagingException {
		if(!memberService.existsByEmail(email)) {
			// TODO: exception 처리
			return null;
		}

		String uuid = passwordResetEmailService.send(email);

		redisTemplate.opsForValue().set(VERIFICATION_PASSWORD_RESET + uuid, email,
				Duration.ofMillis(expirationTime));

		return uuid;
	}

	@Override
	public boolean verifyResetPasswordCode(String uuid) {
		String email = redisTemplate.opsForValue().get(VERIFICATION_PASSWORD_RESET + uuid);
		if(email == null) {
			return false;
		}

		return true;
	}

	@Override
	public void resetPassword(ResetPasswordRequest request) {
		if(request.getConfirmNewPassword().equals(request.getNewPassword())) {
			memberMapper.updatePassword(request.getEmail(), request.getNewPassword());
		}
	}


}
