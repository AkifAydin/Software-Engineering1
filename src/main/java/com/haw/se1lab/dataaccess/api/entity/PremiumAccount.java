package com.haw.se1lab.dataaccess.api.entity;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.haw.se1lab.common.api.datatype.PremiumOption;

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
@Entity // marks this class as an entity
// default table name: PREMIUM_ACCOUNT
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // avoids redundancy in JSON
public class PremiumAccount {

	/*
	 * The tables for this class (automatically created from the code by the H2 database embedded in this project) can
	 * be seen in the H2 Console when the application is running: http://localhost:8080/h2-console
	 */

	/* ---- Member Fields ---- */

	@Id // marks this field as the entity's unique technical ID (primary key) in the database
	@GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
	// default column name: ID
	private Long id;

	@OneToOne( // this entity has one parent and the parent has only one child
			fetch = FetchType.LAZY // only loads parent on access (prevent fetch error -> association is bidirectional)
	)
	@NotNull // adds a constraint for this field (checked by Hibernate during saving)
	// default column name: OWNER_ID
	private Customer owner;

	@NotNull // adds a constraint for this field (checked by Hibernate during saving)
	// default column name: VALID_TO
	private Date validTo;

	@Enumerated(EnumType.STRING) // causes the values of this enum-type field to be stored under the enum values' names
	@ElementCollection( // marks this field as collection of non-entity values (stored in junction table)
			fetch = FetchType.EAGER // loads all elements when this entity is loaded (not only when accessing them)
	)
	// simple value collection realized by junction table; default table name: PREMIUM_ACCOUNT_BOOKED_OPTIONS
	private Set<PremiumOption> bookedOptions = new HashSet<>();

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

	public Set<PremiumOption> getBookedOptions() {
		return Collections.unmodifiableSet(bookedOptions);
	}

	/* ---- Overridden Methods ---- */

	// overridden to improve object representation in logging and debugging
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

	/**
	 * Adds the given premium option to the booked options. If the option is already booked, nothing happens.
	 * 
	 * @param option the option to add; must not be <code>null</code>
	 * @return <code>true</code> in case the option was added, <code>false</code> otherwise
	 */
	public boolean addBookedOption(PremiumOption option) {
		// check preconditions
		Assert.notNull(option, "Parameter 'option' must not be null!");

		// check if option is already booked
		boolean optionAlreadyBooked = bookedOptions.contains(option);

		if (!optionAlreadyBooked) {
			bookedOptions.add(option);
			return true;
		}

		return false;
	}

	/**
	 * Removes the given option from the booked options. If the option is not booked yet, nothing happens.
	 * 
	 * @param option the option to remove; must not be <code>null</code>
	 * @return <code>true</code> in case the option was removed, <code>false</code> otherwise
	 */
	public boolean removeBookedOption(PremiumOption option) {
		// check preconditions
		Assert.notNull(option, "Parameter 'option' must not be null!");

		// check if option is already booked
		boolean optionBooked = bookedOptions.contains(option);

		if (optionBooked) {
			bookedOptions.remove(option);
			return true;
		}

		return false;
	}

}
