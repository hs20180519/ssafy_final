package com.ssafy.auth.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.auth.model.request.JoinVerificationRequest;
import com.ssafy.member.model.MemberDto;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

public interface AuthService {

    public MemberDto login(Map<String, String> loginInfo) throws SQLException;
    public void join(MemberDto memberDto) throws SQLException;
    public int sendMailForJoin(String mail, HttpSession session) throws MessagingException;
    public boolean verifyCodeForJoin(JoinVerificationRequest request, HttpSession session);
    public String findId(String name, String email);

}
