package com.ssafy.auth.service;

import java.sql.SQLException;

import com.ssafy.auth.model.request.LoginRequest;
import com.ssafy.auth.model.request.ResetPasswordRequest;
import com.ssafy.auth.model.request.SendResetPasswordEmailRequest;
import com.ssafy.auth.model.request.SignUpRequest;
import com.ssafy.auth.model.request.SignUpVerificationRequest;
import com.ssafy.member.model.MemberDto;

import jakarta.mail.MessagingException;

public interface AuthService {

	public MemberDto login(LoginRequest loginInfo) throws SQLException;

	public void signUp(SignUpRequest memberDto) throws SQLException;

	public String sendSignUpMail(String mail) throws MessagingException;

	public boolean verifySignUpCode(SignUpVerificationRequest request);

	public String findId(String name, String email);

	public String sendResetPasswordEmail(SendResetPasswordEmailRequest request) throws MessagingException;

	public boolean verifyResetPasswordCode(String uuid);

	public void updatePassword(ResetPasswordRequest request);
}
