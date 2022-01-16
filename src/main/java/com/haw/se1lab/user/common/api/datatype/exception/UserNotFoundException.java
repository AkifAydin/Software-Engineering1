package com.haw.se1lab.user.common.api.datatype.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

// Lombok annotations for auto-generation of constructors/getters/setters/equals etc. in compiled classes (replaces manual boilerplate code)
// the following annotations represent an immutable value type
@Value // generates constructors, getters and more
@EqualsAndHashCode(callSuper = false) // generates equals and hashCode methods
public class UserNotFoundException extends Exception{
    /*
     * Details about Lombok annotations can be found here: https://projectlombok.org
     */

    /* ---- Class Fields ---- */

    private static final long serialVersionUID = 1L;

    public static final String USER_WITH_EMAIL_NOT_FOUND_MESSAGE = "Could not find user with email %s.";

    /* ---- Member Fields ---- */

    private final String email;

    /* ---- Constructors ---- */

    public UserNotFoundException(String email) {
        super(String.format(USER_WITH_EMAIL_NOT_FOUND_MESSAGE, email));
        this.email = email;
    }

}
