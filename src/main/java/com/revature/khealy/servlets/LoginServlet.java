package com.revature.khealy.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.khealy.Movie;
import com.revature.khealy.User;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    Connection connection;

    public LoginServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    /**
     * Gives values from json string to movie object then posts on sql server.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("doing post!!!");
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(req.getInputStream(), User.class);
        try {
            System.err.println("Inserting into DB.");
            PreparedStatement stmt = connection.prepareStatement("insert into user values (?,?,?,?,?)");
            stmt.setInt(user.setUserID(150);
            stmt.setInt(1,user.getUserID());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(user.setImdb_id("X");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to insert: " + e.getMessage());
        }
    }
}







