/**
 * This is a junit test file
 * Date: 4 - 6 - 17
 * @author - 
 * @version 1.0
 */

package junit;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import checkers.gui.CheckerFrame;
import checkers.gui.Checkers;
import checkers.gui.PlaySound;
import checkers.gui.StartPanel;

/**
 * This test case will ensure that CheckerFrameTest and PlaySound are working as expected
 */
public class CheckerFrameTest{
	JButton stB = new JButton();
	JPanel gmP = new StartPanel();
	CheckerFrame cF = new CheckerFrame();
	AudioInputStream audioInputStream;
	String filename;
	PlaySound run = new PlaySound(filename);
	
	@Before
	public void setUp() throws Exception {
		CheckerFrame cF = new CheckerFrame();
	}
	
	@Test // CheckerFrame testing
	public void testSetupGUI() {
		cF.setBackground(Color.RED);
		stB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		stB.setBounds(130, 415, 240, 60);
		stB.setFont(new Font("Times new roman",Font.BOLD, 20));
		stB.setFocusPainted(false);
		gmP.setBounds(0, 0, 508, 401);	
	}
	
	@Test // PlaySound testing
	public void testRun() {
	}
}