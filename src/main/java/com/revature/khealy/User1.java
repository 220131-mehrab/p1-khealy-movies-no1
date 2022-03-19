package com.revature.khealy;

public class User1 {
    //private static int IDCounter = 0;
    private int userID =0;
    private String userName = "";
    private String email = "";
    private String password = "";
    private String imdb_id= "";

    public User1(){
        //no arg constructor
    }

    public User1(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User1(int userID, String userName, String email, String password, String imdb_id){
        this.userID = 0;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.imdb_id = "X";
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", imdb_id='" + imdb_id + '\'' +
                '}';
    }

    public static User1 of(){return new User1();};

    //User1 Builders
    User1 userID(int UserID){
        this.userID = userID;
        return this;
    }

    User1 userName(String userName){
        this.userName = userName;
        return this;
    }

    User1 email(String email){
        this.email = email;
        return this;
    }

    User1 password(String password){
        this.password = password;
        return this;
    }

    User1 imdb_id(String imdb_id){
        this.imdb_id = imdb_id;
        return this;
    }
}