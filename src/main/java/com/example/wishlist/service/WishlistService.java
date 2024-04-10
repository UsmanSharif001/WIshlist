package com.example.wishlist.service;


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

}
