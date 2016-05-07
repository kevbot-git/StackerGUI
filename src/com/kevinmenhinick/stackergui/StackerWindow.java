package com.kevinmenhinick.stackergui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StackerWindow extends JFrame {
    private Dimension size = new Dimension(256, 512);
    
    private JPanel mainWrapper;

    public StackerWindow(String title) {
	super(title);
	this.mainWrapper = new JPanel(new GridBagLayout());
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(size);
	this.setResizable(false);
	//this.setUndecorated(true);
	this.setLocation(StackerGUI.getCenterPoint(this));
	
	this.add(this.getMainWrapper());
    }

    public JPanel getMainWrapper() {
	return mainWrapper;
    }
}