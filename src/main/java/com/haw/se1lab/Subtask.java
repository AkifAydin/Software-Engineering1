package com.haw.se1lab;

public class Subtask {
    //Attribute
    private final String toDo;
    private final boolean finished;

    //Konstruktor
    public Subtask(String toDo, boolean finished) {
        this.toDo = toDo;
        this.finished = finished;
    }

    //Getters
    public String getToDo() {
        return toDo;
    }
    public boolean isFinished() {
        return finished;
    }
}
