package com.haw.se1lab.common.api.datatype;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

/**
 * Represents a course number. A course number consists of 2-4 capital letters and digits. Usually this should be the
 * commonly used acronym for the course (e.g. "SE1" for "Software Engineering 1").
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
public class CourseNumber {

	/* ---- Class Fields ---- */

	/** The pattern for a valid course number. Example: SE1 */
	private static final String COURSE_NUMBER_PATTERN = "^[A-Z0-9]{2,4}$";

	/* ---- Member Fields ---- */

	private String code;

	/* ---- Constructors ---- */

	public CourseNumber() {
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

	public static boolean isValid(String courseNumber) {
		if (courseNumber == null) {
			return false;
		} else {
			return courseNumber.matches(COURSE_NUMBER_PATTERN);
		}
	}
}
