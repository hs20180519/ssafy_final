package com.ssafy.mail.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mail.service.MailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {
	private final MailService mailService;
	
	@PostMapping("/send")
	public String mailSend(String mail) {
		int number = mailService.sendMail(mail);
		
		String num = "" + number;
		
		return num;
	}
}
