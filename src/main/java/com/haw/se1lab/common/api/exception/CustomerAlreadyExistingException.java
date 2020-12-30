package com.haw.se1lab.common.api.exception;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.haw.se1lab.common.api.datatype.CustomerNumber;

/**
 * Represents the exception when a customer could not be created because he
 * already exists.
 * 
 * @author Arne Busch
 */
// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.EqualsAndHashCode;
//import lombok.Value;

//@Value
//@EqualsAndHashCode(callSuper=false)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistingException extends Exception {

	/* ---- Class Fields ---- */

	private static final long serialVersionUID = 1L;

	/* ---- Member Fields ---- */

	private final CustomerNumber customerNumber;

	/* ---- Constructors ---- */

	public CustomerAlreadyExistingException(CustomerNumber customerNumber) {
		super(String.format("Customer with customer number %s already exists.", customerNumber.getNumber()));
		this.customerNumber = customerNumber;
	}

	/* ---- Getters/Setters ---- */

	public CustomerNumber getCustomerNumber() {
		return customerNumber;
	}

	/* ---- Overridden Methods ---- */

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
