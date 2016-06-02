package com.kevinmenhinick.stackergui;

public class DBTest {

    
    
    public static void main(String[] args) {
	
	Score score = new Score("Kevin", 1);
	System.out.println(score.getName() + " " + score.getLevel());
	
	Database d = new Database();
	
	d.save(new Score("KEV", 11));
	
	d.disconnect();
    }
    
}