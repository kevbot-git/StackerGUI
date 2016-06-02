package com.kevinmenhinick.stackergui;

import javax.swing.ImageIcon;

class StackerCanvas extends BackgroundPanel {
    
    public static final int SPACING = 4;
    
    private ImageIcon bg;
    private Pixel[][] pixels;
    private int xPixels;
    private int yPixels;

    public StackerCanvas(String bgFilename, String pixelFilename, int xPixels, int yPixels) {
	super(bgFilename);
	this.xPixels = xPixels;
	this.yPixels = yPixels;
	this.pixels = new Pixel[yPixels][xPixels];
	
	initArray();
    }
    
    private void initArray() {
	for(int y = 0; y < yPixels; y++) {
	    for(int x = 0; x < xPixels; x++) {
		pixels[y][x] = new Pixel(true, x, y);
		pixels[y][x].setVisible(false);
		this.add(pixels[y][x], indexToCoord(x), indexToCoord(y));
	    }
	}
    }
    
    private int indexToCoord(int index) {
	return (index * (32 + SPACING)) + SPACING;
    }
    
    public void render(PixelStack stack) {
	for(int y = 0; y < yPixels; y++) {
	    for(int x = 0; x < xPixels; x++) {
		boolean state = false;
		try {
		    state = stack.getStack().get(y)[x];
		} catch(java.lang.ArrayIndexOutOfBoundsException e) {
		    
		}
		pixels[y][x].setVisible(state);
	    }
	}
    }

}