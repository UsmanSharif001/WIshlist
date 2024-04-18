package com.example.wishlist.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {

    private int wishListID;
    private int userId;
    private String name;
    private List<Wish> wishList;
    private int userid;



    public Wishlist(int wishListID, int userId, String name, List<Wish> wishList) {
        this.userId = userId;
        this.wishListID = wishListID;
        this.name = name;
        this.wishList = wishList;

    }

    public Wishlist(int userid) {
        this.userid=userid;
    }

    public Wishlist() {

    }


    public Wishlist(int wishListID, String name) {
        this.wishListID = wishListID;
        this.name = name;
    }

    public Wishlist(int userId, int wishListID, String name){
        this.userId = userId;
        this.name = name;
        this.wishListID = wishListID;
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

    public int getWishListID() {
        return wishListID;
    }

    public void setWishListID(int wishListID) {
        this.wishListID = wishListID;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "wishListID=" + wishListID +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", wishList=" + wishList +
                ", userid=" + userid +
                '}';
    }
}
