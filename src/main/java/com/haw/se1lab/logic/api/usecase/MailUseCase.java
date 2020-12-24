package com.haw.se1lab.logic.api.usecase;

/**
 * Defines use case functionality e-mail communication.
 * 
 * @author Arne Busch
 */
public interface MailUseCase {

	/**
	 * Sends an e-mail with the given data.
	 * 
	 * @param to      the recipient
	 * @param subject the e-mail subject
	 * @param text    the e-mail body
	 * @return <code>true</code> if the e-mail was sent successfully,
	 *         <code>false</code> otherwise
	 */
	boolean sendMail(String to, String subject, String text);

}
