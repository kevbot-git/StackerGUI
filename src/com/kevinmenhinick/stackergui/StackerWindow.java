package com.kevinmenhinick.stackergui;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class StackerWindow extends JFrame implements Runnable {
    
    public static int START_INTERVAL = 300;
    
    public PixelStack pixels;
    private Thread updateThread;
    
    private Dimension size = new Dimension(320, 832);
    
    private BackgroundPanel mainWrapper;
    private BackgroundPanel title;
    private BackgroundPanel controls;
    private ArcadeButton btnSelect;
    private ArcadeButton btnUp;
    private ArcadeButton btnDown;
    private ArcadeButton btnQuit;
    private StackerCanvas screenCanvas;
    
    private boolean running;
    private int tickInterval;
    private String playerName;
    private Database db;

    public StackerWindow(String title, PixelStack pixelStack, String playerName) {
	super(title);
	
	db = new Database();
	
	this.playerName = playerName;
	pixels = pixelStack;
	
	this.mainWrapper = new BackgroundPanel("frame.png");
	
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	super.setSize(size);
	super.setResizable(false);
	super.setUndecorated(true);
	super.setLocation(getCenterPoint(this));
	
	setupComponents(pixelStack.getWidth(), pixelStack.getMaxHeight());
	
	setupListeners();
	
	super.add(this.getMainWrapper());
    }

    public final BackgroundPanel getMainWrapper() {
	return mainWrapper;
    }
    
    private void setupComponents(int screenXPixels, int screenYPixels) {
	title = new BackgroundPanel(new String[] {"title_dim.png", "title_lit.png"});
	controls = new BackgroundPanel("button_panel.png");
	btnSelect = new ArcadeButton("select_btn_dim.png", "select_btn_lit.png");
	btnUp = new ArcadeButton("up_arrow_dim.png", "up_arrow_lit.png");
	btnDown = new ArcadeButton("down_arrow_dim.png", "down_arrow_lit.png");
	btnQuit = new ArcadeButton("quit_btn_dim.png", "quit_btn_lit.png");
	screenCanvas = new StackerCanvas("screen_dim.png", "pixel.png", screenXPixels, screenYPixels);
	
	try {
	    super.setIconImage(ImageIO.read(getClass().getResource(StackerGUI.IMG_PATH + "icon.png")));
	} catch (IOException ex) {
	    Logger.getLogger(StackerWindow.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	this.add(title, 32, 32);
	this.add(screenCanvas, 32, 160);
	this.add(controls, 32, 704);
	controls.add(btnSelect, 80, 24);
	controls.add(btnUp, 18, 24);
	controls.add(btnDown, 18, 52);
	controls.add(btnQuit, 192, 24);
    }

    private void setupListeners() {
	btnUp.setListener(new ArcadeButton.Listener() {
	    @Override
	    public void onPress() {
		//
	    }

	    @Override
	    public void onRelease() {
		//
	    }
	});
	
	btnDown.setListener(new ArcadeButton.Listener() {
	    @Override
	    public void onPress() {
		//
	    }

	    @Override
	    public void onRelease() {
		//
	    }
	});
	
	btnSelect.setListener(new ArcadeButton.Listener() {
	    @Override
	    public void onPress() {
		if(isRunning()) {
		    trap();
		}
	    }

	    @Override
	    public void onRelease() {
		//
	    }
	});
	
	btnQuit.setListener(new ArcadeButton.Listener() {
	    @Override
	    public void onPress() {
		//
	    }

	    @Override
	    public void onRelease() {
		quit();
	    }
	});
    }
    
    public void startup() {
	tickInterval = START_INTERVAL;
	pixels = new PixelStack(pixels);
	
	Thread gtThread = new Thread(new GlitchTask(title));
	gtThread.start();
	
	pixels.push(pixels.startRow(3));
	screenCanvas.render(pixels);
	
	setRunning(true);
	updateThread = new Thread(this);
	updateThread.start();
    }
    
    public void update() {
	pixels.nextOscTick();
	screenCanvas.render(pixels);
    }
    
    public synchronized void trap() {
	PixelStack temp = pixels;
	Boolean[] rem = temp.remainder();
	if(temp.hasRemainder()) {
	    pixels.moveRemainder(rem);
	    tickInterval -= (tickInterval / 10);
	    pause(1000);
	    
	    
	} else {
	    db.save(new Score(playerName, pixels.getTotalHeight()));
            String scoreString = "POS #\tNAME\tSCORE\tDATE\n--------------------";
            int pos = 0;
	    for(Score s: db.getScores()) {
                pos++;
		scoreString += ("\n" + pos + ".\t" + s.getName() + "\t" + s.getLevel() + "\t" + s.dateString());
	    }
	    setRunning(false);
	    try {
		updateThread.join();
	    } catch (InterruptedException ex) {
		System.out.println("Fail");
	    }
            
            ScoreWindow sw = new ScoreWindow(this, scoreString);
            sw.setVisible(true);
            
            //startup();
	}
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
    
    public boolean isRunning() {
	return running;
    }
    
    public void setRunning(boolean running) {
	this.running = running;
    }

    @Override
    public void run() {   
	while(isRunning()) {
	    pause(tickInterval);
	    update();
	}
    }
}