package com.haw.se1lab.workinggroup.common.api.datatype;

import javax.persistence.Embeddable;

@Embeddable // indicates that the type's attributes can be stored in columns of the owning entity's table
public class WorkGroupIDTyp {
    //Attribute
    private final int groupID;

    //Konstruktor
    public WorkGroupIDTyp(int groupID) {
        this.groupID = groupID;
    }

    //Getter
    public int getGroupID() {
        return groupID;
    }
}
