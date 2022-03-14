package com.revature.khealy;

public class Movie {

    private int movieID;
    private String imdb_id;
    private String title;
    private String overview; //brief description about movie from imbd.com
    private String releaseDate;
    private int cost;
    
    public Movie(){
        
    }
    public Movie(int ID, String title) {
        movieID = ID; 
        this.title = title;
    }
    public Movie(int ID, String title, String overview) {
        movieID = ID;
        this.title = title;
        this.overview = overview;
    }
    public Movie(int ID, String title, String overview, String releaseDate) {
        movieID = ID;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public Movie(int ID, String imdb_id, String title, String overview, String releaseDate, int cost) {
        movieID = ID;
        this.imdb_id = imdb_id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.cost = cost;
    }

    //getters and setters
    public int getMovieID() {
        return movieID;
    }
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
    public String getImdb_id() {
        return imdb_id;
    }
    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getRelesedate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public int getCost(){return this.cost;}
    public void setCost(int cost) {
        this.cost = cost;
    } 
    
    //of function and build functions
    public static Movie of(){
        return new Movie();
    }


    //moviebuilders
    Movie movieID(int movieID){
        this.movieID = movieID;
        return this;
    }
    Movie imdb_id(String imdb_id){
        this.imdb_id = imdb_id;
        return this;
    }
    Movie title(String title) {
        this.title = title;
        return this;
    } 
    Movie overview(String overview) {
        this.overview = overview;
        return this;
    }  
    Movie releaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
    Movie cost(int cost) {
        this.cost = cost;
        return this;
    }
    @Override
    public String toString() {
        return "Movie [movieID=" + movieID +", imdb_id=" + imdb_id + ", overview=" + overview
                + ", releaseDate=" + releaseDate + ", title=" + title + ", cost=" + cost + "]";
    } 

}
