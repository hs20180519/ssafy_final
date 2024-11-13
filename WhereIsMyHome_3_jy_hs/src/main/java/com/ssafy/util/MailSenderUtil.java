package com.ssafy.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MailSenderUtil {

	private final JavaMailSender javaMailSender;
	private final TemplateEngine templateEngine;

	@Value("${spring.mail.username}")
	private String sender;

	private int createCode() {
		return (int)(Math.random() * (90000)) + 100000;
	}

	private String setContext(int code) {
		//		MimeMessage msg = javaMailSender.createMimeMessage();
		//		
		//		try {
		//			msg.setFrom(sender);
		//			msg.setRecipients(MimeMessage.RecipientType.TO, mail);
		//			msg.setSubject("이메일 인증 테스트");
		//			String body = "";
		//			body += "<h3>이메일 전송 테스트 메일입니다.</h3>";
		//			body += "<h1>" + code + "</h1>";
		//			
		//			msg.setText(body, "UTF-8", "html");
		//		} catch(MessagingException e) {
		//			e.printStackTrace();
		//		}

		Context context = new Context();

		context.setVariable("code", code);

		return templateEngine.process("join", context);
	}

	private MimeMessage createEmailForm(String mail, int code) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.addRecipients(MimeMessage.RecipientType.TO, mail);
		message.setSubject("인증번호 전송 테스트 메일");
		message.setFrom(sender);
		message.setText(setContext(code), "utf-8", "html");

		// TODO: Redis에 인증코드 설정

		return message;
	}

	public int sendMail(String mail) throws MessagingException {
		int code = createCode();
		MimeMessage mailForm = createEmailForm(mail, code);

		javaMailSender.send(mailForm);

		return code;
	}
}
