package com.haw.se1lab.user.common.api.datatype.exception;

import com.haw.se1lab.user.dataaccess.api.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Value;

// Lombok annotations for auto-generation of constructors/getters/setters/equals etc. in compiled classes (replaces manual boilerplate code)
// the following annotations represent an immutable value type
@Value // generates constructors, getters and more
@EqualsAndHashCode(callSuper = false) // generates equals and hashCode methods
public class WrongPasswordException extends Exception{
    /*
     * Details about Lombok annotations can be found here: https://projectlombok.org
     */

    /* ---- Class Fields ---- */

    private static final long serialVersionUID = 1L;

    public static final String MESSAGE = "Wrong password for user %s.";

    /* ---- Member Fields ---- */

    private final String email;

    /* ---- Constructors ---- */

    public WrongPasswordException(String email) {
        super(String.format(MESSAGE, email));
        this.email = email;
    }

}
