package com.ssafy.auth.service;

import com.ssafy.util.MailSenderUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    MailSenderUtil mailSenderUtil;

    private final String VERIfICATION_JOIN_CODE = "verificatioinJoinCode";

    public AuthService(MailSenderUtil mailSenderUtil) {
        this.mailSenderUtil = mailSenderUtil;
    }
    public int sendMailForJoin(String mail, HttpSession session) throws MessagingException {
        int code = mailSenderUtil.sendMail(mail);
        session.setAttribute(VERIfICATION_JOIN_CODE, code);
        return code;
    }

    public boolean verifyCodeForJoin(String code, HttpSession session) {
        String stored = (String) session.getAttribute(VERIfICATION_JOIN_CODE);

        return stored != null && stored.equals(code);
    }

}
