package com.ssafy.auth.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.auth.model.request.LoginRequest;
import com.ssafy.auth.model.request.SignUpVerificationRequest;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.MailSenderUtil;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	MailSenderUtil mailSenderUtil;
	private final MemberService memberService;
	private final MemberMapper memberMapper;

	private final String VERIFICATION_SIGNUP_CODE = "verificationSignUpCode";
	private final String VERIFICATION_SIGNUP_EMAIL = "verificationSignUpEmail";

	@Override
	public MemberDto login(LoginRequest loginInfo) throws SQLException {
		return memberMapper.getMember(loginInfo);
	}

	@Override
	public void signUp(MemberDto memberDto) throws SQLException {
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

	@Override
	public String sendResetPasswordEmail(String email) {
		if(!memberService.existsByEmail(email)) {
			// TODO: exception 처리
			return null;
		}
		
		
		return null;
	}
	
	private String generateUUID() {
		return null;
	}


}
