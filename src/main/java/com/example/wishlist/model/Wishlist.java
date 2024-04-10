package com.example.wishlist.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {

    private int id;
    private String name;
    private List<Wish> wishList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wishlist(int id, String name, List<Wish> wishList) {
        this.id = id;
        this.name = name;
        this.wishList = wishList;
    }

    public Wishlist() {

    }

    public Wishlist(int id, String name) {
        this.id = id;
        this.name = name;
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


}
