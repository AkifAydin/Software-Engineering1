package com.haw.se1lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    //Attribute
    private final String toDo;
    private final Date todoFrom;
    private final Date toDoTo;
    private final Colour colour;
    private boolean finished;

    private final List<Subtask> subtasks;

    //Konstruktor
    public Task(String toDo, Date todoFrom, Date toDoTo, Colour colour){
        this.toDo = toDo;
        this.todoFrom = todoFrom;
        this.toDoTo = toDoTo;
        this.colour = colour;
        this.finished = false;
        this.subtasks = new ArrayList<>();
    }


    //Getter and Setter
    public String getToDo() {
        return toDo;
    }
    public Date getTodoFrom() {
        return todoFrom;
    }
    public Date getToDoTo() {
        return toDoTo;
    }
    public Colour getColour() {
        return colour;
    }
    public boolean isFinished() {
        return finished;
    }


    public List<Subtask> getSubtasks() {
        return subtasks;
    }
}
