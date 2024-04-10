package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wish;
import com.example.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class WishlistController {
    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    private String getUser(Model model) {
        List<User> userList = wishlistService.getListOfUsers();
        model.addAttribute("users", userList);
        return "userlist";
    }

    @GetMapping("/adduser")
    private String addUser(Model model) {
        String name = "";
        model.addAttribute("user", new User());
        model.addAttribute("name", name);
        return "adduser";
    }

    @PostMapping("/save")
    private String saveUser(@ModelAttribute User newUser) {
        wishlistService.addNewUser(newUser);
        return "redirect:/";
    }

    @GetMapping("/{userid}/wishlist")
    private String getWishlist() {
        return "wishlist";
    }

    @GetMapping("/{userid}/addwishlist")
    private String addWishlist() {
        return "addwishlist";
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

    @GetMapping("/{wishlistid}/addwish")
    public String addWish(@PathVariable("wishlistid") int wishlistid, Model model) {
        model.addAttribute("wish", new Wish());
        model.addAttribute("wishlistid", wishlistid); //OBS: måske den fejler på int/string
        return "addWish";
    }

    @PostMapping("/savewish")
    public String saveWish(@ModelAttribute Wish newWish) {
        wishlistService.addWish(newWish);
        return "redirect/wishes";
    }

    @GetMapping("/{wishid}/editwish")
    private String editWish() {
        return "editwish";
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