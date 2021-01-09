package com.haw.se1lab.dataaccess.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Represents a review for a course made by one of its subscribed customers. A review contains a rating with an integer
 * value between 1 (lowest) and 5 (highest). Reviews can also include an optional comment.
 * 
 * @author Arne Busch
 */
// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity // marks this class as an entity; default table name: COURSE_REVIEW
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // avoids redundancy in JSON
public class CourseReview {

	/* ---- Member Fields ---- */

	@Id // marks this field as the entity's technical ID (primary key) in the database
	@GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
	// default column name: ID
	private Long id;

	// default column name: REVIEWER
	private String reviewer;

	// default column name: RATING
	private int rating;

	// default column name: COMMENT
	private String comment;

	/* ---- Constructors ---- */

	// default constructor (required by Hibernate)
	CourseReview() {
	}

	public CourseReview(String reviewer, int rating, String comment) {
		this.reviewer = reviewer;
		this.rating = rating;
		this.comment = comment;
	}

	/* ---- Getters/Setters ---- */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/* ---- Overridden Methods ---- */

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
