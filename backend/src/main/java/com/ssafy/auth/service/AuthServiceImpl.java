package com.ssafy.auth.service;

import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.auth.model.request.LoginRequest;
import com.ssafy.auth.model.request.SignUpVerificationRequest;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.util.MailSenderUtil;
import com.ssafy.util.PasswordUtil;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	MailSenderUtil mailSenderUtil;
	private final MemberMapper memberMapper;
	private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 해시

	private final String VERIFICATION_SIGNUP_CODE = "verificationSignUpCode";
	private final String VERIFICATION_SIGNUP_EMAIL = "verificationSignUpEmail";

	@Override
	public MemberDto login(LoginRequest loginInfo) throws SQLException {
		// 사용자 정보 조회
		MemberDto member = memberMapper.getMemberByMemberId(loginInfo.getId());
		if (member == null) {
			throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
		}
		// 비밀번호 검증

		if (!PasswordUtil.verifyPassword(loginInfo.getPassword(), member.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
		return member;
	}

	@Override
	public void signUp(MemberDto memberDto) throws SQLException {
		String hashedPassword = passwordEncoder.encode(memberDto.getPassword());
		memberDto.setPassword(hashedPassword);

		// 기본 역할 설정
		if (memberDto.getRole() == null || memberDto.getRole().isEmpty()) {
			memberDto.setRole("USER"); // 기본값으로 "USER" 설정
		}

		memberMapper.insertMember(memberDto);
	}

	@Override
	public int sendSignUpMail(String mail, HttpSession session) throws MessagingException {
		int code = mailSenderUtil.sendMail(mail);
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

}
