package com.haw.se1lab.common.api.exception;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.haw.se1lab.common.api.datatype.CourseNumber;

/**
 * Represents the exception when a course could not be found by the given search
 * criteria.
 * 
 * @author Arne Busch
 */
// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.EqualsAndHashCode;
//import lombok.Value;
//
//@Value
//@EqualsAndHashCode(callSuper = false)
public class CourseNotFoundException extends Exception {

	/* ---- Class Fields ---- */

	private static final long serialVersionUID = 1L;

	public static final String COURSE_WITH_COURSE_NUMBER_NOT_FOUND_MESSAGE = "Could not find course with course number %s.";

	/* ---- Member Fields ---- */

	private final CourseNumber courseNumber;

	/* ---- Constructors ---- */

	public CourseNotFoundException(CourseNumber courseNumber) {
		super(String.format(COURSE_WITH_COURSE_NUMBER_NOT_FOUND_MESSAGE, courseNumber.getCode()));
		this.courseNumber = courseNumber;
	}

	/* ---- Getters/Setters ---- */

	public CourseNumber getCourseNumber() {
		return courseNumber;
	}

	/* ---- Overridden Methods ---- */

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
