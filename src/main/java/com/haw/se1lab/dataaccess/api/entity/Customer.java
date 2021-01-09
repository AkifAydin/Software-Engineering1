package com.haw.se1lab.dataaccess.api.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.datatype.PhoneNumber;

/**
 * Represents a customer of the application. Customers can e.g. subscribe to courses.
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
@Entity // marks this class as an entity; default table name: CUSTOMER
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // avoids redundancy in JSON
public class Customer {

	/*
	 * The tables for this class (automatically created from the code by the H2 database embedded in this project) can
	 * be seen in the H2 Console when the application is running: http://localhost:8080/h2-console
	 */

	/* ---- Member Fields ---- */

	@Id // marks this field as the entity's technical ID (primary key) in the database
	@GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
	// default column name: ID
	private Long id;

	@Embedded // causes this field's attributes to be stored in columns within this entity's table
	// default column names for inner attributes (without attribute overrides): see comments inside of this field's type
	private CustomerNumber customerNumber;

	// default column name: FIRST_NAME
	private String firstName;

	// default column name: LAST_NAME
	private String lastName;

	@Enumerated(EnumType.STRING) // causes the value of this enum-type field to be stored under the enum value's name
	// default column name: GENDER
	private Gender gender;

	// default column name: EMAIL
	private String email;

	@Embedded // causes this field's attributes to be stored in columns within this entity's table
	@AttributeOverrides({ // replaces the default column names for the embedded attributes by more meaningful ones
			@AttributeOverride( //
					name = "countryCode", // the name of the embedded attribute
					column = @Column(name = "PHONE_NUMBER_COUNTRY_CODE") // the column name in this entity's table
			), @AttributeOverride( //
					name = "areaCode", // the name of the embedded attribute
					column = @Column(name = "PHONE_NUMBER_AREA_CODE") // the column name in this entity's table
			), @AttributeOverride( //
					name = "subscriberNumber", // the name of the embedded attribute
					column = @Column(name = "PHONE_NUMBER_SUBSCRIBER_NUMBER") // the column name in this entity's table
			) })
	// default column names for inner attributes (without attribute overrides): see comments inside of this field's type
	private PhoneNumber phoneNumber;

//    @Setter(AccessLevel.NONE)
	@ManyToMany( // this entity can have multiple children and every child can have multiple parents
			fetch = FetchType.EAGER // loads all children when this entity is loaded (not only when accessing them)
	)
	// association realized by junction table; default table name: CUSTOMER_COURSES
	private List<Course> courses = new ArrayList<>();

	@ManyToOne // this entity has one child, but the child can have multiple parents
	// default column name: LAST_FINISHED_COURSE_ID
	private Course lastFinishedCourse;

	@OneToOne( // this entity has one child and the child has only one parent
			cascade = CascadeType.ALL, // also removes the child when this entity is removed
			orphanRemoval = true, // removes the child after being detached from this entity without being re-attached
			mappedBy = "owner" // the field name in the child's type which holds the reference to this entity
	)
	// no column for this field (association mapped by child entity)
	private PremiumAccount premiumAccount;

	/* ---- Constructors ---- */

	// default constructor (required by Hibernate)
	Customer() {
	}

	public Customer(CustomerNumber customerNumber, String firstName, String lastName, Gender gender) {
		this.customerNumber = customerNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public Customer(CustomerNumber customerNumber, String firstName, String lastName, Gender gender, String email,
			PhoneNumber phoneNumber) {
		this.customerNumber = customerNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	/* ---- Getters/Setters ---- */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerNumber getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(CustomerNumber customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public Course getLastFinishedCourse() {
		return lastFinishedCourse;
	}

	public void setLastFinishedCourse(Course lastFinishedCourse) {
		this.lastFinishedCourse = lastFinishedCourse;
	}

	public PremiumAccount getPremiumAccount() {
		return premiumAccount;
	}

	public void setPremiumAccount(PremiumAccount premiumAccount) {
		this.premiumAccount = premiumAccount;
	}

	/* ---- Overridden Methods ---- */

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

	/**
	 * Adds the given course to the customer's booked courses. If the customer has already booked the course, nothing
	 * happens.
	 * 
	 * @param course the course to add; must not be <code>null</code> and have a non-null course number
	 * @return <code>true</code> in case the course was added, <code>false</code> otherwise
	 */
	public boolean addCourse(Course course) {
//		// check preconditions
//		Assert.notNull(course, "Parameter 'course' must not be null!");
//		Assert.notNull(course.getCourseNumber(), "Parameter 'course' must have a non-null course number!");

		// check if course already in list (identified by unique course number)
		boolean courseAlreadyBooked = courses.stream()
				.anyMatch(c -> c.getCourseNumber().equals(course.getCourseNumber()));

		if (!courseAlreadyBooked) {
			courses.add(course);
			return true;
		}

		return false;
	}

	/**
	 * Removes the given course from the customer's booked courses. If the customer has not booked the course, nothing
	 * happens.
	 * 
	 * @param course the course to remove; must not be <code>null</code> and have a non-null course number
	 * @return <code>true</code> in case the course was removed, <code>false</code> otherwise
	 */
	public boolean removeCourse(Course course) {
//		// check preconditions
//		Assert.notNull(course, "Parameter 'course' must not be null!");
//		Assert.notNull(course.getCourseNumber(), "Parameter 'course' must have a non-null course number!");

		// find course in list (identified by unique course number)
		Optional<Course> bookedCourse = courses.stream()
				.filter(c -> c.getCourseNumber().equals(course.getCourseNumber())).findFirst();

		if (bookedCourse.isPresent()) {
			courses.remove(bookedCourse.get());
			return true;
		}

		return false;
	}
}
