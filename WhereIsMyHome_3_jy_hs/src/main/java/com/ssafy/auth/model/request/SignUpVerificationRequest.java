package com.ssafy.auth.model.request;

import lombok.Getter;

@Getter
public class SignUpVerificationRequest {
    private String email;
    private String code;
}
