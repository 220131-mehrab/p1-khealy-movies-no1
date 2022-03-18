package com.revature.khealy.servlets;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.khealy.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MovieServlet extends HttpServlet {
    Connection connection;

    public MovieServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Movie> movies = new ArrayList<>();
        try {

            ResultSet moviesrs = connection.prepareStatement("select * from movie").executeQuery();
            while (moviesrs.next()) {
                Movie tempMovie = new Movie(moviesrs.getInt("movieID"),
                        moviesrs.getString("imdb_id"),//almost forgot this.
                        moviesrs.getString("title"),
                        moviesrs.getString("overview"),
                        moviesrs.getString("releaseDate"),
                        moviesrs.getFloat("cost")
                );
                movies.add(tempMovie);

                    /*
                    String result = mapper.writeValueAsString(tempMovie);
                    resp.setContentType("application/json");
                    resp.getWriter().println(result + ',');
                    //System.err.println(tempMovie.toString());
                    */
            }
        } catch (SQLException e) {
            System.err.println("Failed to retrieve from db: " + e.getSQLState());
        }

        // Get a JSON Mapper

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(movies);
        resp.setContentType("application/json");
        resp.getWriter().println(result);
    }

    /**
     * Gives values from json string to movie object then posts on sql server.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Movie newMovie = mapper.readValue(req.getInputStream(),Movie.class);
        try {
            System.err.println("Inserting into DB.");
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
        }
    }
}



