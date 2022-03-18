package com.revature.khealy.server;

import com.revature.khealy.servlets.DefaultServlet;
import com.revature.khealy.servlets.LoginServlet;
import com.revature.khealy.servlets.MovieServlet;


import org.apache.catalina.LifecycleException;

import org.apache.catalina.startup.Tomcat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Servers {
    Tomcat server = new Tomcat();

    /**
     * Default constructor initalizes the server
     * @throws SQLException
     */
    public Servers() throws SQLException {


        server.setBaseDir("java.io.tempdir");
        server.getConnector();
        server.addContext("", null);



        // Page Servlets
        //--get json strings from database
        String url = "jdbc:h2:mem:test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;INIT=runscript from 'classpath:schema.sql'";
        //MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;";
        String username = "Kevin";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);

        // Defualt servlet
        server.addServlet("", "defaultServlet", new DefaultServlet()).addMapping("/*");
        server.addServlet("", "movieServlet", new MovieServlet(connection)).addMapping("/movies");
        server.addServlet("","loginServlet", new LoginServlet(connection)).addMapping("/login");
/*
        server.addServlet("", "teamServlet", new TeamServlet(connection)).addMapping("/teams");
        server.addServlet("", "eventServlet", new EventServlet(connection)).addMapping("/events");
        server.addServlet("", "registerServlet", new RegisteredServlet(connection)).addMapping("/registered");
*/
    }

    /**
     * Starts server
     */
    public void startServer() throws LifecycleException {
        server.start();
    }
}

