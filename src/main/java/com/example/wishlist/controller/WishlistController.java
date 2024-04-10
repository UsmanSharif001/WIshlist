package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

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
    public String saveUser(@ModelAttribute User newUser) {
        wishlistService.addNewUser(newUser);
        return "redirect:/";
    }

    @GetMapping("/{userid}/wishlist")
    public String getWishlist(@PathVariable int userid, Model model) {
        List<Wishlist> wishlists = wishlistService.getWishlists(userid);
        model.addAttribute("wishlists", wishlists);
        return "wishlist";
    }

    @GetMapping("/{userid}/addwishlist")
    public String addWishlist() {
        return "addwishlist";
    }

    @PostMapping("/savewishlist")
    public String saveWishlist() {
        return "redirect:/wishlist";
    }

    @GetMapping("{userid}/delete/{wishlistid}")
    public String deleteWishlist(@PathVariable int userid,@PathVariable int wishlistid) {
        wishlistService.deleteWishlist(wishlistid);
        return "redirect:/" + userid + "/wishlist";
    }

    @GetMapping("/{wishlistid}/wishes")
    public String getWishes() {
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
    public String editWish() {
        return "editwish";
    }

    @PostMapping("/updatewish")
    public String updateWish() {
        return "redirect/wishes";
    }

    @GetMapping("/{wishid}/deletewish")
    public String deleteWish() {
        return "redirect/wishes";
    }
}