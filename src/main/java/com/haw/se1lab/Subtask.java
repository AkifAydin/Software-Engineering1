package com.haw.se1lab;

public class Subtask {
    //Attribute
    private final String subtask;
    private final boolean subtaskFinished;

    //Konstruktor
    public Subtask(String subtask, boolean subtaskFinished) {
        this.subtask = subtask;
        this.subtaskFinished = subtaskFinished;
    }

    //Getters
    public String getSubtask() {
        return subtask;
    }

    public boolean isSubtaskFinished() {
        return subtaskFinished;
    }
}
