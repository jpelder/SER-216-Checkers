/**
 * This is a junit test file
 * Date: 4 - 6 - 17
 * @author - 
 * @version 1.0
 */

package junit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import checkers.gui.IntelliChecker;
import checkers.gui.Checkers;

/**
 * This test case will ensure that Checkers is behaving as expected
 */
public class CheckersTest {
	IntelliChecker mainTest;
	Checkers checkers;
	String[] args;

	@Before
	public void setUp() throws Exception {
		mainTest = new IntelliChecker();
		checkers = new Checkers();
		args = new String[0];
	}

	@Test
	public void testMain() {
		try{
			mainTest.main(args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			assertTrue(mainTest != null);
		}
	}

	@Test
	public void testCheckers(){
		try{
			checkers.play();
			checkers.newGame();
			checkers.paintComponent(checkers.getGraphics());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			checkers.update(checkers.getGraphics());
			checkers.drawCheckers();
			assertEquals(1, checkers.getMouseListeners().length);
		}
	}
}