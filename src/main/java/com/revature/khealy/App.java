package com.revature.khealy;

///import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.khealy.server.Servers;
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
    public static void main(String[] args) {


        try {
            Servers server = new Servers();
                server.startServer();

            } catch (LifecycleException e) {
                e.printStackTrace();
               System.err.println("Failed to start server: " + e.getMessage());
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }//end main
}