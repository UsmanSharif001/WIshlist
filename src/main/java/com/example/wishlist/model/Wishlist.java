package com.example.wishlist.model;

import java.util.List;

public class Wishlist {

    private String name;
    private List<Wish> wishList;

    public Wishlist(String name, List<Wish> wishList) {
        this.name = name;
        this.wishList = wishList;
    }

    public Wishlist() {

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
