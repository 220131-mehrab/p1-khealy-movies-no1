package com.revature.khealy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import java.sql.SQLTimeoutException;

public class DB {
    ArrayList<Movie> movies = new ArrayList<>();
    
    String url = "jdbc:h2:mem:test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;INIT=runscript from 'classpath:schema.sql'";
    //MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;";
    String username = "Kevin";
    String password = "";
    public void insertIntoDB(Movie newMovie) {
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            System.err.println("Inserting "+ newMovie.toString()+ " Into DB.");

            PreparedStatement stmt = connection.prepareStatement("insert into movie values (?,?,?,?,?,?)");
            stmt.setInt(1, newMovie.getMovieID());
            stmt.setString(2, newMovie.getImdb_id());
            stmt.setString(3, newMovie.getTitle());
            stmt.setString(4, newMovie.getRelesedate());
            stmt.setString(5, newMovie.getOverview());
            stmt.setFloat(6, newMovie.getCost());
            stmt.executeUpdate();
        }catch (SQLTimeoutException t){
                System.err.println("SQL Timed out: " + t.getMessage());
        }catch(SQLException e){
                System.err.println("SQL Error: " + e.getMessage());
        }
    }
        
    public ArrayList<Movie> getFromDB() {
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
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
        }
        return movies;
    }
}
    
