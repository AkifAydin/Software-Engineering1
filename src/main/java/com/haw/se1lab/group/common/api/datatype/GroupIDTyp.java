package com.haw.se1lab.group.common.api.datatype;

import javax.persistence.Embeddable;

@Embeddable // indicates that the type's attributes can be stored in columns of the owning entity's table
public class GroupIDTyp {
    //Attribute
    private final int groupID;

    //Konstruktor
    public GroupIDTyp(int groupID) {
        this.groupID = groupID;
    }

    //Getter
    public int getGroupNumber() {
        return groupID;
    }

}
