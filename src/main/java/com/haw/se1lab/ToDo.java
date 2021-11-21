package com.haw.se1lab;

import java.util.Date;

public class ToDo {
    //Attribute
    private final String toDo;
    private final Date todoFrom;
    private final Date toDoTo;
    private final Colour colour;

    //Konstruktor
    public ToDo(String toDo, Date todoFrom, Date toDoTo, Colour colour){
        this.toDo = toDo;
        this.todoFrom = todoFrom;
        this.toDoTo = toDoTo;
        this.colour = colour;
    }


    //Getters
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
}
