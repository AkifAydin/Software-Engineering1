package com.haw.se1lab.common.api.datatype;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

/**
 * <p>
 * Represents a course number. A course number consists of 2-8 capital letters and digits. Usually this should be the
 * commonly used acronym for the course (e.g. "SE1" for "Software Engineering 1").
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
public class CourseNumber {

	/*
	 * Details about JPA annotations can be found here: http://www.java2s.com/Tutorials/Java/JPA/index.htm
	 */

	/*
	 * Details about Lombok annotations can be found here: https://projectlombok.org
	 */

	/* ---- Class Fields ---- */

	/** The pattern for a valid course number. Example: SE1 */
	private static final String COURSE_NUMBER_PATTERN = "^[A-Z0-9]{2,8}$";

	/* ---- Member Fields ---- */

	// default column name: CODE
	private String code;

	/* ---- Constructors ---- */

	// default constructor (required by Hibernate)
	CourseNumber() {
	}

	public CourseNumber(String code) {
		// check preconditions
		Assert.notNull(code, "Parameter 'code' must not be null!");

		if (!isValid(code)) {
			throw new IllegalArgumentException("Invalid course number: " + code);
		}

		this.code = code;
	}

	/* ---- Getters/Setters ---- */

	public String getCode() {
		return code;
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

	public static boolean isValid(String courseNumber) {
		if (courseNumber == null) {
			return false;
		} else {
			return courseNumber.matches(COURSE_NUMBER_PATTERN);
		}
	}
}
