package com.example.wishlist.repository;


import com.example.wishlist.model.Wish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class WishlistRepository {

    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String pwd;

    //Metode der henter liste af users fra databasen - Thea

    //Metode der opretter og gemmer en user i databasen

    //Metode der henter wishlist på userID - Nikolaj

    //Metode der opretter og gemmer en wishlist i databasen US

    //Metode der sletter en liste på UserID

    //Metode der getWishes??? (burde den hedde get wish?)

    //Metode der opretter og gemmer et ønske i databasen - Mu
    public void addWish(Wish wish){
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)){
            String SQL = "INSERT INTO wish(Name, Description, Link, Price, WishlistID) VALUES( ?, ?, ?, ?);";
            //Hvordan får jeg fat i wishlistID?
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, wish.getName());
            ps.setString(2, wish.getDescription());
            ps.setString(3, wish.getLink());
            ps.setInt(4, wish.getPrice());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metode der update/edit og gemmer et ønske i databasen

    //Metode der sletter ønske på wishID

}
