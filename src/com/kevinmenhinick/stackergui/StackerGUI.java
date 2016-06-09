package com.kevinmenhinick.stackergui;

import java.util.ArrayList;

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
    private static String player;
    
    public static void main(String[] args) throws InterruptedException {
	loadSounds();
	
        final ArrayList<String> answerHolder = new ArrayList(); // Will only store one answer
        NameInputWindow niw = new NameInputWindow("Enter Name:", answerHolder);
        synchronized(answerHolder) {
            while(answerHolder.isEmpty()) {
                answerHolder.wait();
            }
        }
	setName(answerHolder.get(0));
	
	pixels = new PixelStack(X_PIXELS, Y_PIXELS);
	
	StackerWindow machine = new StackerWindow("Stacker! by Kevin", pixels, player);
        machine.setAutoRequestFocus(true);
	machine.setVisible(true);
	
	machine.startup();
    }
    
    private static void setName(String name) {
	player = name;
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
