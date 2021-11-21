package com.haw.se1lab;

public class User {

    //Attribute
    private final UserId userid;  //UserId
    private final String firstName;
    private final String lastName;

    //Konstruktor
    public User(UserId userid, String firstName, String lastName) {
        this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getters
    public UserId getUserid() {
        return userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}