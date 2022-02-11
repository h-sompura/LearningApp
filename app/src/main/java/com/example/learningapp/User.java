package com.example.learningapp;

import java.util.ArrayList;

public class User {

    private String username;
    private String password;


    //constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
