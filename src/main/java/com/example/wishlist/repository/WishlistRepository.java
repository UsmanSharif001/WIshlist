package com.example.wishlist.repository;


import com.example.wishlist.model.Wish;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.util.ConnectionManager;
import com.example.wishlist.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.sql.ResultSet;
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

    //Metode der henter liste af users fra databasen - Thea

    //Metode der opretter og gemmer en user i databasen

    //Metode der henter wishlist på userID - Nikolaj

    //Metode der opretter og gemmer en wishlist i databasen US

    public List<Wishlist> createWishlist(int userid, String wishlistName) {
        List<Wishlist> wishlists = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, username, pwd)) {
            String insertSQL = "INSERT INTO Wishlist (UserID, Name) VALUES (?, ?);";

            try (PreparedStatement psInsert = con.prepareStatement(insertSQL)) {
                psInsert.setInt(1, userid);
                psInsert.setString(2, wishlistName);
                psInsert.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create wishlist for user: " + userid, e);
        }
        return wishlists;
    }

    //Metode der henter wishlist på userID

    public List<Wishlist> getWishlists(int userId) {
        List<Wishlist> wishlistList = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection(db_url, username, pwd);
        String SQL = """
                SELECT name,wishlistid
                FROM wishlist
                WHERE userid = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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

    public boolean deleteWishlist(int wishlistID) {
        return deleteWishFromTable(wishlistID) && deleteWishlistFromTable(wishlistID);
    }

    public boolean deleteWishFromTable(int wishlistId) {
        int rows = 0;
        Connection connection = ConnectionManager.getConnection(db_url, username, pwd);
        String SQL = """
                DELETE FROM wish WHERE wishlistid = ?
                """;
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, wishlistId);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows >= 0;
    }

    public boolean deleteWishlistFromTable(int wishlistID) {
        int rows = 0;
        Connection connection = ConnectionManager.getConnection(db_url, username, pwd);
        String SQL = """
                DELETE FROM wishlist WHERE wishlistid = ?
                """;
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, wishlistID);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows == 1;
    }

    public void deleteWish(int wishID) {
        Connection connection = ConnectionManager.getConnection(db_url, username, pwd);
        String SQL = """
                DELETE FROM wish WHERE wishid = ?
                """;
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, wishID);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metode der getWishes??? (burde den hedde get wish?)

    public List<Wish> getListOfWishes(int wishlistID) {
        List<Wish> listOfWishes = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection(db_url, username, pwd);
        String SQL = """
                SELECT Name,Description,Link,Price,Wishlistid,Wishid 
                FROM wish
                WHERE wishlistid = ?
                """;
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, wishlistID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String link = rs.getString("Link");
                int price = rs.getInt("Price");
                int listid = rs.getInt("Wishlistid");
                int id = rs.getInt("Wishid");
                //   int wishID = rs.getInt("wishid");
                Wish wish = new Wish(name, description, link, price, listid, id);
                listOfWishes.add(wish);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfWishes;
    }

    //Metode der opretter og gemmer et ønske i databasen - Mu
    public void addWish(Wish wish) {
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            if (!wishlistExists(connection, wish.getWishlistID())) {
                throw new IllegalArgumentException("Wishlist med ID: " + wish.getWishlistID() + " findes ikke.");
            }
            String SQL = "INSERT INTO wish(Name, Description, Link, Price, WishlistID) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, wish.getName());
            ps.setString(2, wish.getDescription());
            ps.setString(3, wish.getLink());
            ps.setInt(4, wish.getPrice());
            ps.setInt(5, wish.getWishlistID());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int wishID = generatedKeys.getInt(1);
                    wish.setWishID(wishID);
                } else {
                    throw new SQLException("Kunne ikke finde det genererede wishID.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metode der update/edit og gemmer et ønske i databasen

    public void editWish(Wish wish) {
        try (Connection con = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "UPDATE Wish SET Name = ?, Description ?, " +
                    "Link ?, Price ? WHERE Wishid = ?;";
            try (PreparedStatement ps = con.prepareStatement(SQL)) {
                {
                    ps.setString(1, wish.getName());
                    ps.setString(2, wish.getDescription());
                    ps.setString(3, wish.getLink());
                    ps.setInt(4, wish.getPrice());
                    ps.setInt(5, wish.getWishID());

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        System.out.println("No wish found with given wishid and wishlistid");

                    } else {
                        System.out.println("Wish updated succesfully!");
                    }
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to edit wish: " + wish.getName() + e);
        }
    }

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

            // set userID
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

    public int getUserIdFromWishlistTable(int wishlistId) {
        Connection con = ConnectionManager.getConnection(db_url, username, pwd);
        String SQL = """
                SELECT Userid
                FROM wishlist
                WHERE Wishlistid = ?
                """;
        try (PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, wishlistId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Userid");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private boolean wishlistExists(Connection connection, int wishlistID) throws SQLException {
        String SQL = "SELECT Wishlistid FROM wishlist WHERE Wishlistid = ?";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1, wishlistID);
        ResultSet rs = ps.executeQuery();
        return rs.next(); // Returnerer true hvis wishlist med et givent ID findes
    }

    public Wish getWishFromWishID(int wishid) {
        Wish wishToUpdate;

        Connection con = ConnectionManager.getConnection(db_url, username, pwd);

        String sql = """
                        SELECT Name,
                        Description,
                        Link,
                        Price,
                        Wishlistid
                FROM wish
                WHERE Wishid = ?;
                """;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, wishid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String link = rs.getString("Link");
                int price = rs.getInt("Price");
                int wishlistid = rs.getInt("Wishlistid");
                wishToUpdate = new Wish(name, description, link, price, wishlistid, wishid);

                return wishToUpdate;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
