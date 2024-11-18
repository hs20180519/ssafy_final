package com.ssafy.auth.service;

import java.sql.SQLException;

import com.ssafy.auth.model.request.LoginRequest;
import com.ssafy.auth.model.request.SignUpVerificationRequest;
import com.ssafy.member.model.MemberDto;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

public interface AuthService {

	public MemberDto login(LoginRequest loginInfo) throws SQLException;

	public void signUp(MemberDto memberDto) throws SQLException;

	public int sendSignUpMail(String mail, HttpSession session) throws MessagingException;

	public boolean verifySignUpCode(SignUpVerificationRequest request, HttpSession session);

	public String findId(String name, String email);

	public String sendResetPasswordEmail(String email);
}
