package checkers.gui;

/**
 * The class which contains the 'main' method
 * SER 216 - Group 15 (Edited Initial Code)
 * Date: 4-18-17
 */
public class IntelliChecker {
    public static void main(String args[]) throws Exception {
        java.awt.EventQueue.invokeLater(new Runnable() {
        	public void run() {
        		new CheckerFrame();
        	}
        });
    }
}