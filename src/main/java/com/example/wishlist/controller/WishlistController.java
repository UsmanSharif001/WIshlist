package com.example.wishlist.controller;

import com.example.wishlist.model.User;
import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String saveUser(@ModelAttribute User newUser) {
        wishlistService.addNewUser(newUser);
        return "redirect:/";
    }

    @GetMapping("/{userid}/wishlists")
    public String getWishlist(@PathVariable int userid, Model model) {
        List<Wishlist> wishlists = wishlistService.getWishlists(userid);
        model.addAttribute("wishlists", wishlists);
        return "wishlists";
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
        return "redirect:/" + userid + "wishlists";
    }

    @GetMapping("{wishlistid}/wishes")
    public String getWishes(@PathVariable int wishlistid, Model model) {
    List<Wish> listOfWishes = wishlistService.getListofWishes(wishlistid);
    int userID = wishlistService.getUserIdFromWishlist(wishlistid);
    int wishid = listOfWishes.get(0).getWishID();
    model.addAttribute("listOfWishes", listOfWishes);
    model.addAttribute("userID", userID);
    model.addAttribute("wishid", wishid);
        return "wishes";
    }

    @GetMapping("/{wishlistid}/addwish")
    public String addWish(@PathVariable int wishlistid, Model model) {
        model.addAttribute("wish", new Wish());
        model.addAttribute("wishlistid", wishlistid);
        return "addWish";
    }

    @PostMapping("/{wishlistid}/savewish")
    public String saveWish(@PathVariable int wishlistid, @ModelAttribute Wish newWish, Model model) {
        newWish.setWishlistID(wishlistid);
        model.addAttribute("wishlistid", wishlistid);
        wishlistService.addWish(newWish);
        return "redirect:/" + wishlistid + "/wishes";
    }

    @GetMapping("/{wishlistid}/{wishid}/editwish")
    public String editWish() {
        return "editwish";
    }

    @PostMapping("/{wishlistid}/updatewish")
    public String updateWish(@PathVariable int wishlistid, Model model) {
        model.addAttribute("wishlistid", wishlistid);
        return "redirect:/" + wishlistid + "/wishes";
    }

    @GetMapping("/{wishlistid}/{wishid}/deletewish")
    public String deleteWish(@PathVariable int wishlistid, @PathVariable("wishid") int wishid, Model model) {
        wishlistService.deleteWish(wishid);
        model.addAttribute("wishlistid", wishlistid);
        return "redirect:/" + wishlistid + "/wishes";
    }
}