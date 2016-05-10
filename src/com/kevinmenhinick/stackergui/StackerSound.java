package com.kevinmenhinick.stackergui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class StackerSound {

    private Clip clip;
    
    public StackerSound(String filename) {
	try {
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(StackerGUI.AUDIO_PATH + filename));
	    clip = AudioSystem.getClip();
	    clip.open(audioInputStream);
	} catch(UnsupportedAudioFileException e) {
	    System.out.println("Not supported");
	} catch (IOException | LineUnavailableException ex) {
	    Logger.getLogger(StackerSound.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public void play() {
	if(clip != null) {
	    clip.start();
	    clip.setFramePosition(0);
	}
    }
    
}