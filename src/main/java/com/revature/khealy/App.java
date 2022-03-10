package com.revature.khealy;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.*;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


//import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.String;

public class App {
    public String name = new String("me");
    public static void main(String[] args) {
        String name = new String("Kev");
        ObjectMapper objectMapper = new ObjectMapper();
        String movieJason = "{\"adult\":false,\"backdrop_path\":\"/tY6zVyt0OubPgCapbXFJLKhQqSu.jpg\",\"belongs_to_collection\":null,\"budget\":1350000,\"genres\":[{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"}],\"homepage\":\"http://www.universalstudiosentertainment.com/lock-stock-and-two-smoking-barrels/\",\"id\":100,\"imdb_id\":\"tt0120735\",\"original_language\":\"en\",\"original_title\":\"Lock, Stock and Two Smoking Barrels\",\"overview\":\"A card shark and his unwillingly-enlisted friends need to make a lot of cash quick after losing a sketchy poker match. To do this they decide to pull a heist on a small-time gang who happen to be operating out of the flat next door.\",\"popularity\":7.119,\"poster_path\":\"/8kSerJrhrJWKLk1LViesGcnrUPE.jpg\",\"production_companies\":[{\"id\":491,\"logo_path\":\"/rUp0lLKa1pr4UsPm8fgzmnNGxtq.png\",\"name\":\"Summit Entertainment\",\"origin_country\":\"US\"},{\"id\":21920,\"logo_path\":null,\"name\":\"The Steve Tisch Company\",\"origin_country\":\"\"},{\"id\":13419,\"logo_path\":null,\"name\":\"SKA Films\",\"origin_country\":\"\"},{\"id\":1382,\"logo_path\":\"/sOg7LGESPH5vCTOIdbMhLuypoLL.png\",\"name\":\"PolyGram Filmed Entertainment\",\"origin_country\":\"US\"},{\"id\":20076,\"logo_path\":\"/i9qXGJIP9fGN22PP5jXUVENbyHi.png\",\"name\":\"HandMade Films\",\"origin_country\":\"GB\"}],\"production_countries\":[{\"iso_3166_1\":\"GB\",\"name\":\"United Kingdom\"}],\"release_date\":\"1998-03-05\",\"revenue\":28356188,\"runtime\":105,\"spoken_languages\":[{\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Released\",\"tagline\":\"A Disgrace to Criminals Everywhere.\",\"title\":\"Lock, Stock and Two Smoking Barrels\",\"video\":false,\"vote_average\":8.2,\"vote_count\":4048}";
        JsonNode jsonNode = objectMapper.readValue(movieJason);
        String movieTitle =  new String().valueOf(jsonNode.get("title"));

        Tomcat server = new Tomcat(); 
        server.getConnector();
        server.addContext("", null);
        server.addServlet("", "proServlet", new HttpServlet() {
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
        try {
            server.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
            System.err.println("Server failed to start");
        }
    }
}