package com.haw.se1lab.common.api.datatype;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

/**
 * <p>
 * Represents a customer number. A customer number consists of digits. The maximum number is 2^31 - 1.
 * </p>
 * <p>
 * This is a value type (like e.g. <code>String</code>), i.e. two instances of this class are considered to be equal if
 * their values are equal (although each instance in fact has its own object identity).
 * </p>
 * <p>
 * Instances of this class are immutable, i.e. their values are assigned during construction and may never be changed
 * from there on.
 * </p>
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
@Embeddable // indicates that the type's attributes can be stored in columns of the owning entity's table
public class CustomerNumber {

	/*
	 * Details about JPA annotations can be found here: http://www.java2s.com/Tutorials/Java/JPA/index.htm
	 */

	/*
	 * Details about Lombok annotations can be found here: https://projectlombok.org
	 */

	/* ---- Member Fields ---- */

	// default column name: NUMBER
	private Integer number;

	/* ---- Constructors ---- */

	// default constructor (required by Hibernate)
	CustomerNumber() {
	}

	public CustomerNumber(Integer number) {
		// check preconditions
		Assert.notNull(number, "Parameter 'number' must not be null!");

		this.number = number;
	}

	/* ---- Getters/Setters ---- */

	public Integer getNumber() {
		return number;
	}

	/* ---- Overridden Methods ---- */

	// overridden, so two instances having the same values are considered as equal
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
