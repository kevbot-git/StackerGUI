package com.kevinmenhinick.stackergui;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Score implements Comparable {

    private String name;
    private int level;
    private Timestamp date;

    public Score(String name, int level) {
	this(name, level, new Timestamp(new Date().getTime()));
    }
    public Score(String name, int level, Timestamp timestamp) {
	this.name = name.toUpperCase();
        if(this.name.length() > 3) this.name = this.name.substring(0, 3);
	this.level = level;
	date = timestamp;
    }
    
    @Override
    public String toString() {
	return ("Name: " + getName() + "\nScore:" + getLevel() + "\nTime:" + dateString());
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Score) {
            Score other = ((Score) o);
            if(this.getDate() != null && other.getDate() != null) {
                if(this.getLevel() != other.getLevel()) {
                    return (new Integer(this.getLevel())).compareTo(other.getLevel());
                }
                else if(!this.getDate().equals(other.getDate())) {
                    return this.getDate().compareTo(other.getDate());
                }
                else if(!this.getName().equals(other.getName())) {
                    return this.getName().compareTo(other.getName());
                }
            }
            else {
                //
            }
        }
        return 0;
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