package com.haw.se1lab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

// TODO Uncomment Lombok annotations to auto-generate getters/setters/constructors etc. in compiled classes
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//// the following annotations represent an immutable data type (@Value can't be used, but Hibernate needs an argumentless constructor when deserializing)
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Embeddable
public class PhoneNumber {

	/* ---- Class Fields ---- */

	private static final String PHONENUMBER_PATTERN = "^(\\+\\d{2})-(\\d{2,3})-(\\d{4,})$";

	/* ---- Member Fields ---- */

	private String countryCode;

	private String areaCode;

	private String subscriberNumber;

	/* ---- Constructors ---- */

	public PhoneNumber() {
	}

	public PhoneNumber(String phoneNumber) throws IllegalArgumentException {
		Pattern pattern = Pattern.compile(PHONENUMBER_PATTERN);
		Matcher matcher = pattern.matcher(phoneNumber);

		if (!matcher.matches()) {
			throw new IllegalArgumentException("Invalid phone number: " + phoneNumber);
		}

		countryCode = matcher.group(1);
		areaCode = matcher.group(2);
		subscriberNumber = matcher.group(3);
	}

	public PhoneNumber(String countryCode, String areaCode, String subscriberNumber) {
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.subscriberNumber = subscriberNumber;
	}

	/* ---- Getters/Setters ---- */

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getSubscriberNumber() {
		return subscriberNumber;
	}

	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}

	/* ---- Overridden Methods ---- */

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/* ---- Custom Methods ---- */

	public static boolean isValid(String phoneNumber) {
		if (phoneNumber == null) {
			return false;
		} else {
			return phoneNumber.matches(PHONENUMBER_PATTERN);
		}
	}

}
