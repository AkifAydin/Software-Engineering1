package com.haw.se1lab.group.dataaccess.api.entity;


import com.haw.se1lab.group.common.api.datatype.GroupIDTyp;
import org.apache.catalina.User; //KomponentenSchnittstelle

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a Subtask of TodoLists. user can create Subtasks for there TodoLists.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */

@Entity // marks this class as an entity
// default table name: GROUP
public class Group {
    //Attribute

    @NotNull // adds a constraint for this field (checked by Hibernate during saving)
    private Date createdAt;

    private String name;
    private boolean publicVisible;

    //@Embedded // causes this field's attributes to be stored in columns within this entity's table
    @Id // the Groups unique primary key in the database
    @GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
    private GroupIDTyp groupId;


    @ManyToMany( // this entity can have multiple children and every child can have multiple parents
            fetch = FetchType.EAGER // loads all children when this entity is loaded (not only when accessing them)
    )
    private List<User> members;

    /* ---- Constructors ---- */

    // default constructor (required by Hibernate)
    Group(){
    }

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
