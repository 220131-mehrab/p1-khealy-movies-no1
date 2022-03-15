package com.revature.khealy;

///import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleException;
//import org.apache.catalina.connector.Response;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//import org.h2.message.DbException;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

//Curl statements to test
//curl localhost:8080/movies
//curl -X POST -d '{"movieID":3 "title":"no highway"}' localhost:8080/movies
//https://www.rfc-editor.org/rfc/rfc4627
//import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.String;

public class App {

    String movieJason = "{\"adult\":false,\"backdrop_path\":\"/tY6zVyt0OubPgCapbXFJLKhQqSu.jpg\",\"belongs_to_collection\":null,\"budget\":1350000,\"genres\":[{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"}],\"homepage\":\"http://www.universalstudiosentertainment.com/lock-stock-and-two-smoking-barrels/\",\"id\":100,\"imdb_id\":\"tt0120735\",\"original_language\":\"en\",\"original_title\":\"Lock, Stock and Two Smoking Barrels\",\"overview\":\"A card shark and his unwillingly-enlisted friends need to make a lot of cash quick after losing a sketchy poker match. To do this they decide to pull a heist on a small-time gang who happen to be operating out of the flat next door.\",\"popularity\":7.119,\"poster_path\":\"/8kSerJrhrJWKLk1LViesGcnrUPE.jpg\",\"production_companies\":[{\"id\":491,\"logo_path\":\"/rUp0lLKa1pr4UsPm8fgzmnNGxtq.png\",\"name\":\"Summit Entertainment\",\"origin_country\":\"US\"},{\"id\":21920,\"logo_path\":null,\"name\":\"The Steve Tisch Company\",\"origin_country\":\"\"},{\"id\":13419,\"logo_path\":null,\"name\":\"SKA Films\",\"origin_country\":\"\"},{\"id\":1382,\"logo_path\":\"/sOg7LGESPH5vCTOIdbMhLuypoLL.png\",\"name\":\"PolyGram Filmed Entertainment\",\"origin_country\":\"US\"},{\"id\":20076,\"logo_path\":\"/i9qXGJIP9fGN22PP5jXUVENbyHi.png\",\"name\":\"HandMade Films\",\"origin_country\":\"GB\"}],\"production_countries\":[{\"iso_3166_1\":\"GB\",\"name\":\"United Kingdom\"}],\"release_date\":\"1998-03-05\",\"revenue\":28356188,\"runtime\":105,\"spoken_languages\":[{\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Released\",\"tagline\":\"A Disgrace to Criminals Everywhere.\",\"title\":\"Lock, Stock and Two Smoking Barrels\",\"video\":false,\"vote_average\":8.2,\"vote_count\":4048}";  
    public static void main(String[] args) throws SQLException {
        
        //--get json strings from database
        String url = "jdbc:h2:mem:test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;INIT=runscript from 'classpath:schema.sql'";
        //MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;";
        String username = "Kevin";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);
        HttpServlet movieServlet = new HttpServlet() {
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
            @Overrid  e
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
        }; //.addmapping("/movies");

/*      
        HttpServlet movieServlet = new HttpServlet() {

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                ObjectMapper objectMapper = new ObjectMapper();

                String movieJason = "{\"adult\":false,\"backdrop_path\":\"/tY6zVyt0OubPgCapbXFJLKhQqSu.jpg\",\"belongs_to_collection\":null,\"budget\":1350000,\"genres\":[{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"}],\"homepage\":\"http://www.universalstudiosentertainment.com/lock-stock-and-two-smoking-barrels/\",\"id\":100,\"imdb_id\":\"tt0120735\",\"original_language\":\"en\",\"original_title\":\"Lock, Stock and Two Smoking Barrels\",\"overview\":\"A card shark and his unwillingly-enlisted friends need to make a lot of cash quick after losing a sketchy poker match. To do this they decide to pull a heist on a small-time gang who happen to be operating out of the flat next door.\",\"popularity\":7.119,\"poster_path\":\"/8kSerJrhrJWKLk1LViesGcnrUPE.jpg\",\"production_companies\":[{\"id\":491,\"logo_path\":\"/rUp0lLKa1pr4UsPm8fgzmnNGxtq.png\",\"name\":\"Summit Entertainment\",\"origin_country\":\"US\"},{\"id\":21920,\"logo_path\":null,\"name\":\"The Steve Tisch Company\",\"origin_country\":\"\"},{\"id\":13419,\"logo_path\":null,\"name\":\"SKA Films\",\"origin_country\":\"\"},{\"id\":1382,\"logo_path\":\"/sOg7LGESPH5vCTOIdbMhLuypoLL.png\",\"name\":\"PolyGram Filmed Entertainment\",\"origin_country\":\"US\"},{\"id\":20076,\"logo_path\":\"/i9qXGJIP9fGN22PP5jXUVENbyHi.png\",\"name\":\"HandMade Films\",\"origin_country\":\"GB\"}],\"production_countries\":[{\"iso_3166_1\":\"GB\",\"name\":\"United Kingdom\"}],\"release_date\":\"1998-03-05\",\"revenue\":28356188,\"runtime\":105,\"spoken_languages\":[{\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Released\",\"tagline\":\"A Disgrace to Criminals Everywhere.\",\"title\":\"Lock, Stock and Two Smoking Barrels\",\"video\":false,\"vote_average\":8.2,\"vote_count\":4048}";
                    String movieTitle = objectMapper.writeValueAsString(movieJason);
                    resp.setContentType("application/json");
                    resp.getWriter().println(movieTitle);
                }
            };
            
 */       

        Tomcat server = new Tomcat(); 
        server.getConnector();
        server.addContext("", null);
        
        server.addServlet("", "defaultServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String filename = req.getPathInfo();//Points to path of file from browser
                String resourceDir = "static";
                InputStream file = getClass().getClassLoader().getResourceAsStream(resourceDir + filename); //loads src data into backend from static folder
                String mimeType = getServletContext().getMimeType(filename); //checks for type of file being imported
                resp.setContentType(mimeType);
                IOUtils.copy(file, resp.getOutputStream()); //copies file from the input to server


            }
        }).addMapping("/*");

        server.addServlet("", "movieServlet", movieServlet).addMapping("/movies");
        try {
            server.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
            System.err.println("Server failed to start");
        }
    }
}