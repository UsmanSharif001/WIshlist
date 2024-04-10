package com.example.wishlist.service;


import com.example.wishlist.model.Wish;
import com.example.wishlist.model.User;
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
}
