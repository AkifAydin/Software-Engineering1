package com.haw.se1lab;

import java.util.Date;

public class TodoList {
    //Attribute
    private String listTitle;
    private final Date createdAt;
    private boolean visibleForOthers;

    //Konstruktor
    public TodoList(String listTitle, boolean visibleForOthers){
        this.listTitle = listTitle;
        this.createdAt = new Date();
        this.visibleForOthers = visibleForOthers;
    }

    //Getters
    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
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
}
