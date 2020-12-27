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

	/* ---- Member Fields ---- */

	private final CourseNumber courseNumber;

	private final String courseName;

	/* ---- Constructors ---- */

	public CourseNotFoundException(CourseNumber courseNumber) {
		super(String.format("Could not find course with course number %s.", courseNumber.getCode()));
		this.courseNumber = courseNumber;
		this.courseName = null;
	}

	public CourseNotFoundException(String courseName) {
		super(String.format("Could not find course with name %s.", courseName));
		this.courseNumber = null;
		this.courseName = courseName;
	}

	/* ---- Getters/Setters ---- */

	public CourseNumber getCourseNumber() {
		return courseNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	/* ---- Overridden Methods ---- */

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
