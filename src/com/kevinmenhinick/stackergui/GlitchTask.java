package com.kevinmenhinick.stackergui;

import java.util.Random;

public class GlitchTask implements Runnable {
	
    private Random rand;
    private BackgroundPanel toGlitch;

    public GlitchTask(BackgroundPanel toGlitch) {
	rand = new Random(System.currentTimeMillis());
	this.toGlitch = toGlitch;
    }

    @Override
    public void run() {
	try {
	    while(true) {
		StackerGUI.getLightOff().play();
		toGlitch.setBGIndex(1);
		Thread.sleep(Math.abs(rand.nextInt()) % 5000 + 500);
		StackerGUI.getLightOn().play();
		toGlitch.setBGIndex(0);
		Thread.sleep(Math.abs(rand.nextInt()) % 300 + 30);
	    }
	} catch(InterruptedException e) {
	    //
	}
    }
}