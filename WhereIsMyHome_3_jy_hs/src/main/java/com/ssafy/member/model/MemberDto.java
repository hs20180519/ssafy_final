package com.ssafy.member.model;

import lombok.Data;

@Data
public class MemberDto {
	private String id;
	private String password;
	private String email;
	private String name;
}
