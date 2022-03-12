package com.revature.khealy;

public class Movie {

    private int movieID;
    private String title;
    private String Overview; //brief description about movie from imbd.com
    private String relesedate;
    
    public Movie(){
        
    }
    public Movie(int ID, String title) {
        movieID = ID; 
        this.title = title;
    }
    public int getMovieID() {
        return movieID;
    }
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOverview() {
        return Overview;
    }
    public void setOverview(String overview) {
        Overview = overview;
    }
    public String getRelesedate() {
        return relesedate;
    }
    public void setRelesedate(String relesedate) {
        this.relesedate = relesedate;
    }

    public static Movie of(){
        return new Movie();
    }
    
}
