package com.ssafy.auth.model.request;

import lombok.Data;

@Data
public class SendResetPasswordEmailRequest {
    private String email;
    private String id;
}
