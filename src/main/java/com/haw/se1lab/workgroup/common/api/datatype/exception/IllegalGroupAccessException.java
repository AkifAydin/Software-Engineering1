package com.haw.se1lab.workgroup.common.api.datatype.exception;

import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import lombok.EqualsAndHashCode;
import lombok.Value;

// Lombok annotations for auto-generation of constructors/getters/setters/equals etc. in compiled classes (replaces manual boilerplate code)
// the following annotations represent an immutable value type
@Value // generates constructors, getters and more
@EqualsAndHashCode(callSuper = false) // generates equals and hashCode methods
public class IllegalGroupAccessException extends Exception{
    /*
     * Details about Lombok annotations can be found here: https://projectlombok.org
     */

    /* ---- Class Fields ---- */

    private static final long serialVersionUID = 1L;

    public static final String MESSAGE = "User (%s) is not allowed to access group %s.";

    /* ---- Member Fields ---- */

    private final String email;
    private final String groupName;

    /* ---- Constructors ---- */

    public IllegalGroupAccessException(User user, WorkGroup group) {
        super(String.format(MESSAGE, user.getEmail(), group.getName()));
        this.email = user.getEmail();
        this.groupName = group.getName();
    }
}
