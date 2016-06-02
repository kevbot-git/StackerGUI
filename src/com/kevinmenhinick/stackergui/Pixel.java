package com.kevinmenhinick.stackergui;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pixel extends JLabel {

    public static final String FILENAME = "pixel.png";
    
    private ImageIcon image;
    
    private int xIndex;
    private int yIndex;
    
    public Pixel(boolean shown, int xIndex, int yIndex) {
	try {
	    Image temp = ImageIO.read(getClass().getResource(StackerGUI.IMG_PATH + FILENAME));
	    image = new ImageIcon(temp);
	    super.setSize(temp.getWidth(this), temp.getHeight(this));
	    super.setIcon(image);
	} catch (IOException e) {
	    //
	}
    }
    
    public Pixel(int xIndex, int yIndex) {
	this(false, xIndex, yIndex);
    }
    
}