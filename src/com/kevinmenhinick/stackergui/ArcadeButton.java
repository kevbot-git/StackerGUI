package com.kevinmenhinick.stackergui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ArcadeButton extends JButton {
    
    private Image onImage;
    private Image offImage;
    private Listener listener;
    
    public ArcadeButton(String offImageName, String onImageName) {
	try {
	    onImage = ImageIO.read(getClass().getResource(StackerGUI.IMG_PATH + onImageName));
	    offImage = ImageIO.read(getClass().getResource(StackerGUI.IMG_PATH + offImageName));
	    
	    super.setSize(offImage.getWidth(this), offImage.getHeight(this));
	    super.setFocusPainted(false);
	    super.setBorder(BorderFactory.createEmptyBorder());
	    super.setContentAreaFilled(false);
	    super.setIcon(new ImageIcon(offImage));
	    super.setPressedIcon(new ImageIcon(onImage));
            
            super.setFocusable(false);
	    
	} catch (IOException e) {
	    System.err.println("image not found");
	}
    }
    
    public Listener getListener() {
	return this.listener;
    }
    
    public void setListener(Listener listener) {
	super.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		releaseButton();
	    }
	    
	});
	
	super.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
		pressButton();
	    }
	    
	});
	
	this.listener = listener;
    }
    
    public void pressButton() {
	StackerGUI.getKeyDownSound().play();
	getListener().onPress();
    }
    
    public void releaseButton() {
	StackerGUI.getKeyUpSound().play();
	getListener().onRelease();
    }
    
    public interface Listener {
	public void onPress();
	public void onRelease();
    }
    
}