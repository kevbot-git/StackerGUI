package com.kevinmenhinick.stackergui;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {

    private String name;
    private int level;
    private Timestamp date;

    public Score(String name, int level) {
	this(name, level, null);
    }
    public Score(String name, int level, Timestamp timestamp) {
	this.name = name.toUpperCase().substring(0, 3);
	this.level = level;
	date = timestamp;
    }
    
    public String toString() {
	return ("Name: " + getName() + "\nScore:" + getLevel() + "\nTime:" + dateString());
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getLevel() {
	return level;
    }

    public void setLevel(int level) {
	this.level = level;
    }
    
    public String dateString() {
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	return sdf.format(date);
    }

    public Timestamp getDate() {
	return date;
    }

    public void setDate(Timestamp date) {
	this.date = date;
    }
}