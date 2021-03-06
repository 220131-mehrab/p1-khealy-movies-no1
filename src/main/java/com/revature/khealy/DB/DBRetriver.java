package com.revature.khealy.DB;

import com.revature.khealy.Movie;

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



public class DBRetriver {
    public ArrayList<Movie> movies = new ArrayList<>();

    public void insertIntoDB(Movie newMovie,Connection connection) {
        try{
            System.err.println("Inserting " + newMovie.toString() + " Into DB.");
            //------------------------------------------------------------------------------//(md,imdb, tt,ov,rd,ct)
            PreparedStatement stmt = connection.prepareStatement("insert into movie values (?,    ?, ?, ?, ?, ?)");
            stmt.setInt(1, newMovie.getMovieID());
            stmt.setString(2, newMovie.getImdb_id());
            stmt.setString(3, newMovie.getTitle());
            stmt.setString(4, newMovie.getOverview());
            stmt.setString(5, newMovie.getRelesedate());
            stmt.setFloat(6, newMovie.getCost());
            stmt.executeUpdate();

        }catch (SQLTimeoutException t){
                System.err.println("SQL Timed out: " + t.getMessage());
        }catch(SQLException e){
                System.err.println("SQL Error: " + e.getMessage() + e.getErrorCode());
                //If (e.getErrorCode() == )
        }catch(Exception ge){
            System.err.println("General Error: " + ge.getMessage());
        }
    }
        
    public ArrayList<Movie> getFromDB(PreparedStatement statement) {
        try{
            ResultSet moviesrs = statement.executeQuery();
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
    
