package com.haw.se1lab.user.dataaccess.api.entity;

import com.haw.se1lab.user.common.api.datatype.UserIDTyp;

import javax.persistence.Entity;

/**
 * Represents a user of the application. user can create TodoList with Tasks and Subtasks.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */

@Entity // marks this class as an entity
// default table name: USER
public class User {

    //Attribute
    private final UserIDTyp userId;  //UserId
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    //Konstruktor
    public User(UserIDTyp userId, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    //Getter and Setter
    public UserIDTyp getId() {
        return userId;
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

}