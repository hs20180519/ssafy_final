package com.ssafy.auth.model.request;

import lombok.Getter;

@Getter
public class JoinVerificationRequest {
    private String email;
    private String code;
}
