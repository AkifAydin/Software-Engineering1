package com.haw.srs.se1lab;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.Value;
//
//@Value
public class CourseNumber {

	/* ---- Member Fields ---- */

	private Long courseNumber;

	/* ---- Constructors ---- */

	public CourseNumber() {
	}

	public CourseNumber(Long courseNumber) {
		this.courseNumber = courseNumber;
	}

	/* ---- Getters/Setters ---- */

	public Long getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(Long courseNumber) {
		this.courseNumber = courseNumber;
	}

	/* ---- Overridden Methods ---- */

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
