package com.ssafy.auth.model.request;

import lombok.Data;

@Data
public class SignUpRequest {
	private String id;
	private String name;
	private String email;
	private String password;
	private String address;
	private String role;
}
