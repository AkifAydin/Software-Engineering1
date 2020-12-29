package com.haw.se1lab.dataaccess.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Represents a review for a course made by one of its subscribed customers. A
 * review contains a rating with an integer value between 1 (lowest) and 5
 * (highest). Reviews can also include an optional comment.
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
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CourseReview {

	/* ---- Member Fields ---- */

	@Id
	@GeneratedValue
	private Long id;

	private String reviewer;

	private int rating;

	private String comment;

	/* ---- Constructors ---- */

	public CourseReview() {
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
