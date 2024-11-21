package com.ssafy.email.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpEmailService implements EmailService {
	
	private final JavaMailSender mailSender;
	private final TemplateEngine templateEngine;
	private final static String SUBJECT = "이메일 인증 메일입니다.";
	
	@Value("${spring.mail.username}")
	private static String sender;
	
	private int generateCode() {
		return (int)(Math.random() * (90000)) + 100000;
	}
	

	@Override
	public String send(String email) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		Context context = new Context();
		
		int code = generateCode();
		
		context.setVariable("code", code);
		
		message.addRecipients(MimeMessage.RecipientType.TO, email);
		message.setSubject(SUBJECT);
		message.setFrom(sender);
		message.setText(templateEngine.process("signup", context), "utf-8", "html");
		
		// TODO: Redis에 인증코드 저장
		
		mailSender.send(message);
		
		return Integer.toString(code);
	}

}
