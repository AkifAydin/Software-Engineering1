package com.haw.se1lab.common.api.datatype;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents a customer number. A customer number consists of digits. The
 * maximum number is 2^63 - 1.
 * 
 * @author Arne Busch
 */
// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.Value;
//
//@Value
public class CustomerNumber {

	/* ---- Member Fields ---- */

	private Long customerNumber;

	/* ---- Constructors ---- */

	public CustomerNumber() {
	}

	public CustomerNumber(Long customerNumber) {
		this.customerNumber = customerNumber;
	}

	/* ---- Getters/Setters ---- */

	public Long getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Long customerNumber) {
		this.customerNumber = customerNumber;
	}

	/* ---- Overridden Methods ---- */

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
