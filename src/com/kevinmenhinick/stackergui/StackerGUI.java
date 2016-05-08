package com.kevinmenhinick.stackergui;

import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;

public class StackerGUI {
    
    public static void main(String[] args) {
	setupUI();
    }
    
    public static void setupUI() {
	StackerWindow window = new StackerWindow("Stacker! by Kevin");
	window.setVisible(true);
    }
    
    public static Point getCenterPoint(Frame frame) {
	Toolkit tk = Toolkit.getDefaultToolkit();
	int x = (int) tk.getScreenSize().getWidth() / 2;
	int y = (int) tk.getScreenSize().getHeight() / 2;
	
	int diffX = (int) frame.getWidth() / 2;
	int diffY = (int) frame.getHeight() / 2;
	
	return new Point(x - diffX, y - diffY);
    }
}
