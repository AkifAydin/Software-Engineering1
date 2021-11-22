package com.haw.se1lab;

import java.util.ArrayList;
import java.util.List;

public class User {

    //Attribute
    private final UserID id;  //UserId
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private final List<TodoList> todoLists;

    //Konstruktor
    public User(UserID id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        todoLists = new ArrayList<>();
    }

    //Getter and Setter
    public UserID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TodoList> getTodoLists() {
        return todoLists;
    }
}