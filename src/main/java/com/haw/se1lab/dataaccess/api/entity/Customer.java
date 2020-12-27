package com.haw.se1lab.dataaccess.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.datatype.PhoneNumber;

/**
 * Represents a customer of the application. Customers can e.g. subscribe to
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
public class Customer {

	/* ---- Member Fields ---- */

	@Id
	@GeneratedValue
	private Long id;

	@Embedded
	private CustomerNumber customerNumber;

	private String firstName;

	private String lastName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String email;

	private PhoneNumber phoneNumber;

//    @Setter(AccessLevel.NONE)
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Course> courses = new ArrayList<>();

	@ManyToOne
	private Course lastFinishedCourse;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
	private PremiumAccount premiumAccount;

	/* ---- Constructors ---- */

	public Customer() {
	}

	public Customer(CustomerNumber customerNumber, String firstName, String lastName, Gender gender) {
		this.customerNumber = customerNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = null;
		this.phoneNumber = null;
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

	public void addCourse(Course course) {
		if (!courses.contains(course)) {
			courses.add(course);
		}
	}

	public void removeCourse(Course course) {
		courses.remove(course);
	}
}
