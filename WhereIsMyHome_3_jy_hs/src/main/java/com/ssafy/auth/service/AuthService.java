package com.ssafy.auth.service;

import com.ssafy.auth.model.request.JoinVerificationRequest;
import com.ssafy.util.MailSenderUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    MailSenderUtil mailSenderUtil;

    private final String VERIFICATION_JOIN_CODE = "verificationJoinCode";
    private final String VERIFICATION_JOIN_EMAIL = "verificationJoinEmail";

    public AuthService(MailSenderUtil mailSenderUtil) {
        this.mailSenderUtil = mailSenderUtil;
    }
    public int sendMailForJoin(String mail, HttpSession session) throws MessagingException {
        int code = mailSenderUtil.sendMail(mail);
        session.setAttribute(VERIFICATION_JOIN_CODE, code);
        session.setAttribute(VERIFICATION_JOIN_EMAIL, mail);
        return code;
    }

    public boolean verifyCodeForJoin(JoinVerificationRequest request, HttpSession session) {
        String stored = session.getAttribute(VERIFICATION_JOIN_CODE).toString();
        String email = (String) session.getAttribute(VERIFICATION_JOIN_EMAIL);

        if ((stored != null && stored.equals(request.getCode())) &&
                (email != null && email.equals(request.getEmail()))) {
            session.removeAttribute(VERIFICATION_JOIN_CODE);
            session.removeAttribute(VERIFICATION_JOIN_EMAIL);
            return true;
        }
        return false;
    }

}
