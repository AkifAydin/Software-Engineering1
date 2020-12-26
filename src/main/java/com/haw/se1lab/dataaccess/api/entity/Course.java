package com.haw.se1lab.dataaccess.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents a course for personal education. Customers can subscribe to
 * courses.
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
@Entity
public class Course {

	/* ---- Member Fields ---- */

	@Id
	@GeneratedValue
	private Long id;

	private String name;

//  @Setter(AccessLevel.NONE)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CourseReview> reviews = new ArrayList<>();

	/* ---- Constructors ---- */

	public Course() {
	}

	public Course(String name) {
		this.name = name;
	}

	/* ---- Getters/Setters ---- */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

	public void addReview(CourseReview review) {
		reviews.add(review);
	}

}
