package com.haw.se1lab.user.common.api.datatype;

import javax.persistence.Embeddable;

@Embeddable // indicates that the type's attributes can be stored in columns of the owning entity's table
public class UserIDTyp {
    //Attribute
    private final int userid;

    //Konstruktor
    public UserIDTyp(int userid) {
        this.userid = userid;
    }

    //Getter
    public int getUserid() {
        return userid;
    }
}
