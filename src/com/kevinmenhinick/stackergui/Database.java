package com.kevinmenhinick.stackergui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    
    public static String URL = "jdbc:derby://localhost:1527/";
    public static String DB = "stackerscores";
    public static String USER = "stacker";
    public static String PASSWORD = "stacker";
    public static String SCORE_TABLE = "scores";

    private Connection conn;
    
    public Database() {
	conn = connect();
    }
    
    public void save(Score score) {
	if(conn != null) {
	    Statement state = null;
	    try {
		state = conn.createStatement();
		
		state.executeUpdate("INSERT INTO " + SCORE_TABLE + " VALUES " +
			"('" + score.getName() + "', " + score.getLevel() + ", CURRENT_TIMESTAMP)");
		
		state.close();
	    } catch (SQLException ex) {
		Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
    
    public ArrayList<Score> getScores() {
	
	if(conn != null) {
	    try {
		Statement state = conn.createStatement();
		
		ResultSet res = state.executeQuery("SELECT * FROM " + SCORE_TABLE + " ORDER BY score, player, tstamp");
		
		ArrayList<Score> temp = new ArrayList();
		
		while(res.next()) {
		    String name = res.getString("player");
		    int level = res.getInt("score");
		    Timestamp time = res.getTimestamp("tstamp");
		    temp.add(new Score(name, level, time));
		}
		state.close();
		return temp;
	    } catch (SQLException ex) {
		Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
	return null;
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
	    return DriverManager.getConnection(URL + DB, USER, PASSWORD);
	} catch (SQLException ex) {
	    //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Connection failed. Please make sure the local DB is running! Go to Services > Java DB: Start Server.");
            System.exit(1);
            return null;
	}
    }
}