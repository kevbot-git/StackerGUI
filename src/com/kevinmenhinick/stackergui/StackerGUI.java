package com.kevinmenhinick.stackergui;

public class StackerGUI {
    
    public static final String IMG_PATH = "/com/kevinmenhinick/stackergui/img/";
    
    public static void main(String[] args) {
	setupUI();
    }
    
    public static void setupUI() {
	StackerWindow window = new StackerWindow("Stacker! by Kevin");
	window.setVisible(true);
    }
}
