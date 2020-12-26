package com.haw.se1lab.common.api.datatype;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents a customer number. A customer number consists of digits. The
 * maximum number is 2^63 - 1.
 * 
 * @author Arne Busch
 */
//TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
////the following annotations represent an immutable data type (@Value can't be used, but Hibernate needs an argumentless constructor when deserializing)
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public class CustomerNumber {

	/* ---- Member Fields ---- */

	private Long number;

	/* ---- Constructors ---- */

	public CustomerNumber() {
	}

	public CustomerNumber(Long number) {
		this.number = number;
	}

	/* ---- Getters/Setters ---- */

	public Long getNumber() {
		return number;
	}

	/* ---- Overridden Methods ---- */

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
