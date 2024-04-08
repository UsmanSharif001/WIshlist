package com.example.wishlist.service;


import com.example.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

private WishlistRepository repository;

public WishlistService(WishlistRepository repository) {
    this.repository = repository;
}
}
