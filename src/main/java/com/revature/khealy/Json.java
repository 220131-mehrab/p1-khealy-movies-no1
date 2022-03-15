package com.revature.khealy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    public String SampleStirng = "{\"adult\":false,\"backdrop_path\":\"/tY6zVyt0OubPgCapbXFJLKhQqSu.jpg\",\"belongs_to_collection\":null,\"budget\":1350000,\"genres\":[{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"}],\"homepage\":\"http://www.universalstudiosentertainment.com/lock-stock-and-two-smoking-barrels/\",\"id\":100,\"imdb_id\":\"tt0120735\",\"original_language\":\"en\",\"original_title\":\"Lock, Stock and Two Smoking Barrels\",\"overview\":\"A card shark and his unwillingly-enlisted friends need to make a lot of cash quick after losing a sketchy poker match. To do this they decide to pull a heist on a small-time gang who happen to be operating out of the flat next door.\",\"popularity\":7.119,\"poster_path\":\"/8kSerJrhrJWKLk1LViesGcnrUPE.jpg\",\"production_companies\":[{\"id\":491,\"logo_path\":\"/rUp0lLKa1pr4UsPm8fgzmnNGxtq.png\",\"name\":\"Summit Entertainment\",\"origin_country\":\"US\"},{\"id\":21920,\"logo_path\":null,\"name\":\"The Steve Tisch Company\",\"origin_country\":\"\"},{\"id\":13419,\"logo_path\":null,\"name\":\"SKA Films\",\"origin_country\":\"\"},{\"id\":1382,\"logo_path\":\"/sOg7LGESPH5vCTOIdbMhLuypoLL.png\",\"name\":\"PolyGram Filmed Entertainment\",\"origin_country\":\"US\"},{\"id\":20076,\"logo_path\":\"/i9qXGJIP9fGN22PP5jXUVENbyHi.png\",\"name\":\"HandMade Films\",\"origin_country\":\"GB\"}],\"production_countries\":[{\"iso_3166_1\":\"GB\",\"name\":\"United Kingdom\"}],\"release_date\":\"1998-03-05\",\"revenue\":28356188,\"runtime\":105,\"spoken_languages\":[{\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Released\",\"tagline\":\"A Disgrace to Criminals Everywhere.\",\"title\":\"Lock, Stock and Two Smoking Barrels\",\"video\":false,\"vote_average\":8.2,\"vote_count\":4048}";  
        
    public Movie JsonToMovieObject(int ID, String movieJason, float cost){

        Movie newMovie = new Movie();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode movieJsonNode;
        try {
            movieJsonNode = mapper.readTree(movieJason);
            newMovie.setMovieID(-1);
            newMovie.setImdb_id(String.valueOf(movieJsonNode.get("imdb_id")));
            newMovie.setTitle(String.valueOf(movieJsonNode.get("title")));
            newMovie.setOverview(String.valueOf(movieJsonNode.get("overview")));
            newMovie.setReleaseDate(String.valueOf(movieJsonNode.get("release_date")));
            newMovie.setCost(cost);

        } catch (JsonMappingException jme){
            System.err.println("Error Mappping json data: " + jme.getMessage());
            jme.printStackTrace();  
        } catch (JsonProcessingException jpe) {
            System.err.println("Error Processing json data: " + jpe.getMessage());
            jpe.printStackTrace();   
        } catch (Exception e) {
            System.err.println("Error: "+ e.getMessage());
        }
        return newMovie;
    }
    
}
