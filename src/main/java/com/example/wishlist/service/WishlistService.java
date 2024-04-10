package com.example.wishlist.service;


import com.example.wishlist.model.Wish;
import com.example.wishlist.model.User;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

private WishlistRepository repository;

public WishlistService(WishlistRepository repository) {
    this.repository = repository;
}

public void addWish(Wish wish){
    repository.addWish(wish);
}
    public List<User> getListOfUsers() {
    return repository.getListOfUsers();
    }

    public int getUserID(String name) {
    return repository.getUserID(name);
    }

    public void addNewUser(User newUser) {
        repository.addNewUser(newUser);
    }

public List<Wishlist> getWishlists(int userId){
   return repository.getWishlists(userId);
}

public boolean deleteWishlist(int wishlistId){
    return repository.deleteWishlist(wishlistId);
}

public void updateWish(int wishid, String newName, String newDescription, String newLink, int newPrice, int wishlistid) {
    repository.editWish(wishid, newName, newDescription, newLink, newPrice, wishlistid);
}
public List<Wishlist> createWishlist(int userid, String wishlistName) {
    return repository.createWishlist(userid, wishlistName);
}

}
