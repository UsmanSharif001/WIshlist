package com.example.wishlist.model;

public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
