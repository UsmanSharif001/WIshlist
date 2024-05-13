package com.example.wishlist.model;


public class Wishlist {

    private int id;
    private int userId;
    private String name;


    public Wishlist() {

    }


    public Wishlist(int id, int userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
