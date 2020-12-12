package com.haw.se1lab.logic.impl.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailUseCase {

	@Autowired
	public JavaMailSender emailSender;

	public boolean sendMail(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);
		} catch (MailException ex) {
			// do some logging and handling here
			return false;
		}

		return true;
	}

}
