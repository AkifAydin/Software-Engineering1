package com.haw.se1lab.dataaccess.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Represents a premium account of a customer. A premium account is a payed subscription which grants exclusive offers
 * and other benefits.
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
@Entity // marks this class as an entity; default table name: PREMIUM_ACCOUNT
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // avoids redundancy in JSON
public class PremiumAccount {

	/*
	 * The tables for this class (automatically created from the code by the H2 database embedded in this project) can
	 * be seen in the H2 Console when the application is running: http://localhost:8080/h2-console
	 */

	/* ---- Member Fields ---- */

	@Id // marks this field as the entity's technical ID (primary key) in the database
	@GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
	// default column name: ID
	private Long id;

	@OneToOne( // this entity has one parent and the parent has only one child
			optional = false, // indicates that the field must be non-null when this entity is saved
			fetch = FetchType.LAZY // only loads parent on access (prevent fetch error -> association is bidirectional)
	)
	// default column name: OWNER_ID
	private Customer owner;

	// default column name: VALID_TO
	private Date validTo;

	/* ---- Constructors ---- */

	// default constructor (required by Hibernate)
	PremiumAccount() {
	}

	public PremiumAccount(Customer owner, Date validTo) {
		this.owner = owner;
		this.validTo = validTo;
	}

	/* ---- Getters/Setters ---- */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	/* ---- Overridden Methods ---- */

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

}
