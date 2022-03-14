package com.revature.khealy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


//import org.h2.message.DbException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppTests {
    

    private Movie movie;

    @Test
    public void jasonStringToMovieObjectToDBToMovieObject(){
        List<Movie> movies = new ArrayList<>();

        //--get json strings from database
        String url = "jdbc:h2:mem:test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;INIT=runscript from 'classpath:schema.sql'";
        //MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;";
        String username = "Kevin";
        String password = "";
         try {
            Movie newMovie = new Movie();  
            Connection connection = DriverManager.getConnection(url, username, password);
            ObjectMapper mapper = new ObjectMapper();//
            String movieJason = "{\"adult\":false,\"backdrop_path\":\"/tY6zVyt0OubPgCapbXFJLKhQqSu.jpg\",\"belongs_to_collection\":null,\"budget\":1350000,\"genres\":[{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"}],\"homepage\":\"http://www.universalstudiosentertainment.com/lock-stock-and-two-smoking-barrels/\",\"id\":100,\"imdb_id\":\"tt0120735\",\"original_language\":\"en\",\"original_title\":\"Lock, Stock and Two Smoking Barrels\",\"overview\":\"A card shark and his unwillingly-enlisted friends need to make a lot of cash quick after losing a sketchy poker match. To do this they decide to pull a heist on a small-time gang who happen to be operating out of the flat next door.\",\"popularity\":7.119,\"poster_path\":\"/8kSerJrhrJWKLk1LViesGcnrUPE.jpg\",\"production_companies\":[{\"id\":491,\"logo_path\":\"/rUp0lLKa1pr4UsPm8fgzmnNGxtq.png\",\"name\":\"Summit Entertainment\",\"origin_country\":\"US\"},{\"id\":21920,\"logo_path\":null,\"name\":\"The Steve Tisch Company\",\"origin_country\":\"\"},{\"id\":13419,\"logo_path\":null,\"name\":\"SKA Films\",\"origin_country\":\"\"},{\"id\":1382,\"logo_path\":\"/sOg7LGESPH5vCTOIdbMhLuypoLL.png\",\"name\":\"PolyGram Filmed Entertainment\",\"origin_country\":\"US\"},{\"id\":20076,\"logo_path\":\"/i9qXGJIP9fGN22PP5jXUVENbyHi.png\",\"name\":\"HandMade Films\",\"origin_country\":\"GB\"}],\"production_countries\":[{\"iso_3166_1\":\"GB\",\"name\":\"United Kingdom\"}],\"release_date\":\"1998-03-05\",\"revenue\":28356188,\"runtime\":105,\"spoken_languages\":[{\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Released\",\"tagline\":\"A Disgrace to Criminals Everywhere.\",\"title\":\"Lock, Stock and Two Smoking Barrels\",\"video\":false,\"vote_average\":8.2,\"vote_count\":4048}";  
            JsonNode movieJsonNode =  mapper.readTree(movieJason);
            String backDrop = String.valueOf(movieJsonNode.get("backdrop_path"));
            String adult = String.valueOf(movieJsonNode.get("adult"));
            newMovie.setMovieID(-1);
            newMovie.setImdb_id(String.valueOf(movieJsonNode.get("imdb_id")));
            newMovie.setTitle(String.valueOf(movieJsonNode.get("title")));
            newMovie.setOverview(String.valueOf(movieJsonNode.get("overview")));
            newMovie.setReleaseDate(String.valueOf(movieJsonNode.get("release_date")));
            newMovie.setCost(0);

            System.err.println("Other values: ");
            System.err.println(adult);
            System.err.println(backDrop);
            System.err.println("Inserting "+ newMovie.toString()+ " Into DB.");

            PreparedStatement stmt = connection.prepareStatement("insert into movie values (?,?,?,?,?,?)");
            stmt.setInt(1, newMovie.getMovieID());
            stmt.setString(2, newMovie.getImdb_id());
            stmt.setString(3, newMovie.getTitle());
            stmt.setString(4, newMovie.getRelesedate());
            stmt.setString(5, newMovie.getOverview());
            stmt.setInt(6, newMovie.getCost());
            stmt.executeUpdate();

            ResultSet moviesrs = connection.prepareStatement("select * from movie").executeQuery();
            while (moviesrs.next()) {
                movie = new Movie(
                    moviesrs.getInt("movieID"),
                    moviesrs.getString("imdb_id"),//almost forgot this.
                    moviesrs.getString("title"),
                    moviesrs.getString("overview"),
                    moviesrs.getString("releaseDate"),
                    moviesrs.getInt("cost")
                    );
                Movie tempMovie = movie;
                movies.add(tempMovie);
                System.err.println("Outputting " + tempMovie.toString() + " from DB.");

            }
        } catch (SQLException | JsonProcessingException e) {
            System.err.println("Failed to insert: " + e.getMessage());
        }
    }
    
    @Test
    public void testingMovieOf(){
        Movie mve = new Movie();

        mve = Movie.of().movieID(1).title("No Highway in the Sky").imdb_id("xxxxxxsky").overview("James Steuwart Saves the day.").releaseDate("1-1-1950").cost(1);
        System.err.println("running test for movie assignment: "+ mve.toString());
        Assertions.assertEquals("Movie [movieID=1, imdb_id=xxxxxxsky, overview=James Steuwart Saves the day., releaseDate=1-1-1950, title=No Highway in the Sky, cost=1]",mve.toString());

    }
}
