package com.example.wishlist.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {

    private int id;
    private int userId;
    private String name;
    private List<Wish> wishList;
    private int userid;



    public Wishlist(int id, int userId, String name, List<Wish> wishList) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.wishList = wishList;

    }

    public Wishlist(int userid) {
        this.userid=userid;
    }

    public Wishlist() {

    }


    public Wishlist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Wishlist(int userId,int id,String name){
        this.userId = userId;
        this.name = name;
        this.id = id;
    }


    public void addWish(Wish wish){
        if (wishList == null) {
            wishList = new ArrayList<>();
        }
        wishList.add(wish);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wish> getWishList() {
        return wishList;
    }

    public void setWishList(List<Wish> wishList) {
        this.wishList = wishList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }


}
