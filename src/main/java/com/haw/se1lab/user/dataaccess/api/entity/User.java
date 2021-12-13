package com.haw.se1lab.user.dataaccess.api.entity;

import com.haw.se1lab.user.common.api.datatype.UserIDTyp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents an user of the application. user can create TodoList with Tasks and Subtasks.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */
@Entity // marks this class as an entity
// default table name: USER
public class User {

    /* ---- Attribute ---- */

    @Id // the Users unique primary key in the database
    @GeneratedValue // lets Hibernate take care of assigning an ID to new database entries
    private Long id;

    @Embedded // causes this field's attributes to be stored in columns within this entity's table
    @NotNull // adds a constraint for this field (checked by Hibernate during saving)
    @Column(unique = true) // adds a uniqueness constraint for this field's column (business key column)
    private UserIDTyp userId;  //UserId

    private String firstName;
    private String lastName;
    private String email;
    private String password;


    /* ---- Constructors ---- */
    // default constructor (required by Hibernate)
    User() {
    }

    public User(UserIDTyp userId, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    //Getter and Setter
    public UserIDTyp getUserId() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}