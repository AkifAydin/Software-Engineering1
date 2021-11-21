package com.haw.se1lab;

import java.util.Date;

public class Group {
    //Attribute
    private final String groupName;
    private final GroupNumber groupNumber;
    private final boolean publicVisible;
    private final Date validFrom;
    private final Date validTo;

    //Konstruktor
    public Group(String groupName, GroupNumber groupNumber, boolean publicVisible, Date validFrom, Date validTo) {
        this.groupName = groupName;
        this.groupNumber = groupNumber;
        this.publicVisible = publicVisible;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    //Getters
    public String getGroupName() {
        return groupName;
    }

    public GroupNumber getGroupNumber() {
        return groupNumber;
    }

    public boolean isPublicVisible() {
        return publicVisible;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }
}
