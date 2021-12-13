package com.haw.se1lab.tasks.dataaccess.api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents Tasks of a TodoList. user can create Tasks to keep track of there tasks.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */

@Entity // marks this class as an entity
// default table name: TASK
public class Task {

    /* ---- Member Fields ---- */

    @Id // the Tasks unique primary key in the database
    @GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
    private Long id;

    private String toDo;

    @NotNull // adds a constraint for this field (checked by Hibernate during saving)
    private Date todoFrom;

    @NotNull // adds a constraint for this field (checked by Hibernate during saving)
    private Date toDoTo;

    @Enumerated(EnumType.STRING) // causes the value of this enum-type field to be stored under the enum value's name
    @NotNull // adds a constraint for this field (checked by Hibernate during saving)
    // default column name: COLOUR


    private boolean finished;

    @OneToMany
    private List<Subtask> subtasks;

    /* ---- Constructors ---- */

    // default constructor (required by Hibernate)
    public Task(){
    }

    public Task(String toDo, Date todoFrom, Date toDoTo){
        this.toDo = toDo;
        this.todoFrom = todoFrom;
        this.toDoTo = toDoTo;

        this.finished = false;
        this.subtasks = new ArrayList<>();
    }


    //Getter and Setter
    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public Date getTodoFrom() {
        return todoFrom;
    }

    public void setTodoFrom(Date todoFrom) {
        this.todoFrom = todoFrom;
    }

    public Date getToDoTo() {
        return toDoTo;
    }

    public void setToDoTo(Date toDoTo) {
        this.toDoTo = toDoTo;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
