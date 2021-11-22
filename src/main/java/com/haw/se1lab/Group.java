package com.haw.se1lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group {
    //Attribute
    private final GroupIDTyp id;
    private String name;
    private boolean publicVisible;
    private final Date createdAt;

    private final List<TodoList> todoLists;
    private final List<User> users;

    //Konstruktor
    public Group(GroupIDTyp id, String name, boolean publicVisible) {
        this.id = id;
        this.name = name;

        this.publicVisible = publicVisible;
        this.createdAt = new Date();
        this.todoLists = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    //getter and setter
    public GroupIDTyp getId() {
        return id;
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

    public List<TodoList> getTodoLists() {
        return todoLists;
    }

    public List<User> getUsers() {
        return users;
    }
}
