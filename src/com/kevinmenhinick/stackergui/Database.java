package com.kevinmenhinick.stackergui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    
    public static String URL = "jdbc:derby://localhost:1527/stackerscores";
    public static String USER = "stacker";
    public static String PASSWORD = "stacker";
    public static String SCORE_TABLE = "score";

    private Connection conn;
    
    public Database() {
	conn = connect();
	System.out.println(conn != null);
    }
    
    public void save(Score score) {
	if(conn != null) {
	    try {
		Statement state = conn.createStatement();
		
		state.executeUpdate("INSERT INTO " + SCORE_TABLE + " VALUES " + //"(CURRENT_TIMESTAMP, 11, 'KEV')");
			"('" + score.getName() + "', " + score.getLevel() + ", CURRENT_TIMESTAMP)");
	    } catch (SQLException ex) {
		Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
    
    public Score[] getScores() {
	Score[] scores = new Score[2];
	return scores;
    }
    
    public void disconnect() {
	try {
	    if(conn != null) {
		conn.close();
	    }
	} catch (SQLException e) {
	    //
	}
    }
    
    private Connection connect() {
	try {
	    return DriverManager.getConnection(URL, USER, PASSWORD);
	} catch (SQLException ex) {
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }
    
    public void printInfo() {
	try {
	    System.out.println(conn.getMetaData().getDatabaseProductName() + " v" + conn.getMetaData().getDriverVersion());
	} catch (SQLException ex) {
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}