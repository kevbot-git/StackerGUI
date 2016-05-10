package com.kevinmenhinick.stackergui;

public class StackerGUI {
    
    public static final String IMG_PATH = "/com/kevinmenhinick/stackergui/img/";
    public static final String AUDIO_PATH = "/com/kevinmenhinick/stackergui/audio/";
    
    private static StackerSound lightOn;
    private static StackerSound lightOff;
    
    public static void main(String[] args) {
	loadSounds();
	
	StackerWindow machine = new StackerWindow("Stacker! by Kevin");
	machine.setVisible(true);
	
	machine.startup();
    }
    
    private static void loadSounds() {
	lightOn = new StackerSound("light_on.wav");
	lightOff = new StackerSound("light_off.wav");
    }

    public static StackerSound getLightOn() {
	return lightOn;
    }

    public static StackerSound getLightOff() {
	return lightOff;
    }
}
