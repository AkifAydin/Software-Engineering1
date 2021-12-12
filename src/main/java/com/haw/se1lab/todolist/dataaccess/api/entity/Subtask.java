package com.haw.se1lab.todolist.dataaccess.api.entity;

import javax.persistence.Entity;

@Entity // marks this class as an entity
// default table name: Subtask
public class Subtask {
    //Attribute
    private String toDo;
    private boolean finished;

    //Konstruktor
    public Subtask(String toDo, boolean finished) {
        this.toDo = toDo;
        this.finished = finished;
    }

    //Getters
    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
