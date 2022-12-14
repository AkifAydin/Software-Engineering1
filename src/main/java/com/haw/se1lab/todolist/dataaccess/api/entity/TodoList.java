package com.haw.se1lab.todolist.dataaccess.api.entity;


import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import com.haw.se1lab.tasks.dataaccess.api.entity.Task;
import com.haw.se1lab.user.dataaccess.api.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a TodoList.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */

@Entity // marks this class as an entity
// default table name: TODOLIST
public class TodoList {

    /* ---- Member Fields ---- */

    @Id // the Todolists unique primary key in the database
    @GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
    private Long id;

    private String name;

    @NotNull // adds a constraint for this field (checked by Hibernate during saving)
    private Date createdAt;

    @ManyToOne
    @NotNull // adds a constraint for this field (checked by Hibernate during saving)
    private User owner;

    private boolean visibleForOthers;

    @OneToMany
    private List<Task> tasks;

    @ManyToOne
    // @Embedded // causes this field's attributes to be stored in columns within this entity's table
    private WorkGroup workGroup;


    /* ---- Constructors ---- */

    // default constructor (required by Hibernate)
    public TodoList(){
    }

    public TodoList(String name, boolean visibleForOthers, User owner, WorkGroup workGroup){
        this.name = name;
        this.owner = owner;
        this.workGroup = workGroup;
        this.createdAt = new Date();
        this.visibleForOthers = visibleForOthers;

        this.tasks = new ArrayList<>();
    }

    //Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean isVisibleForOthers() {
        return visibleForOthers;
    }

    public void setVisibleForOthers(boolean visibleForOthers) {
        this.visibleForOthers = visibleForOthers;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public User getOwner() { return owner; }

    public WorkGroup getWorkGroup() { return workGroup; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
