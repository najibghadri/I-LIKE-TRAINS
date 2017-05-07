package sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	File soundFile;
	AudioInputStream audioIn;
	Clip clip;
	
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
	
	public void start(){
		clip.start();
	}
	
	public void stop(){
		clip.stop();
		clip.setFramePosition(0);
	}

}
