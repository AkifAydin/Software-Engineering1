package com.haw.se1lab.common.api.datatype;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents a customer number. A customer number consists of digits. The
 * maximum number is 2^31 - 1.
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
@Embeddable
public class CustomerNumber {

	/* ---- Member Fields ---- */

	private Integer number;

	/* ---- Constructors ---- */

	public CustomerNumber() {
	}

	public CustomerNumber(Integer number) {
		this.number = number;
	}

	/* ---- Getters/Setters ---- */

	public Integer getNumber() {
		return number;
	}

	/* ---- Overridden Methods ---- */

	// overridden, so objects having the same values are considered as equal
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
