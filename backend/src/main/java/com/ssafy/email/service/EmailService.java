package com.ssafy.email.service;

import jakarta.mail.MessagingException;

public interface EmailService {
	String send(String email) throws MessagingException;
}
