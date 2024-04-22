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


    public List<User> getListOfUsers() {
        List<User> userList = new ArrayList<>();
        User user;

        Connection con = ConnectionManager.getConnection(db_url, username, pwd);

        String sqlSelectAllUsers = "SELECT * FROM user;";

        try (PreparedStatement ps = con.prepareStatement(sqlSelectAllUsers)) {
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

    public void addNewUser(User newUser) {

        Connection con = ConnectionManager.getConnection(db_url, username, pwd);
        String insertSql = "INSERT INTO wishlist.user (Name) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

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


    public Wishlist createWishlist(Wishlist wishlist) {
        Connection con = ConnectionManager.getConnection(db_url, username, pwd);
        String insertSQL = "INSERT INTO Wishlist (Userid, Name) VALUES (?,?)";

        try (PreparedStatement psInsert = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
            psInsert.setInt(1, wishlist.getUserId());
            psInsert.setString(2, wishlist.getName());
            int rows = psInsert.executeUpdate();
            ResultSet rs = psInsert.getGeneratedKeys();
            if (rs.next()) {
                int wishlistID = rs.getInt(1);
                wishlist.setId(wishlistID);
                return wishlist;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create wishlist");
        }
        return null;
    }

    public boolean deleteWishlist(int wishlistID) {
        return deleteWishFromTable(wishlistID) && deleteWishlistFromTable(wishlistID);
    }

    //hjælpe metode til deleteWishlist
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
        return rows >= 1;
    }

    //hjælpe metode til deleteWishlist
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

    public void addWish(Wish wish) {
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            if (!wishlistExists(wish.getWishlistID())) {
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

    public void editWish(Wish wish) {
        Connection con = ConnectionManager.getConnection(db_url, username, pwd);
        String SQL = "UPDATE Wish SET Name = ?, Description = ?, " +
                "Link = ?, Price = ? WHERE Wishid = ?;";
        try (PreparedStatement ps = con.prepareStatement(SQL)) {

            ps.setString(1, wish.getName());
            ps.setString(2, wish.getDescription());
            ps.setString(3, wish.getLink());
            ps.setInt(4, wish.getPrice());
            ps.setInt(5, wish.getWishID());

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Failed to edit wish: " + wish.getName() + e);
        }
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

            if (rs.next()) {
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


    //hjælpe metode
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

    public boolean wishlistExists(int wishlistID) throws SQLException {
        Connection connection = DriverManager.getConnection(db_url, username, pwd);
        String SQL = "SELECT Wishlistid FROM wishlist WHERE Wishlistid = ?";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1, wishlistID);
        ResultSet rs = ps.executeQuery();
        return rs.next(); // Returnerer true hvis wishlist med et givent ID findes
    }


}
