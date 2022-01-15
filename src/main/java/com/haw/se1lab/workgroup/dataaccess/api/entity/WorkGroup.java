package com.haw.se1lab.workgroup.dataaccess.api.entity;

import com.haw.se1lab.workgroup.common.api.datatype.WorkGroupIDTyp;
import com.haw.se1lab.user.dataaccess.api.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.catalina.User;

/**
 * Represents a Subtask of TodoLists. user can create Subtasks for there TodoLists.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */

@Entity // marks this class as an entity
// default table name: GROUP
public class WorkGroup {
    //Attribute

    @Id // the Users unique primary key in the database
    @GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
    private Long id;

    @NotNull // adds a constraint for this field (checked by Hibernate during saving)
    private Date createdAt;

    private String name;

    private boolean publicVisible;

    //@Embedded // causes this field's attributes to be stored in columns within this entity's table
    @Embedded // causes this field's attributes to be stored in columns within this entity's table
    @NotNull // adds a constraint for this field (checked by Hibernate during saving)
    @Column(unique = true) // adds a uniqueness constraint for this field's column (business key column)
    private WorkGroupIDTyp workGroupIDTyp;


    @ManyToMany()
    private List<User> members;

    /* ---- Constructors ---- */

    // default constructor (required by Hibernate)
    public WorkGroup() {

    }

    public WorkGroup(WorkGroupIDTyp workGroupIDTyp, String name, boolean publicVisible) {
        this.workGroupIDTyp = workGroupIDTyp;
        this.name = name;
        this.publicVisible = publicVisible;
        this.createdAt = new Date();
        this.members = new ArrayList<>();
    }



    //getter and setter
    public WorkGroupIDTyp getWorkGroupIDTyp() {
        return workGroupIDTyp;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
