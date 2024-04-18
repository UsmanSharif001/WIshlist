package com.example.wishlist.model;


public class Wishlist {

    private int id;
    private int userId;
    private String name;


    public Wishlist() {

    }

    public Wishlist(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Wishlist(int userId, int id, String name) {
        this.userId = userId;
        this.id = id;
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
