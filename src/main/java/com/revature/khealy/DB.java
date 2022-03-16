package com.revature.khealy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLTimeoutException;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;



public class DB {
    public ArrayList<Movie> movies = new ArrayList<>();

    public void insertIntoDB(Movie newMovie,Connection connection) throws SQLTimeoutException, SQLException {
            System.err.println("Inserting " + newMovie.toString() + " Into DB.");

            PreparedStatement stmt = connection.prepareStatement("insert into movie values (?,?,?,?,?,?)");
            stmt.setInt(1, newMovie.getMovieID());
            stmt.setString(2, newMovie.getImdb_id());
            stmt.setString(3, newMovie.getTitle());
            stmt.setString(4, newMovie.getRelesedate());
            stmt.setString(5, newMovie.getOverview());
            stmt.setFloat(6, newMovie.getCost());
            stmt.executeUpdate();
        }
    }
        
    public ArrayList<Movie> getFromDB(Connection connection) {
        try{
            ResultSet moviesrs = connection.prepareStatement("select * from movie").executeQuery();
            while (moviesrs.next()) {
                Movie tempMovie = new Movie(
                    moviesrs.getInt("movieID"),
                    moviesrs.getString("imdb_id"),//almost forgot this.
                    moviesrs.getString("title"),
                    moviesrs.getString("overview"),
                    moviesrs.getString("releaseDate"),
                    moviesrs.getInt("cost")
                    );
                movies.add(tempMovie);
                System.err.println("Outputting " + tempMovie.toString() + " from DB.");
            }
        }catch (SQLTimeoutException t){
            System.err.println("SQL Timed out: " + t.getMessage());
        }catch(SQLException e){
            System.err.println("SQL Error: " + e.getMessage());
        }catch (Exception ge){
            System.err.println("General Error: " + ge.getMessage());
        }
        return movies;
    }
}
    
