package com.haw.se1lab;

import java.util.ArrayList;
import java.util.List;

public class User {

    //Attribute
    private final UserID id;  //UserId
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final List<TodoList> todoLists;

    //Konstruktor
    public User(UserID id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.todoLists = new ArrayList<>();
    }

    //Getter and Setter
    public UserID getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public List<TodoList> getTodoLists() {
        return todoLists;
    }
}