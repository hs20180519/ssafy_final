package com.ssafy.auth.controller;

import com.ssafy.auth.model.request.JoinVerificationRequest;
import com.ssafy.auth.service.AuthService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join-mail")
    public String sendMailForJoin(String mail, HttpSession session) throws MessagingException {
        return authService.sendMailForJoin(mail, session) + "";
    }

    @PostMapping("/join-verification")
    public ResponseEntity<Boolean> verifyCodeForJoin(@RequestBody JoinVerificationRequest request, HttpSession session) {
        return ResponseEntity.ok(authService.verifyCodeForJoin(request, session));
    }

    @GetMapping("/id")
    public ResponseEntity<String> findId(@RequestParam("name") String name, @RequestParam("email") String email) {
        return ResponseEntity.ok(authService.findId(name, email));
    }
}
