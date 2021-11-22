package com.haw.se1lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group {
    //Attribute
    private final GroupID id;
    private final String name;
    private final boolean publicVisible;
    private final Date createdAt;

    private final List<Subtask> todoLists;
    private final List<User> users;

    //Konstruktor
    public Group(GroupID id, String name, boolean publicVisible) {
        this.id = id;
        this.name = name;

        this.publicVisible = publicVisible;
        this.createdAt = new Date();
        this.todoLists = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    //getter and setter
    public GroupID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isPublicVisible() {
        return publicVisible;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public List<Subtask> getTodoLists() {
        return todoLists;
    }

    public List<User> getUsers() {
        return users;
    }
}
