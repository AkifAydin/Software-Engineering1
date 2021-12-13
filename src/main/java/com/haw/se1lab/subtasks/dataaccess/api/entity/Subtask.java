package com.haw.se1lab.subtasks.dataaccess.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Represents a Subtask of a Task. user can create Subtasks to specify there Tasks.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */


@Entity // marks this class as an entity
// default table name: SUBTASK
public class Subtask {
    //Attribute

    @Id // the Subtasks unique primary key in the database
    @GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
    private Long id;

    private String toDo;
    private boolean finished;


    /* ---- Constructors ---- */

    // default constructor (required by Hibernate)
    Subtask(){
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
