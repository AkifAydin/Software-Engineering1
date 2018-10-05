package com.haw.srs.customerservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// this represents an immutable datatype (no setter)
// we cannot use @Value from lombok since jpa needs a nonargconstructor when deserializing
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PhoneNumber {
    private String countryCode;

    private String areaCode;

    private String subscriberNumber;

    public PhoneNumber(String phoneNumber) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("(\\+\\d{2})-(\\d{2,3})-(\\d{4,})");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone number: " + phoneNumber);
        }

        countryCode = matcher.group(1);
        areaCode = matcher.group(2);
        subscriberNumber = matcher.group(3);
    }
}
