package com.kevinmenhinick.stackergui;

import java.util.Random;

public class GlitchTask implements Runnable {
	
    private Random rand;
    private BackgroundPanel toGlitch;

    public GlitchTask(BackgroundPanel toGlitch) {
	rand = new Random(System.currentTimeMillis());
	this.toGlitch = toGlitch;
    }

    public void on() {
	StackerGUI.getLightOnSound().play();
	toGlitch.setBGIndex(0);
    }
    
    public void off() {
	StackerGUI.getLightOffSound().play();
	toGlitch.setBGIndex(1);
    }
    
    @Override
    public void run() {
	try {
	    while(true) {
		off();
		Thread.sleep(Math.abs(rand.nextInt()) % 5000 + 500);
		on();
		Thread.sleep(Math.abs(rand.nextInt()) % 300 + 30);
	    }
	} catch(InterruptedException e) {
	    //
	}
    }
}