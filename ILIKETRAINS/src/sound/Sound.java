package sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * A zenélést végző osztály <3
 */
public class Sound {
	
	/** A fájl elérési útvonala */
	File soundFile;
	
	/** Betöltéshez szükséges osztály */
	AudioInputStream audioIn;
	
	/** A betöltött audiofájl */
	Clip clip;
	
	/**
	 * Konstruktor, ahol betöltjük a fájlt
	 */
	public Sound(){
		try {
		soundFile = new File("res/iliketrains.wav");
        audioIn = AudioSystem.getAudioInputStream(soundFile);              
        clip = AudioSystem.getClip();
        
			clip.open(audioIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Zene elindítása
	 */
	public void start(){
		clip.start();
	}
	
	/**
	 * Zene megállítása és kezdeti pozcióba állítása
	 */
	public void stop(){
		clip.stop();
		clip.setFramePosition(0);
	}

}
