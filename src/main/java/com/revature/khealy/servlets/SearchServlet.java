package com.revature.khealy.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.khealy.DB.DBRetriver;
import com.revature.khealy.Movie;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchServlet extends HttpServlet {
    Connection connection;
    String imdb_id;

    public SearchServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        DBRetriver dbRetriver = new DBRetriver();
        Movie tempMovie = new Movie();
        ArrayList<Movie> newMovies = new ArrayList<Movie>();
        PreparedStatement stmt = null;

        try {
            System.err.println("Searching for imdb_id...");
            String query = "select * from movie where imdb_id = ?"; //""Select * from movie "
            stmt = connection.prepareStatement(query);
            stmt.setString(1, this.imdb_id);

        } catch (SQLException e) {
            System.err.println("Error searching movies! "+e.getMessage());
        }
        newMovies = dbRetriver.getFromDB(stmt);

        String result = mapper.writeValueAsString(newMovies);
        System.err.println(result);
        resp.setContentType("application/json");
        resp.getWriter().println(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
/*        System.err.println("doing Post of server request...");
        System.err.println("Server request: " + req.toString());
        this.imdb_id = req.toString();*/
        
        ObjectMapper mapper = new ObjectMapper();
        this.imdb_id = mapper.readValue(req.getInputStream(),String.class);

/*
        try {
            System.err.println("Inserting movie into DB.");
            PreparedStatement stmt = connection.prepareStatement("insert into movie values (?,?,?,?,?,?)");
            stmt.setInt(1, newMovie.getMovieID());
            stmt.setString(2, newMovie.getImdb_id());
            stmt.setString(3, newMovie.getTitle());
            stmt.setString(4, newMovie.getRelesedate());
            stmt.setString(5, newMovie.getOverview());
            stmt.setFloat(6, newMovie.getCost());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to insert: " + e.getMessage());
        } */
    }
}
