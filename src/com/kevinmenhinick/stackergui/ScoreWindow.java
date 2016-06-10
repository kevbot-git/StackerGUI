package com.kevinmenhinick.stackergui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class ScoreWindow extends JFrame {
    
    private final JTextArea scoreArea;
    
    public ScoreWindow(final StackerWindow machine, final String scoreString) {
        super("Scores");
        super.setMinimumSize(new Dimension(480, 480));
        super.setLocation(StackerWindow.getCenterPoint(this));
        super.setLayout(new BorderLayout());
        super.setAutoRequestFocus(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
	    super.setIconImage(ImageIO.read(getClass().getResource(StackerGUI.IMG_PATH + "icon.png")));
	} catch (IOException ex) {
	    Logger.getLogger(StackerWindow.class.getName()).log(Level.SEVERE, null, ex);
	}
        
        scoreArea = new JTextArea();
        scoreArea.setMargin(new Insets(4, 4, 4, 4));
        scoreArea.setEditable(false);
        scoreArea.setText(scoreString);
        
        JScrollPane scroller = new JScrollPane(scoreArea);
        
        JPanel lowerPanel = new JPanel();
        JButton btnPlayAgain = new JButton("Play again!");
        lowerPanel.add(btnPlayAgain);
        
        super.add(scroller, BorderLayout.CENTER);
        super.add(lowerPanel, BorderLayout.SOUTH);
        
        btnPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                machine.startup();
            }
        });
    }
    
}
