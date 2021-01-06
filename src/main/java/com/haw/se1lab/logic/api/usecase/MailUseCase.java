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
	 * @param to      the recipient; must be a valid e-mail address
	 * @param subject the e-mail subject; must not be <code>null</code>
	 * @param text    the e-mail body; must not be <code>null</code>
	 * @return <code>true</code> if the e-mail was sent successfully,
	 *         <code>false</code> otherwise
	 */
	boolean sendMail(String to, String subject, String text);

}
