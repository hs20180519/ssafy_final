package com.ssafy.auth.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.address.model.AddressDto;
import com.ssafy.address.model.mapper.AddressMapper;
import com.ssafy.auth.model.request.LoginRequest;
import com.ssafy.auth.model.request.ResetPasswordRequest;
import com.ssafy.auth.model.request.SignUpRequest;
import com.ssafy.auth.model.request.SignUpVerificationRequest;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.util.PasswordUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	//	MailSenderUtil mailSenderUtil;
	private final AddressMapper addressMapper;
	private final MemberMapper memberMapper;

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

	@Transactional
	@Override
	public void signUp(SignUpRequest signUpRequest) throws SQLException {
		// 기본 역할 설정
		MemberDto memberDto = new MemberDto();
		memberDto.setId(signUpRequest.getId());
		memberDto.setEmail(signUpRequest.getEmail());
		memberDto.setName(signUpRequest.getName());
		String hashedPassword = PasswordUtil.encodePassword(signUpRequest.getPassword());
		memberDto.setPassword(hashedPassword);
		memberDto.setRole("USER"); // 기본값으로 "USER" 설정

		memberMapper.insertMember(memberDto);

		// 기본 주소 저장
		if (signUpRequest.getAddress() != null) {
			AddressDto addressDto = new AddressDto();
			addressDto.setMemberId(memberDto.getId());
			addressDto.setName("기본 주소");
			addressDto.setRoadNameAddress(signUpRequest.getAddress());

			addressMapper.insertAddress(addressDto);
		}

	}
	//
	//	public int sendSignUpMail(String mail, HttpSession session) throws MessagingException {
	//		int code = mailSenderUtil.sendMail(mail);
	//		session.setAttribute(VERIFICATION_SIGNUP_CODE, code);
	//		session.setAttribute(VERIFICATION_SIGNUP_EMAIL, mail);
	//		return code;
	//	}
	//
	//	@Override
	//	public boolean verifySignUpCode(SignUpVerificationRequest request, HttpSession session) {
	//		String stored = session.getAttribute(VERIFICATION_SIGNUP_CODE).toString();
	//		String email = (String)session.getAttribute(VERIFICATION_SIGNUP_EMAIL);
	//
	//		if ((stored != null && stored.equals(request.getCode())) &&
	//			(email != null && email.equals(request.getEmail()))) {
	//			session.removeAttribute(VERIFICATION_SIGNUP_CODE);
	//			session.removeAttribute(VERIFICATION_SIGNUP_EMAIL);
	//			return true;
	//		}
	//		return false;
	//	}

	@Override
	public String findId(String name, String email) {
		MemberDto member = memberMapper.getMemberByNameAndEmail(name, email);
		return member.getId();
	}

	@Override
	public boolean verifySignUpCode(SignUpVerificationRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updatePassword(ResetPasswordRequest request) {
		// TODO Auto-generated method stub

	}

}
