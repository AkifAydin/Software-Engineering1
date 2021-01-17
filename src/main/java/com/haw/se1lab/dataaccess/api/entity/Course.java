package com.haw.se1lab.dataaccess.api.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

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
@Entity // marks this class as an entity
// default table name: COURSE
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // avoids redundancy in JSON
public class Course {

	/*
	 * The tables for this class (automatically created from the code by the H2 database embedded in this project) can
	 * be seen in the H2 Console when the application is running: http://localhost:8080/h2-console
	 */

	/* ---- Member Fields ---- */

	@Id // marks this field as the entity's unique technical ID (primary key) in the database
	@GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
	// default column name: ID
	private Long id;

	@Embedded // causes this field's attributes to be embedded (i.e. stored in columns within this entity's table)
	@AttributeOverride( // replaces the default column names for the embedded attributes by more meaningful ones
			name = "code", // the name of the embedded attribute
			column = @Column(name = "COURSE_NUMBER") // the column name in this entity's table
	)
	@NotNull // adds a constraint for this field (checked by Hibernate during saving)
	@Column(unique = true) // adds a uniqueness constraint for this field's column (business key column)
	// default column names for inner attributes (without attribute overrides): see comments inside of this field's type
	private CourseNumber courseNumber;

	@NotNull // adds a constraint for this field (checked by Hibernate during saving)
	@Size(min = 1, max = 150) // adds a constraint for this field (checked by Hibernate during saving)
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
		return Collections.unmodifiableList(reviews);
	}

	/* ---- Overridden Methods ---- */

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

	/**
	 * Adds the given review to the review list. If the review is already in the list, nothing happens.
	 * 
	 * @param review the review to add; must not be <code>null</code>
	 * @return <code>true</code> in case the review was added, <code>false</code> otherwise
	 */
	public boolean addReview(CourseReview review) {
		// check preconditions
		Assert.notNull(review, "Parameter 'review' must not be null!");

		// check if review already in list (identified by unique ID)
		boolean reviewAlreadyAdded = false;

		if (review.getId() != null) {
			reviewAlreadyAdded = reviews.stream().anyMatch(r -> review.getId().equals(r.getId()));
		}

		if (!reviewAlreadyAdded) {
			reviews.add(review);
			return true;
		}

		return false;
	}

	/**
	 * Removes the given review from the review list. If the review is not in the list, nothing happens.
	 * 
	 * @param review the review to remove; must not be <code>null</code> and have a non-null ID
	 * @return <code>true</code> in case the review was removed, <code>false</code> otherwise
	 */
	public boolean removeReview(CourseReview review) {
		// check preconditions
		Assert.notNull(review, "Parameter 'review' must not be null!");
		Assert.notNull(review.getId(), "Parameter 'review' must have a non-null ID!");

		// find review in list (identified by unique ID)
		Optional<CourseReview> reviewToRemove = reviews.stream().filter(r -> review.getId().equals(r.getId()))
				.findFirst();

		if (reviewToRemove.isPresent()) {
			reviews.remove(reviewToRemove.get());
			return true;
		}

		return false;
	}

}
