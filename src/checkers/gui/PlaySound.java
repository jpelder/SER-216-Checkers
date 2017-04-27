package checkers.gui;

import java.io.File;
import java.io.IOException;

//import javax.media.j3d.Sound;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The way to hear sound effects in the game
 * SER 216 - Group 15 (Edited Initial Code)
 * Date: 4-18-17
 */
public class PlaySound extends Thread {

	private String filename;

	private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb

	enum Position {LEFT, RIGHT, NORMAL}

	public PlaySound(String wavfile) {
		filename = wavfile;
	}

	public void run() {
        if(Checkers.silent){
        	return;
        }

		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filename));
		} 
		catch(UnsupportedAudioFileException e1) {
			e1.printStackTrace();
			return;
		} 
		catch(IOException e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} 
		catch(LineUnavailableException e) {
			e.printStackTrace();
			return;
		} 
		catch(Exception e) {
			e.printStackTrace();
			return;
		}

		if(auline.isControlSupported(FloatControl.Type.PAN)) {
			FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
		}

		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

		try {
			while(nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if(nBytesRead >= 0){
					auline.write(abData, 0, nBytesRead);
				}
			}
		} 
		catch(IOException e) {
			e.printStackTrace();
        }
		finally {
			auline.drain();
			auline.close();
		}
	}
}
