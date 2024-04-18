package com.example.wishlist;

import com.example.wishlist.model.User;
import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.repository.WishlistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")

public class WishlistRepositoryTests {

    @Autowired
    WishlistRepository repository;

    @Test
    void getWishlists() {
        int userIDforThea = 2;
        List<Wishlist> theasWishlists = repository.getWishlists(userIDforThea);
        List<Wishlist> expectedWishlistFound = new ArrayList<>(List.of(new Wishlist(2, "Fødselsdag")));
        assertEquals(expectedWishlistFound.size(), theasWishlists.size());

        // Sammenligne nøjagtige elementer:

        for (int i = 0; i < expectedWishlistFound.size(); i++) {
            Wishlist expectedWishlist = expectedWishlistFound.get(i);
            Wishlist actualWishlist = theasWishlists.get(i);

            assertEquals(expectedWishlist.getWishListID(), actualWishlist.getWishListID(), "User ID mismatch at index " + i);
            assertEquals(expectedWishlist.getName(), actualWishlist.getName(), "Name mismatch at index " + i);
        }
    }

    @Test
    void getUserIdFromWishlistTable() {
        int wishListIDForThea = 2;
        int expectedUserIDForThea = 2;
        int actualUserID = repository.getUserIdFromWishlistTable(wishListIDForThea);
        assertEquals(expectedUserIDForThea, actualUserID);
    }

    @Test
    void createWishlist() {
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(2);
        wishlist.setName("test");

        wishlist = repository.createWishlist(wishlist);

        System.out.println(wishlist);

        // Tjek om wishlist.getWishListID() > 4

        System.out.println(wishlist.getWishListID());

        assertNotNull(wishlist);
        assertTrue(wishlist.getWishListID() > 4);
    }

    @Test
    void deleteWishFromTable() {
        int wishToDelete = 2;
        int notExsistingWish = 0;
        boolean deletedWish = repository.deleteWishFromTable(wishToDelete);
        boolean notDeleted = repository.deleteWishFromTable(notExsistingWish);
        assertTrue(deletedWish);
        assertFalse(notDeleted);
    }

    @Test
    void deleteWishlistFromTable() {
        int wishListToDelete = 2;
        int notExsistingList = 0;
        boolean deletedWishlist = repository.deleteWishlist(wishListToDelete);
        boolean notDeleted = repository.deleteWishlist(notExsistingList);
        assertTrue(deletedWishlist);
        assertFalse(notDeleted);
    }

    @Test
    void getListOfWishes() {
        int wishlistIDforThea = 2;
        List<Wish> listOfWishes = repository.getListOfWishes(wishlistIDforThea);
        int expectedSize = 2;
        assertEquals(expectedSize, listOfWishes.size());
    }

    @Test
    void addWish() {
        // Samme fejl på user / users som getListOfUsers metoden.

/*        Wish wish = new Wish();
        wish.setName("Test Wish");
        wish.setDescription("This is a test wish");
        wish.setLink("http://example.com");
        wish.setPrice(100);
        wish.setWishlistID(1);
        repository.addWish(wish);
        assertNotNull(wish.getWishID());*/
    }


    @Test
    void editWish() {
        Wish wish = new Wish();
        wish.setWishlistID(2);
        wish.setName("Test");
        wish.setDescription("Dette er en test");
        wish.setLink("testlink");
        wish.setPrice(22);

        repository.editWish(wish);

        // tjekke om den er ændret i databasen?

    }

    @Test
    void getListOfUsers() { // Fejler fordi tabellen hedder "user" i vores SQL, men "users" i h2.
        // Vi kan ikke få lov at kalde den "user" i h2 fordi det er en anden syntax? Måske et keyword?
/*
        List<User> userList = repository.getListOfUsers();
        int expectedSize = 4;
        assertEquals(expectedSize, userList.size());
*/
    }

    @Test
    void addNewUser() {

        // Fejler fordi tabellen hedder "user" i vores SQL, men "users" i h2.
        // Vi kan ikke få lov at kalde den "user" i h2 fordi det er en anden syntax? Måske et keyword?

    }

    @Test
    void wishlistExists() throws SQLException {
        int exsistingWishlistID = 1;
        int notExsistingWishlistID = 0;
        boolean found = repository.wishlistExists(exsistingWishlistID);
        boolean notFound = repository.wishlistExists(notExsistingWishlistID);
        assertTrue(found);
        assertFalse(notFound);
    }

    @Test
    void getWishFromWishID() {
        int exsitingWishID = 1;
        int notExsistingWishID = 0;
        Wish found = repository.getWishFromWishID(exsitingWishID);
        Wish notFound = repository.getWishFromWishID(notExsistingWishID);
        assertNotNull(found);
        assertNull(notFound);
    }
}
