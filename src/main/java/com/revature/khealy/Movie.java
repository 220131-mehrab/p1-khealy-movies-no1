package com.revature.khealy;

public class Movie {

    private int movieID;
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

    public Movie(int ID, String title, String overview, String releaseDate) {
        movieID = ID;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public Movie(int ID, String title, String overview, String releaseDate, int cost) {
        movieID = ID;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.cost = cost;
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
        return overview;
    }
    public void setOverview(String overview) {
        overview = overview;
    }
    public String getRelesedate() {
        return releaseDate;
    }
    public void setReleaseDate(String relesedate) {
        this.releaseDate = relesedate;
    }
    public int getCost(){return this.cost;}

    public static Movie of(){
        return new Movie();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", title='" + title + '\'' +
                ", Overview='" + overview + '\'' +
                ", relese_date='" + releaseDate + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
