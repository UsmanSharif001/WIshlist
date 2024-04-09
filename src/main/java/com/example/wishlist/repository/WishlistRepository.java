package com.example.wishlist.repository;


import com.example.wishlist.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
