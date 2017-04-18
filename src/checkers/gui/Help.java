package checkers.gui;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.awt.Font;

public class Help extends JDialog {
    JScrollPane hlp = new JScrollPane();
    JTextArea txt = new JTextArea();

    public Help(){
        setupGUI();
    }

    private void setupGUI() {
        hlp.setViewportView(txt);
        txt.setEditable(false);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setFont(new Font("Dialog", Font.PLAIN, 14));
        hlp.getHorizontalScrollBar().setEnabled(false);
        addText();        
        add(hlp);


        setLocation((int)getToolkit().getScreenSize().getWidth() / 2 - 250, (int)getToolkit().getScreenSize().getHeight() / 2 - 300);
        setVisible(false);
        add(hlp);
        setSize(500, 600);
        setResizable(false);
        setTitle("How To Play");
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }

    private void addText() {
        String str;
        try{
            BufferedReader b = new BufferedReader(new FileReader(new File("HowToPlay.txt")));
            try{
                while((str = b.readLine()) != null)
                    txt.append(str + "\n");
            } 
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
