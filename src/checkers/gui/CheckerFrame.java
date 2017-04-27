package checkers.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Font;

/**
 * The frame which houses the other GUI components
 * SER 216 - Group 15 (Edited Initial Code)
 * Date: 4-18-17
 */
public class CheckerFrame extends JFrame implements ActionListener, WindowListener{
    
	//static JFrame frame = new CheckerFrame();
	
	JButton stB = new JButton("Start Game");
    JPanel gmP = new StartPanel();
    Checkers gmP2 = new Checkers();
  
    public CheckerFrame(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this); //changing the appearance of the frame
        }
        catch(Exception e) {
           //no need to handle exception as it only affect the appearance
        }
        setupGUI();
        new PlaySound("../resources/sounds/Start.wav").start();
    }

    private void setupGUI() {
        setLayout(null);
        gmP.setBounds(0, 0, 508, 401);//400,401
        //gmP.imageUpdate(ne, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH
        add(gmP);
        stB.setHorizontalAlignment(SwingConstants.LEADING);
        stB.setIcon(new ImageIcon(getClass().getResource("../resources/images/checkersIcon.jpg")));
        stB.setBackground(Color.LIGHT_GRAY);
        stB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stB.setBounds(130, 415, 240, 60);
        stB.setFont(new Font("Times new roman",Font.BOLD, 20));
        stB.addActionListener(this);
        stB.setFocusPainted(false);
        add(stB);
        this.setIconImage(new ImageIcon(getClass().getResource("../resources/images/icon.jpg")).getImage());
        setSize(508, 520);
        setLocation((int)getToolkit().getScreenSize().getWidth() / 2 - 254, (int)getToolkit().getScreenSize().getHeight() / 2 - 310);
        setResizable(false);
        setVisible(true);
        setTitle("Play Checkers");
        super.addWindowListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setState(CheckerFrame.ICONIFIED);
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("Start Game")){
            ((JButton)e.getSource()).setText("New Game");
            new PlaySound("../resources/sounds/button.wav").start();
            gmP2 = new Checkers();
            gmP2.setBounds(0, 0, 508, 401);
            this.setContentPane(gmP2);
            gmP2.paintComponent(gmP2.getGraphics());
        }
    }
    
    public void windowClosing( WindowEvent e ) {

    }
    
	public void windowOpened( WindowEvent e ) {
		
	}
	
    public void windowClosed( WindowEvent e ) {

    }
    
	public void windowIconified( WindowEvent e ) {
		
	}

	public void windowDeiconified( WindowEvent e ) {
		
	}
	
	public void windowActivated( WindowEvent e ) {
		gmP2.drawCheckers();
		gmP2.update();
	}
	
	public void windowDeactivated( WindowEvent e ) {

	}   
    
}