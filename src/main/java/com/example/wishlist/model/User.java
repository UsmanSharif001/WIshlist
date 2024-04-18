package com.example.wishlist.model;

public class User {

    private String userName;
    private int userID;

    public User(int userID, String name) {
        this.userID = userID;
        this.userName = name;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int userID) {
        this.userID = userID;
    }
}
