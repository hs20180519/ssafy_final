package com.ssafy.auth.model.request;

import lombok.Getter;

@Getter
public class LoginRequest {
	private String id;
	private String password;
}
