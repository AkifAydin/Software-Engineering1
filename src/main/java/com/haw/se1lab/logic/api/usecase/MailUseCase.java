package com.haw.se1lab.logic.api.usecase;

public interface MailUseCase {

	boolean sendMail(String to, String subject, String text);

}
