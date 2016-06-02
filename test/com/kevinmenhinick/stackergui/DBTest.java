package com.kevinmenhinick.stackergui;

import java.util.ArrayList;

public class DBTest {

    
    
    public static void main(String[] args) {
	
	Score score = new Score("Kevin", 1);
	System.out.println(score.getName() + " " + score.getLevel());
	
	Database d = new Database();
	
	d.save(new Score("KEV", 11));
	
	ArrayList<Score> scores = d.getScores();
	
	for(Score s : scores) {
	    System.out.println(s.toString());
	}
	
	d.disconnect();
    }
    
}