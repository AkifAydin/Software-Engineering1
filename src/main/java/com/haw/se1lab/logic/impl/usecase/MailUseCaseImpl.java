package com.haw.se1lab.logic.impl.usecase;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.haw.se1lab.logic.api.usecase.MailUseCase;

/**
 * Default implementation for {@link MailUseCase}.
 * 
 * @author Arne Busch
 */
@Service
public class MailUseCaseImpl implements MailUseCase {

	@Autowired
	public JavaMailSender mailSender;

	@Override
	public boolean sendMail(String to, String subject, String text) {
		// check preconditions
		Assert.isTrue(EmailValidator.getInstance().isValid(to), "Parameter 'to' must be a valid e-mail address!");
		Assert.notNull(subject, "Parameter 'subject' must not be null!");
		Assert.notNull(text, "Parameter 'text' must not be null!");

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			mailSender.send(message);
		} catch (MailException ex) {
			// do some logging and error handling here
			// ...

			return false;
		}

		return true;
	}

}
