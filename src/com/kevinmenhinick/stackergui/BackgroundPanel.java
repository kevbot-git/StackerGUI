package com.kevinmenhinick.stackergui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
    
    private ArrayList<Image> bg;
    private Image selectedBG;
    
    public BackgroundPanel(String imgFilename) {
	super();
	super.setLayout(null);
	bg = new ArrayList<Image>();
	try {
	    selectedBG = ImageIO.read(getClass().getResource(StackerGUI.IMG_PATH + imgFilename));
	    bg.add(selectedBG);
	    super.setSize(selectedBG.getWidth(this), selectedBG.getHeight(this));
	} catch (IOException e) {
	    System.err.println("'" + imgFilename + "' not found");
	}
    }
    
    public BackgroundPanel(String[] imgFilenames) {
	this(imgFilenames[0]);
	for(int i = 1; i < imgFilenames.length; i++) {
	    try {
		Image temp = ImageIO.read(getClass().getResource(StackerGUI.IMG_PATH + imgFilenames[i]));
		bg.add(temp);
	    } catch (IOException e) {
		System.err.println("'" + imgFilenames[i] + "' not found");
	    }
	}
    }
    
    public void setBGIndex(int i) {
	if(i < bg.size() && i >= 0) {
	    selectedBG = bg.get(i);
	}
	this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(selectedBG, 0, 0, this);
    }
    
    public Component add(Component comp, int x, int y) {
	comp.setBounds(x, y, comp.getWidth(), comp.getHeight());
	return super.add(comp);
    }
}