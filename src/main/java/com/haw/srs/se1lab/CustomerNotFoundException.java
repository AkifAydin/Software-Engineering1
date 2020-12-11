package com.haw.srs.se1lab;

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
class CustomerNotFoundException extends Exception {

	/* ---- Class Fields ---- */

	private static final long serialVersionUID = 1L;

	/* ---- Member Fields ---- */

	private final Long customerId;

	private final String lastName;

	/* ---- Constructors ---- */

	CustomerNotFoundException(Long customerId) {
		super(String.format("Could not find customer with number %d.", customerId));
		this.customerId = customerId;
		this.lastName = "";
	}

	CustomerNotFoundException(String lastName) {
		super(String.format("Could not find customer with lastname %s.", lastName));
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
