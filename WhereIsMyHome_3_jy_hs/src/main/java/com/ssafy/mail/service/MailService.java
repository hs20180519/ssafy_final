package com.ssafy.mail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	private static int number;
	
	public static void createNumber() {
		number = (int) (Math.random() * (90000)) + 100000;
	}
	
	public MimeMessage createMail(String mail) {
		createNumber();
		MimeMessage msg = javaMailSender.createMimeMessage();
		
		try {
			msg.setFrom(sender);
			msg.setRecipients(MimeMessage.RecipientType.TO, mail);
			msg.setSubject("이메일 인증 테스트");
			String body = "";
			body += "<h3>이메일 전송 테스트 메일입니다.</h3>";
			body += "<h1>" + number + "</h1>";
			
			msg.setText(body, "UTF-8", "html");
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	public int sendMail(String mail) {
		MimeMessage message = createMail(mail);
		javaMailSender.send(message);
		
		return number;
	}
}
