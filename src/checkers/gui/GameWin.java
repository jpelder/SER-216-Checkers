package checkers.gui;

import javax.swing.JLabel;
import javax.swing.JDialog;

import java.awt.Point;
import java.awt.Font;

/**
 * The 'game over' pop up box
 * SER 216 - Group 15 (Edited Initial Code)
 * Date: 4-18-17
 */
public class GameWin extends JDialog{
    Point p;
    JLabel masseage = new JLabel();
    
    public GameWin(String winner, Point p){
        this.p = p;
        masseage.setText("          " + winner + " Wins!");
        setupGUI();
	}

	private void setupGUI(){
		new PlaySound("/checkers/resources/sounds/Win.wav").start();
        masseage.setFont(new Font("dialog", Font.BOLD, 16));
        add(masseage);

        setAlwaysOnTop(true);
        setLocation((int)p.getX()+100, (int)p.getY() + 200);
        setResizable(false);
        setSize(200, 80);
        setTitle("Game Over");
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
	
	public Point getPoint(){
		return p;
	}

}
