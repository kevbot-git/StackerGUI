package com.kevinmenhinick.stackergui;

import java.sql.Timestamp;
import java.util.Date;

public class Score {

    private String name;
    private int level;
    private Timestamp date;

    public Score(String name, int level) {
	this(name, null, level);
    }
    public Score(String name, Timestamp timestamp, int level) {
	this.name = name.toUpperCase().substring(0, 3);
	this.level = level;
	date = timestamp;
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

    public Date getDate() {
	return date;
    }

    public void setDate(Timestamp date) {
	this.date = date;
    }
}