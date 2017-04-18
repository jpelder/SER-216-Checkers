package checkers.gui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

/**
 * The modeling of the first screen
 * SER 216 - Group 15 (Edited Initial Code)
 * Date: 4-18-17
 */
public class StartPanel extends JPanel{
    
    public StartPanel(){
        setupGUI();
    }

    private void setupGUI(){
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
        g.drawLine(0, 400, 508, 400);
        ///////////////////////////////////////////////////////////////////////
        g.setColor(Color.RED);
        g.setFont(new Font("Times new roman", Font.BOLD, 40));
        g.drawString("CHECKERS", 220, 60);
        /////////////////////////////////////////////////////////////////////////
        g.setColor(Color.BLUE);
        g.setFont(new Font("Times new roman", Font.BOLD, 14));
        g.drawString("CS 3230 INTELLIGENT SYSTEMS", 180, 90);
        //////////////////////////////////////////////////////////////////////
        g.setFont(new Font("Times new roman", Font.BOLD, 12));
        g.setColor(Color.BLACK);
        g.drawString("A.M.H.H. ABEYKOON", 185, 115);
        g.drawString("070002V", 325, 115);
        g.drawString("B.P.P. FERNANDO", 185, 135);
        g.drawString("070123R", 325, 135);
        g.drawString("C.S.N.J. FERNANDO", 185, 155);
        g.drawString("070125B", 325, 155);
        g.drawString("K.C.B. GAJASINGHE", 185, 175);
        g.drawString("070137M", 325, 175);
        
        //SER 215 Credits
        g.setColor(Color.BLUE);
        g.setFont(new Font("Times new roman", Font.BOLD, 14));
        g.drawString("SER 216 - GROUP 15", 205, 200);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times new roman", Font.BOLD, 12));
        g.drawString("Jeremy Elder", 220, 220);
        g.drawString("Brent Russon", 220, 240);
        g.drawString("Wesley Davis", 220, 260);
        g.drawString("Pedro Robles", 220, 280);
        ////////////////////////////////////////////////////////////////////////
        g.drawImage(new ImageIcon(getClass().getResource("/images/checkersIcon.jpg")).getImage(), 350, 280, this);//checkersIcon.jpg


        g.setColor(new Color(0, 0, 0));
        //g.drawLine(0, 200, 200, 400);
        //g.draw3DRect(0, 0, 50, 400, true);
        
        for(int i = 0; i < 8; i++)  {
            if(i % 2 == 0){
                g.fill3DRect(0, i*50, 50, 50, true);
                g.fill3DRect(100, i*50, 50, 50, true);
            }
            else{
                g.fill3DRect(50, i*50, 50, 50, true);
            }
            //g.fillPolygon(new int[]{80 * i, 40+80*i, 80 * i, -40+80*i},new int[]{200+80*i, 240+80*i,280+80*i, 240+80*i},4);
            //g.fillPolygon(new int[]{80 * i, 40+80*i, 80 * i, -40+80*i},new int[]{280+80*i, 320+80*i,360+80*i, 320+80*i},4);
            //g.fillPolygon(new int[]{80 * i, 40+80*i, 80 * i, -40+80*i},new int[]{360+80*i, 400+80*i,420+80*i, 400+80*i},4);
        }
        g.drawLine(150, 0, 150, 400);
    }
}
