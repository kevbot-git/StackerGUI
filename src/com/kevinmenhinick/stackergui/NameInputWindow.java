package com.kevinmenhinick.stackergui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class NameInputWindow extends JFrame {

    private JTextField textField;
    
    public NameInputWindow(String message, final ArrayList<String> answer) {
        super(message);
        super.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JButton btn = new JButton("GO!");
        textField = new LimitedTextField(3);
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Name: "));
        panel.add(textField);
        panel.add(btn);
        super.add(panel, BorderLayout.CENTER);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText().length() > 0)
                    submit(answer);
            }
        });
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText().length() > 0)
                    submit(answer);
            }
            
        });
        super.setMinimumSize(new Dimension(300, 100));
        super.setLocation(StackerWindow.getCenterPoint(this));
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
	    super.setIconImage(ImageIO.read(getClass().getResource(StackerGUI.IMG_PATH + "icon.png")));
	} catch (IOException ex) {
	    Logger.getLogger(StackerWindow.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    private void submit(final ArrayList<String> answer) {
        synchronized(answer) {
            answer.add(textField.getText());
            answer.notify();
        };
        dispose();
    }
    
    private class LimitedTextField extends JTextField {
        
        private PlainDocument pd;
        
        public LimitedTextField(final int maxLength) {
            super();
            super.setColumns(maxLength + 2);
            super.setMargin(new Insets(4, 4, 4, 4));
            super.setDocument(pd = new PlainDocument() {
                
                @Override
                public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                    if(str == null) return;
                    if(getLength() + str.length() <= maxLength) {
                        super.insertString(offs, str.toUpperCase(), a);
                    }
                }
                
            });
        }
    }
}
