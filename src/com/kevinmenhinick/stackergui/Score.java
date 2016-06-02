package com.kevinmenhinick.stackergui;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {

    private String name;
    private int level;
    private Date date;

    public Score(String name, int level) {
	this.name = name.toUpperCase().substring(0, 3);
	this.level = level;
	date = new Date();
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

    public void setDate(Date date) {
	this.date = date;
    }
}