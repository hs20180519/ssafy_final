package com.ssafy.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MailSenderUtil {
	
	private final JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	private static int code;
	
	private static void createCode() {
		code = (int) (Math.random() * (90000)) + 100000;
	}
	
	public String createMail(String mail) {
		createCode();
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
		TemplateEngine templateEngine = new TemplateEngine();
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		
		context.setVariable("code", code);
		
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false);
		
		templateEngine.setTemplateResolver(templateResolver);
		
		return templateEngine.process("mail", context);
	}
	
	public int sendMail(String mail) {
		MimeMessage message = createMail(mail);
		javaMailSender.send(message);
		
		return code;
	}
}