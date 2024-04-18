package com.example.wishlist;

import com.example.wishlist.model.Wishlist;
import com.example.wishlist.repository.WishlistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("h2")

public class RepositoryTests {

    @Autowired
    WishlistRepository repository;

    @Test
    void getWishlists() {
        int userIDforThea = 2;
        List<Wishlist> theasWishlists = repository.getWishlists(userIDforThea);
        List<Wishlist> expectedWishlistFound = new ArrayList<>(List.of(new Wishlist(2, "Fødselsdag")));
        assertEquals(expectedWishlistFound.size(), theasWishlists.size());

        // Sammenligne nøjagtige elementer:

//        for (int i = 0; i < expectedWishlistFound.size(); i++) {
//            Wishlist expectedWishlist = expectedWishlistFound.get(i);
//            Wishlist actualWishlist = theasWishlists.get(i);
//
//            assertEquals(expectedWishlist.getId(), actualWishlist.getId(), "User ID mismatch at index " + i);
//            assertEquals(expectedWishlist.getName(), actualWishlist.getName(), "Name mismatch at index " + i);
//        }
    }

    @Test
    void getUserIdFromWishlistTable(){
        int wishListIDForThea = 2;
        int expectedUserIDForThea = 2;
        int actualUserID = repository.getUserIdFromWishlistTable(wishListIDForThea);
        assertEquals(expectedUserIDForThea, actualUserID);
    }

    @Test
    void createWishlist(){
        Wishlist wishlist = repository.createWishlist(new Wishlist(4,"Julegaver"));
        assertTrue(wishlist.getId()>4);
    }

}
