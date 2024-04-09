package com.example.wishlist.controller;

import com.example.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class WishlistController {
    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    private String getUser() {
        return "userlist";
    }

    @GetMapping("/adduser")
    private String addUser() {
        return "adduser";
    }

    @PostMapping("/save")
    private String saveUser() {
        return "redirect:/userlist";
    }

    @GetMapping("/{userid}/wishlist")
    private String getWishlist() {
        return "wishlist";
    }

    @GetMapping("/{userid}/addwishlist")
    private String addWishlist() {
        return "addWishlist";
    }

    @PostMapping("/savewishlist")
    private String saveWishlist() {
        return "redirect:/wishlist";
    }

    @GetMapping("/{userid}/deletewishlist")
    private String deleteWishlist() {
        return "redirect:/wishlist";
    }

    @GetMapping("/{wishlistid}/wishes")
    private String getWishes() {
        return "wishes";
    }

    @GetMapping("/{wishlistid]/addwish")
    private String addWish() {
        return "addWish";
    }

    @PostMapping("/savewish")
    private String saveWish() {
        return "redirect/wishes";
    }

    @GetMapping("/{wishid}/editwish")
    private String editWish() {
        return "/editwish";
    }

    @PostMapping("/updatewish")
    private String updateWish() {
        return "redirect/wishes";
    }

    @GetMapping("/{wishid}/deletewish")
    private String deleteWish() {
        return "redirect/wishes";
    }
}