package checkers.gui;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Point;
import java.awt.Font;

public class PlayerPopUp extends JDialog {
	
	Point point;
	JLabel message = new JLabel();
	
	PlayerPopUp(String player, Point p){
		point = p;
		message.setText(player + " is first!");
		setupGUI();
	}
	
	private void setupGUI(){
		new PlaySound("../resources/sounds/option.wav");
		message.setFont(new Font("dialog", Font.BOLD, 16));
		add(message);
		
		setAlwaysOnTop(true);
		setLocation((int)point.getX()+ 100, (int)point.getY() + 200);
		setResizable(false);
		setSize(210,80);
		setTitle("Player");
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

}
