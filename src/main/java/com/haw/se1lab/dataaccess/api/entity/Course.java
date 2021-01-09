package com.haw.se1lab.dataaccess.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.haw.se1lab.common.api.datatype.CourseNumber;

/**
 * Represents a course for personal education. Customers can subscribe to courses.
 * 
 * @author Arne Busch
 */
// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Data
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity // marks this class as an entity; default table name: COURSE
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // avoids redundancy in JSON
public class Course {

	/* ---- Member Fields ---- */

	@Id // marks this field as the entity's technical ID (primary key) in the database
	@GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
	// default column name: ID
	private Long id;

	@Embedded // causes this field's attributes to be embedded (i.e. stored in columns within this entity's table)
	// default column names for inner attributes (without attribute overrides): see comments inside of this field's type
	private CourseNumber courseNumber;

	// default column name: NAME
	private String name;

//  @Setter(AccessLevel.NONE)
	@OneToMany( // this entity can have multiple children, but every child can have only one parent
			cascade = CascadeType.ALL, // also removes children when this entity is removed
			orphanRemoval = true, // removes children after being detached from this entity without being re-attached
			fetch = FetchType.EAGER // loads all children when this entity is loaded (not only when accessing them)
	)
	// association realized by junction table; default table name: COURSE_REVIEWS
	private List<CourseReview> reviews = new ArrayList<>();

	/* ---- Constructors ---- */

	// default constructor (required by Hibernate)
	Course() {
	}

	public Course(CourseNumber courseNumber, String name) {
		this.courseNumber = courseNumber;
		this.name = name;
	}

	/* ---- Getters/Setters ---- */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CourseNumber getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(CourseNumber courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CourseReview> getReviews() {
		return reviews;
	}

	/* ---- Overridden Methods ---- */

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

	public void addReview(CourseReview review) {
		reviews.add(review);
	}

}
