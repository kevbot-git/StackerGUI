package com.kevinmenhinick.stackergui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ArcadeButton extends JButton {
    
    private Image onImage;
    private Image offImage;
    private ActionListener actionListener;
    
    public ArcadeButton(String onImageName, String offImageName) {
	try {
	    onImage = ImageIO.read(new File(onImageName));
	    offImage = ImageIO.read(new File(offImageName));
	    
	    super.setSize(offImage.getWidth(this), offImage.getHeight(this));
	    super.setFocusPainted(false);
	    super.setBorder(BorderFactory.createEmptyBorder());
	    super.setContentAreaFilled(false);
	    
	    super.setIcon(new ImageIcon(offImage));
	    
	    this.actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    //setIcon();
		}
	    };
	    
	    super.addActionListener(actionListener);
	} catch (IOException e) {
	    System.err.println("image not found");
	}
    }
    
}