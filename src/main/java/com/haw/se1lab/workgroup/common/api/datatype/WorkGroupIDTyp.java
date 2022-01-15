package com.haw.se1lab.workgroup.common.api.datatype;

import javax.persistence.Embeddable;

@Embeddable // indicates that the type's attributes can be stored in columns of the owning entity's table
public class WorkGroupIDTyp {
    //Attribute
    private int workGroupID;

    //Konstruktor
    public WorkGroupIDTyp(int workGroupID) {
        this.workGroupID = workGroupID;
    }

    public WorkGroupIDTyp() {

    }

    //Getter
    public int getWorkGroupID() {
        return workGroupID;
    }

    public void setWorkGroupID(int workGroupID) {
        this.workGroupID = workGroupID;
    }
}
