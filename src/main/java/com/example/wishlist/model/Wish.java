package com.example.wishlist.model;

public class Wish {

    private String name;
    private String description;
    private String link;
    private int price;
    private int wishlistID;

    public Wish(String name, String description, String link, int price){
        this.name = name;
        this.description = description;
        this.link = link;
        this.price = price;
    }
    public Wish(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }
}
