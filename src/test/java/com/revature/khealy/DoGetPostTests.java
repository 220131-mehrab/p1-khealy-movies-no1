package com.revature.khealy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.catalina.startup.Tomcat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoGetPostTests extends HttpServlet {

    Tomcat server = new Tomcat();
    public Connection connection;
    //not here
    //server.addServlet("","testServlet",testServlet).addMapping("/movies");

    @BeforeAll
    public void ServerForTesting(){
        
        server.getConnector();
        server.addContext("", null);
        String url = "jdbc:h2:mem:test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;INIT=runscript from 'classpath:schema.sql'";
        //MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;";
        String username = "Kevin";
        String password = "";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /*
    @Test
    //not here either
    public void addServletTest(){
        server.addServlet("", "testServlet", testServlet).addmapping("/movies")
    }*/

    @Test
    public void TestDoGetPost()  {
        
        HttpServlet testServlet = new HttpServlet(){

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
                    List<Movie> movies = new ArrayList<>();
                    try {
                        PreparedStatement statement = connection.prepareStatement("select * from movie where movieID = ?");
                        statement.setInt(1,3);
                        ResultSet moviesrs = statement.executeQuery();
                        while (moviesrs.next()) {
                            Movie tempMovie = new Movie(moviesrs.getInt("movieID"), 
                                moviesrs.getString("imdb_id"),//almost forgot this.
                                moviesrs.getString("title"),
                                moviesrs.getString("overview"),
                                moviesrs.getString("releaseDate"),
                                moviesrs.getFloat("cost")
                                );
                            movies.add(tempMovie);
                            System.err.println(tempMovie.toString());
                            
                            //Assertions.assertEquals("{"movieID":3,"imdb_id":"imdb3","title":"title3","overview":"date3","cost":3.0,"relesedate":"ov3"}",  tempMovie.toString() )
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
            }
            

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp){
            ObjectMapper mapper = new ObjectMapper();
            Movie newMovie;
            try {
                newMovie = mapper.readValue(req.getInputStream(),Movie.class);
                System.err.println("Inserting "+ newMovie.toString()+ " Into DB.");

                PreparedStatement stmt = connection.prepareStatement("insert into movie values (?,?,?,?,?,?)");
                stmt.setInt(1, newMovie.getMovieID());
                stmt.setString(2, newMovie.getImdb_id());
                stmt.setString(3, newMovie.getTitle());
                stmt.setString(4, newMovie.getRelesedate());
                stmt.setString(5, newMovie.getOverview());
                stmt.setFloat(6, newMovie.getCost());
                stmt.executeUpdate();
                
            } catch (IOException ioe) {
                System.err.println("IO Error: " + ioe.getMessage());
            } catch (SQLException sqle) {
                System.err.println("Failed to insert: " + sqle.getMessage());
            }
            
        }
    };
    //Yes, this is where it goes.
    server.addServlet("", "testServlet", testServlet).addMapping("/movies");
        
        //Assertions.assertEquals(expected, newMovie.toString());
    }
}
