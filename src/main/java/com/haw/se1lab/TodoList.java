package com.haw.se1lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoList {
    //Attribute
    private String name;
    private final Date createdAt;
    private boolean visibleForOthers;

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
}
