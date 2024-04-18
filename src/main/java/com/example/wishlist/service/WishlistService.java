package com.example.wishlist.service;


import com.example.wishlist.model.Wish;
import com.example.wishlist.model.User;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.model.WishlistException;
import com.example.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private WishlistRepository repository;

    public WishlistService(WishlistRepository repository) {
        this.repository = repository;
    }

    public List<User> getListOfUsers() {
        return repository.getListOfUsers();
    }

    public void addNewUser(User newUser) {
        repository.addNewUser(newUser);
    }

    public List<Wishlist> getWishlists(int userId) {
        return repository.getWishlists(userId);
    }


    public Wishlist createWishlist(Wishlist wishlist) {
        return repository.createWishlist(wishlist);
    }

    public boolean deleteWishlist(int wishlistId) {
        return repository.deleteWishlist(wishlistId);
    }

    public int getUserIdFromWishlist(int wishlistId) {
        return repository.getUserIdFromWishlistTable(wishlistId);
    }

    public List<Wish> getListofWishes(int wishlistid) {
        return repository.getListOfWishes(wishlistid);
    }

    public void addWish(Wish wish) {
        repository.addWish(wish);
    }


    public void updateWish(Wish wish) {
        repository.editWish(wish);
    }


    public void deleteWish(int wishID) {
        repository.deleteWish(wishID);
    }

    public Wish getWishFromWishID(int wishid) {
        return repository.getWishFromWishID(wishid);
    }

}
