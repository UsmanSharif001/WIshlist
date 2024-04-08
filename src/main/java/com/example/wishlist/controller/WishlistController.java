package com.example.wishlist.controller;


import com.example.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class WishlistController {

private WishlistService wishlistService;

public WishlistController(WishlistService wishlistService) {
    this.wishlistService = wishlistService;
}


}
