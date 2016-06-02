package com.kevinmenhinick.stackergui;

import java.util.Scanner;

public class StackerGUI {
    
    public static final String IMG_PATH = "/com/kevinmenhinick/stackergui/img/";
    public static final String AUDIO_PATH = "/com/kevinmenhinick/stackergui/audio/";
    
    public static final int X_PIXELS = 7;
    public static final int Y_PIXELS = 14;
    
    private static StackerSound lightOn;
    private static StackerSound lightOff;
    private static StackerSound keyDown;
    private static StackerSound keyUp;
    
    private static PixelStack pixels;
    
    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	
	loadSounds();
	
	pixels = new PixelStack(X_PIXELS, Y_PIXELS);
	
	StackerWindow machine = new StackerWindow("Stacker! by Kevin", pixels);
	machine.setVisible(true);
	
	do {
	    machine.startup();
	} while(scan.nextLine().equals("y"));
    }
    
    private static void loadSounds() {
	lightOn = new StackerSound("light_on.wav");
	lightOff = new StackerSound("light_off.wav");
	keyDown = new StackerSound("key_down.wav");
	keyUp = new StackerSound("key_up.wav");
    }

    public static StackerSound getLightOnSound() {
	return lightOn;
    }

    public static StackerSound getLightOffSound() {
	return lightOff;
    }

    public static StackerSound getKeyDownSound() {
	return keyDown;
    }

    public static StackerSound getKeyUpSound() {
	return keyUp;
    }
}
