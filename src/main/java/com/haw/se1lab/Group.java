package com.haw.se1lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group {
    //Attribute
    private final GroupIDTyp groupId;
    private final Date createdAt;
    private String name;
    private boolean publicVisible;

    private final List<User> members;

    //Konstruktor
    public Group(GroupIDTyp groupId, String name, boolean publicVisible) {
        this.groupId = groupId;
        this.name = name;
        this.publicVisible = publicVisible;
        this.createdAt = new Date();
        this.members = new ArrayList<>();
    }

    //getter and setter
    public GroupIDTyp getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublicVisible() {
        return publicVisible;
    }

    public void setPublicVisible(boolean publicVisible) {
        this.publicVisible = publicVisible;
    }

    public Date getCreatedAt() {
        return createdAt;
    }


    public List<User> getMembers() {
        return members;
    }
}
