package com.revature.khealy;

public class Movies {

    private int MovieID;
    private String title;
    private String Overview; //brief description about movie from imbd.com
    private String relesedate;
    
    public int getMovieID() {
        return MovieID;
    }
    public void setMovieID(int movieID) {
        MovieID = movieID;
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








    
}
