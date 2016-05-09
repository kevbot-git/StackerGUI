package com.kevinmenhinick.stackergui;

public class StackerGUI {
    
    public static final String IMG_PATH = "/com/kevinmenhinick/stackergui/img/";
    
    public static void main(String[] args) {
	StackerWindow machine = new StackerWindow("Stacker! by Kevin");
	machine.setVisible(true);
	
	machine.startup();
    }
    
    public static void setupUI() {
	
    }
}
