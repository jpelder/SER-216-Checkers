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

import checkers.logic.CheckerMove;
import checkers.gui.Checkers;


/**
 * This test case will ensure that CheckerMove is working correctly
 */
public class CheckerMoveTest {
	
	// Declaration of test variables
	CheckerMove moveTest;
	int[][] board;

	/**
	 * Initialization of variables that are needed for testing
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		moveTest = new CheckerMove();
		board = new int[8][8];
	}
	
	@Test
	public void testCanWalk(){
		
	}

}
