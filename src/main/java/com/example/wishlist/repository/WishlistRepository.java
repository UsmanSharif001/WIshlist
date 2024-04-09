package com.example.wishlist.repository;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistRepository {

    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String pwd;

    //Metode der henter liste af users fra databasen

    //Metode der opretter og gemmer en user i databasen

    //Metode der henter wishlist på userID

    //Metode der opretter og gemmer en wishlist i databasen

    //Metode der sletter en liste på UserID

    //Metode der getWishes??? (burde den hedde get wish?)

    //Metode der opretter og gemmer et ønske i databasen

    //Metode der update/edit og gemmer et ønske i databasen

    //Metode der sletter ønske på wishID


}
