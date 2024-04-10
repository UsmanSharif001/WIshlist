package com.example.wishlist.repository;


import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.util.ConnectionManager;
import com.example.wishlist.model.User;
import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wishlist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;


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
    public List<Wishlist> createAndSaveWishlist(int userID, String wishlistName) {
        List<Wishlist> wishlists = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, username, pwd)) {
            String insertSQL = "INSERT INTO Wishlist (UserID, Name) VALUES (?, ?);";
            String selectSQL = "SELECT WishlistID, UserID, Name FROM Wishlist WHERE UserID = ?;";

            try (PreparedStatement psInsert = con.prepareStatement(insertSQL)) {
                psInsert.setInt(1, userID);
                psInsert.setString(2, wishlistName);
                psInsert.executeUpdate();
            }

            try (PreparedStatement psSelect = con.prepareStatement(selectSQL)) {
                psSelect.setInt(1, userID);
                try (ResultSet rs = psSelect.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("Wishid");
                        String name = rs.getString("Name");
                        Wishlist wishlist = new Wishlist(id,name, new ArrayList<>());
                        wishlists.add(wishlist);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get wishlists for user: " + userID, e);
        }
        return wishlists;
    }

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
    //Metode der opretter og gemmer en wishlist i databasen US

    //Metode der sletter en liste på UserID

    //Metode der getWishes??? (burde den hedde get wish?)

    //Metode der opretter og gemmer et ønske i databasen - Mu
    public void addWish(Wish wish){
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)){
            String SQL = "INSERT INTO wish(Name, Description, Link, Price, WishlistID) VALUES( ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, wish.getName());
            ps.setString(2, wish.getDescription());
            ps.setString(3, wish.getLink());
            ps.setInt(4, wish.getPrice());
            ps.setInt(5, wish.getWishlistID());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metode der update/edit og gemmer et ønske i databasen

    //Metode der sletter ønske på wishID

    public List<User> getListOfUsers() {
        List<User> userList = new ArrayList<>();

        User user;
        try {
            Connection con = DriverManager.getConnection(db_url, username, pwd);
            String sqlSelectAllUsers = "SELECT * FROM wishlist.user;";
            PreparedStatement ps = con.prepareStatement(sqlSelectAllUsers);
            ResultSet usersResultSet = ps.executeQuery();

            while (usersResultSet.next()) {
                int userID = usersResultSet.getInt("Userid");
                String name = usersResultSet.getString("Name");
                user = new User(userID, name);
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public int getUserID(String userName) {
        int userID = 0;

        try {
            Connection con = DriverManager.getConnection(db_url, userName, pwd);
            String sqlGetUserID = "SELECT Userid FROM wishlist.user WHERE Name = ?;";
            PreparedStatement ps = con.prepareStatement(sqlGetUserID);

            ps.setInt(1, userID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userID;
    }

    public void addNewUser(User newUser) {

        try (Connection con = DriverManager.getConnection(db_url, username, pwd)) {
            String insertSql = "INSERT INTO wishlist.user (Name) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, newUser.getUserName());

            pstmt.executeUpdate();

            // set attractionID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            int userID = -1;
            if (generatedKeys.next()) {
                userID = generatedKeys.getInt(1);
                newUser.setId(userID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
