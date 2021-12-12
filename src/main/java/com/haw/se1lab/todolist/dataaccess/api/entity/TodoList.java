package com.haw.se1lab.todolist.dataaccess.api.entity;


import com.haw.se1lab.group.dataaccess.api.entity.Group;
import org.apache.catalina.User;  //KomponentenSchnittstelle

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // marks this class as an entity
// default table name: TodoList
public class TodoList {
    //Attribute
    private String name;
    private final Date createdAt;
    private final User owner;
    private boolean visibleForOthers;

    private final List<Task> tasks;
    private final Group group;

    //Konstruktor
    public TodoList(String name, boolean visibleForOthers, User owner, Group group){
        this.name = name;
        this.owner = owner;
        this.group = group;
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

    public Group getGroup() { return group; }

}
