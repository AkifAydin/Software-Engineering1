package com.haw.se1lab.common.api.exception;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.EqualsAndHashCode;
//import lombok.Value;
//
//@Value
//@EqualsAndHashCode(callSuper=false)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends Exception {

	/* ---- Class Fields ---- */

	private static final long serialVersionUID = 1L;

	public static final String CUSTOMER_WITH_ID_NOT_FOUND_MESSAGE = "Could not find customer with ID %d.";

	public static final String CUSTOMER_WITH_LAST_NAME_NOT_FOUND_MESSAGE = "Could not find customer with last name %s.";

	/* ---- Member Fields ---- */

	private final Long customerId;

	private final String lastName;

	/* ---- Constructors ---- */

	public CustomerNotFoundException(Long customerId) {
		super(String.format(CUSTOMER_WITH_ID_NOT_FOUND_MESSAGE, customerId));
		this.customerId = customerId;
		this.lastName = "";
	}

	public CustomerNotFoundException(String lastName) {
		super(String.format(CUSTOMER_WITH_LAST_NAME_NOT_FOUND_MESSAGE, lastName));
		this.customerId = 0L;
		this.lastName = lastName;
	}

	/* ---- Getters/Setters ---- */

	public Long getCustomerId() {
		return customerId;
	}

	public String getLastName() {
		return lastName;
	}

	/* ---- Overridden Methods ---- */

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
