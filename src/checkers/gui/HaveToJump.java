package checkers.gui;

import javax.swing.*;
import java.awt.*;

public class HaveToJump extends JDialog{
    Point p;
    JLabel message = new JLabel();
    
    HaveToJump(Point p){
        this.p = p;
        message.setText("You must JUMP!!!");
        setupGUI();
	}

	private void setupGUI(){
		new PlaySound("/checkers/resources/sounds/error.wav").start();
        message.setFont(new Font("dialog", Font.BOLD, 16));
        add(message);

        setAlwaysOnTop(true);
        setLocation((int)p.getX()+100, (int)p.getY() + 200);
        setResizable(false);
        setSize(210, 80);
        setTitle("Warning");
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
}
