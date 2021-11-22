package com.haw.se1lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoList {
    //Attribute
    private final String name;
    private final Date createdAt;
    private final boolean visibleForOthers;

    private final List<Task> tasks;

    //Konstruktor
    public TodoList(String name, boolean visibleForOthers){
        this.name = name;
        this.createdAt = new Date();
        this.visibleForOthers = visibleForOthers;

        this.tasks = new ArrayList<>();
    }

    //Getters
    public String getName() {
        return name;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public boolean isVisibleForOthers() {
        return visibleForOthers;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
