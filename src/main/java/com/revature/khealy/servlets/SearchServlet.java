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
            stmt.setString(1, tempMovie.getImdb_id());

        } catch (SQLException e) {
            System.err.println("Error searching movies! "+e.getMessage());
        }
        newMovies = dbRetriver.getFromDB(stmt);

        String result = mapper.writeValueAsString(newMovies);
        System.err.println(result);
        resp.setContentType("application/json");
        resp.getWriter().println(result);
    }
}
