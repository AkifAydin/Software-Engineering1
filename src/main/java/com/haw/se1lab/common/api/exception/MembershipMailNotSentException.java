package com.haw.se1lab.common.api.exception;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents the exception when an e-mail containing information about a
 * customer's course membership could not be sent.
 * 
 * @author Arne Busch
 */
// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.EqualsAndHashCode;
//import lombok.Value;
//
//@Value
//@EqualsAndHashCode(callSuper=false)
public class MembershipMailNotSentException extends Exception {

	/* ---- Class Fields ---- */

	private static final long serialVersionUID = 1L;

	public static final String COULD_NOT_SEND_MEMBERSHIP_MAIL = "Could not send membership mail to %s.";

	/* ---- Constructors ---- */

	public MembershipMailNotSentException(String recipient) {
		super(String.format(COULD_NOT_SEND_MEMBERSHIP_MAIL, recipient));
	}

	/* ---- Overridden Methods ---- */

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
