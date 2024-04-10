package com.example.wishlist.repository;


import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.util.ConnectionManager;
import com.mysql.cj.protocol.Resultset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Wishlist> getWishlists(int userId){
        List<Wishlist> wishlistList = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection(db_url,username,pwd);
        String SQL = """
                SELECT name,wishlistid
                FROM wishlist
                WHERE userid = ?
                """;

        try(PreparedStatement ps = connection.prepareStatement(SQL)){
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Wishlist wishlist = new Wishlist(
                        rs.getInt("Wishlistid"),
                        rs.getString("Name")
                );
                wishlistList.add(wishlist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return wishlistList;
    }


    //Metode der opretter og gemmer en wishlist i databasen

    //Metode der sletter en liste på UserID

    //Metode der getWishes??? (burde den hedde get wish?)

    //Metode der opretter og gemmer et ønske i databasen

    //Metode der update/edit og gemmer et ønske i databasen

    //Metode der sletter ønske på wishID

}
