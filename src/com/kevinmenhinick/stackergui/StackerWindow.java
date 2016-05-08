package com.kevinmenhinick.stackergui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StackerWindow extends JFrame {
    private Dimension size = new Dimension(320, 832);
    
    private BackgroundPanel mainWrapper;
    private BackgroundPanel title;
    private BackgroundPanel screen;
    private BackgroundPanel controls;
    private ArcadeButton btnSelect;
    private ArcadeButton btnUp;
    private ArcadeButton btnDown;
    private ArcadeButton btnQuit;

    public StackerWindow(String title) {
	super(title);
	this.mainWrapper = new BackgroundPanel("img/frame.png");
	
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	super.setSize(size);
	super.setResizable(false);
	super.setUndecorated(true);
	super.setLocation(StackerGUI.getCenterPoint(this));
	
	setupComponents();
	
	super.add(this.getMainWrapper());
    }

    public final BackgroundPanel getMainWrapper() {
	return mainWrapper;
    }

    private void setupComponents() {
	screen = new BackgroundPanel(new String[] {"img/screen_dim.png", "img/screen_lit.png"});
	title = new BackgroundPanel(new String[] {"img/title_dim.png", "img/title_lit.png"});
	controls = new BackgroundPanel("img/button_panel.png");
	btnSelect = new ArcadeButton("img/select_btn_dim.png", "img/select_btn_lit.png");
	btnUp = new ArcadeButton("img/up_arrow_dim.png", "img/up_arrow_lit.png");
	btnDown = new ArcadeButton("img/up_arrow_dim.png", "img/up_arrow_lit.png");
	btnQuit = new ArcadeButton("img/quit_btn_dim.png", "img/quit_btn_lit.png");
	
	System.out.println(this.getMainWrapper().getBounds().height);
	
	this.add(title, 32, 32);
	this.add(screen, 32, 160);
	this.add(controls, 32, 704);
	controls.add(btnSelect, 80, 24);
	controls.add(btnUp, 18, 24);
	controls.add(btnDown, 18, 52);
	controls.add(btnQuit, 192, 24);
	
	btnSelect.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		
	    }
	});
    }

    // add component directly into wrapper
    public Component add(Component comp, int x, int y) {
	comp.setBounds(x, y, comp.getWidth(), comp.getHeight());
	return this.getMainWrapper().add(comp);
    }
    
    
}