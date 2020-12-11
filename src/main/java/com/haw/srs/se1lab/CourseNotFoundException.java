package com.haw.srs.se1lab;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.EqualsAndHashCode;
//import lombok.Value;
//
//@Value
//@EqualsAndHashCode(callSuper = false)
class CourseNotFoundException extends Exception {

	/* ---- Class Fields ---- */

	private static final long serialVersionUID = 1L;

	/* ---- Member Fields ---- */

	private final int courseNumber;

	/* ---- Constructors ---- */

	CourseNotFoundException(int courseNumber) {
		super(String.format("Could not find course with number %d.", courseNumber));
		this.courseNumber = courseNumber;
	}

	/* ---- Getters/Setters ---- */

	public int getCourseNumber() {
		return courseNumber;
	}

	/* ---- Overridden Methods ---- */

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
