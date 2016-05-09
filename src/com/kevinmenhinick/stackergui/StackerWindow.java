package com.kevinmenhinick.stackergui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

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
	this.mainWrapper = new BackgroundPanel("frame.png");
	
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	super.setSize(size);
	super.setResizable(false);
	super.setUndecorated(true);
	super.setLocation(getCenterPoint(this));
	
	setupComponents();
	
	setupListeners();
	
	super.add(this.getMainWrapper());
    }

    public final BackgroundPanel getMainWrapper() {
	return mainWrapper;
    }

    private void setupComponents() {
	screen = new BackgroundPanel(new String[] {"screen_dim.png", "screen_lit.png"});
	title = new BackgroundPanel(new String[] {"title_dim.png", "title_lit.png"});
	controls = new BackgroundPanel("button_panel.png");
	btnSelect = new ArcadeButton("select_btn_dim.png", "select_btn_lit.png");
	btnUp = new ArcadeButton("up_arrow_dim.png", "up_arrow_lit.png");
	btnDown = new ArcadeButton("down_arrow_dim.png", "down_arrow_lit.png");
	btnQuit = new ArcadeButton("quit_btn_dim.png", "quit_btn_lit.png");
	
	try {
	    super.setIconImage(ImageIO.read(getClass().getResource(StackerGUI.IMG_PATH + "icon.png")));
	} catch (IOException ex) {
	    Logger.getLogger(StackerWindow.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	btnQuit.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		quit();
	    }
	});
	
	this.add(title, 32, 32);
	this.add(screen, 32, 160);
	this.add(controls, 32, 704);
	controls.add(btnSelect, 80, 24);
	controls.add(btnUp, 18, 24);
	controls.add(btnDown, 18, 52);
	controls.add(btnQuit, 192, 24);
    }

    private void setupListeners() {
	btnSelect.setListener(new ArcadeButton.Listener() {
	    @Override
	    public void onPress() {
		System.out.println("Select pressed!");
	    }

	    @Override
	    public void onRelease() {
		System.out.println("Select released!");
	    }
	});
    }
    
    public void startup() {
	pause(200);
	title.setBGIndex(1);
	pause(200);
	title.setBGIndex(0);
	pause(100);
	title.setBGIndex(1);
	pause(1000);
	title.setBGIndex(0);
	pause(100);
	title.setBGIndex(1);
	pause(100);
	screen.setBGIndex(1);
    }
    
    public void quit() {
	System.out.println("Goodbye!");
	
	System.exit(0);
    }
    
    // add component directly into wrapper
    public Component add(Component comp, int x, int y) {
	comp.setBounds(x, y, comp.getWidth(), comp.getHeight());
	return this.getMainWrapper().add(comp);
    }
    
    public static Point getCenterPoint(Frame frame) {
	Toolkit tk = Toolkit.getDefaultToolkit();
	int x = (int) tk.getScreenSize().getWidth() / 2;
	int y = (int) tk.getScreenSize().getHeight() / 2;
	
	int diffX = (int) frame.getWidth() / 2;
	int diffY = (int) frame.getHeight() / 2;
	
	return new Point(x - diffX, y - diffY);
    }
    
    public static void pause(int millis) {
	try {
	    Thread.sleep(millis);
	} catch(InterruptedException e) {
	    //
	}
    }
}