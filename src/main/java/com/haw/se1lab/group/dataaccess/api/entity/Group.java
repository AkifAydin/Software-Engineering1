package com.haw.se1lab.group.dataaccess.api.entity;


import com.haw.se1lab.group.common.api.datatype.GroupIDTyp;
import org.apache.catalina.User; //KomponentenSchnittstelle

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // marks this class as an entity
// default table name: Group
public class Group {
    //Attribute
    private final GroupIDTyp groupId;
    private final Date createdAt;
    private String name;
    private boolean publicVisible;

    @OneToMany( // this entity can have multiple children, but every child can have only one parent
            cascade = CascadeType.ALL, // also removes children when this entity is removed
            orphanRemoval = true, // removes children after being detached from this entity without being re-attached
            fetch = FetchType.LAZY // only loads children on access (prevent fetch error for multiple bags)
    )
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
