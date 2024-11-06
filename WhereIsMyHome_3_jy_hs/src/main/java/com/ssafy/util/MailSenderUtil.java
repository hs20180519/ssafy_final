package com.ssafy.util;

import org.springframework.mail.javamail.JavaMailSender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MailSenderUtil {
	
	private final JavaMailSender javaMailSender;
	
	private static int code;
	
	private static void createCode() {
		code = (int) (Math.random() * (90000)) + 100000;
	}
}
