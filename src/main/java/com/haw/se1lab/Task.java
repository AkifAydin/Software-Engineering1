package com.haw.se1lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    //Attribute
    private String toDo;
    private Date todoFrom;
    private Date toDoTo;
    private ColourTyp colourTyp;
    private boolean finished;

    private final List<Subtask> subtasks;

    //Konstruktor
    public Task(String toDo, Date todoFrom, Date toDoTo, ColourTyp colourTyp){
        this.toDo = toDo;
        this.todoFrom = todoFrom;
        this.toDoTo = toDoTo;
        this.colourTyp = colourTyp;
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

    public ColourTyp getColour() {
        return colourTyp;
    }

    public void setColour(ColourTyp colourTyp) {
        this.colourTyp = colourTyp;
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
}
