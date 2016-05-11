package com.kevinmenhinick.stackergui;

public class StackerGUI {
    
    public static final String IMG_PATH = "/com/kevinmenhinick/stackergui/img/";
    public static final String AUDIO_PATH = "/com/kevinmenhinick/stackergui/audio/";
    
    private static StackerSound lightOn;
    private static StackerSound lightOff;
    private static StackerSound keyDown;
    private static StackerSound keyUp;
    
    public static void main(String[] args) {
	loadSounds();
	
	StackerWindow machine = new StackerWindow("Stacker! by Kevin");
	machine.setVisible(true);
	
	machine.startup();
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
