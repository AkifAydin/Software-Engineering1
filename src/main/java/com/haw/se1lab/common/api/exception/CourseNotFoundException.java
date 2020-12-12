package com.haw.se1lab.common.api.exception;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.EqualsAndHashCode;
//import lombok.Value;
//
//@Value
//@EqualsAndHashCode(callSuper = false)
public class CourseNotFoundException extends Exception {

	/* ---- Class Fields ---- */

	private static final long serialVersionUID = 1L;

	/* ---- Member Fields ---- */

	private final long courseNumber;

	/* ---- Constructors ---- */

	public CourseNotFoundException(long courseNumber) {
		super(String.format("Could not find course with number %d.", courseNumber));
		this.courseNumber = courseNumber;
	}

	/* ---- Getters/Setters ---- */

	public long getCourseNumber() {
		return courseNumber;
	}

	/* ---- Overridden Methods ---- */

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
