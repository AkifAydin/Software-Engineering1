package com.haw.se1lab.common.api.exception;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.haw.se1lab.common.api.datatype.CustomerNumber;

/**
 * Represents the exception when a customer could not be found by the given
 * search criteria.
 * 
 * @author Arne Busch
 */
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

	public static final String CUSTOMER_WITH_CUSTOMER_NUMBER_NOT_FOUND_MESSAGE = "Could not find customer with customer number %d.";

	/* ---- Member Fields ---- */

	private final Long id;

	private final CustomerNumber customerNumber;

	/* ---- Constructors ---- */

	public CustomerNotFoundException(Long id) {
		super(String.format(CUSTOMER_WITH_ID_NOT_FOUND_MESSAGE, id));
		this.id = id;
		this.customerNumber = null;
	}

	public CustomerNotFoundException(CustomerNumber customerNumber) {
		super(String.format(CUSTOMER_WITH_CUSTOMER_NUMBER_NOT_FOUND_MESSAGE, customerNumber.getNumber()));
		this.id = null;
		this.customerNumber = customerNumber;
	}

	/* ---- Getters/Setters ---- */

	public Long getId() {
		return id;
	}

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
