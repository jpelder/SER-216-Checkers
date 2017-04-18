/**
 * This is a junit test file
 * Date: 4 - 6 - 17
 * @author - 
 * @version 1.0
 */

package junit;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import checkers.gui.Checkers;
import checkers.logic.GameEngine;

/**
 * This test case will ensure that GameEngine is functioning appropriately
 */
public class GameEngineTest {

	//This entire GameEngine class is static so there is no need to initialize GameEngine or call a GameEngine constructor here
	//Checkers checkers = new Checkers();
	
	int[][] boardEmpty = new int[8][8];
	int[][] boardRed = new int[8][8];
	int[][] boardYel = new int[8][8];
	int[][] boardRedKing = new int[8][8];
	int[][] boardYelKing = new int[8][8];
	int[][] copyBoard = new int[8][8];
	int inf = Integer.MAX_VALUE;
	@Before
	public void setUp() throws Exception {
		for (int i =0; i<8 ; i++){
			for (int j=0 ; j<8; j++){
				boardRed[i][j]=(Checkers.redNormal);
				boardYel[i][j]=(Checkers.yellowNormal);
				boardRedKing[i][j]=(Checkers.redKing);
				boardYelKing[i][j]=(Checkers.yellowKing);
			}
		}
	}
		@After
	public void tearDown() throws Exception {
			boardEmpty = null;
			boardRed = null;
			boardYel = null;
			boardRedKing = null;
			boardYelKing = null;
			copyBoard = null;
			inf = 0;		
	}
		//This entire GameEngine class is static so there is no need to do any GameEngine set up here.

	@Test
	public void test() 
	{
		assertTrue (GameEngine.eval(boardEmpty) == 0);
		assertTrue (GameEngine.eval(boardRed) == -7520);
		assertTrue (GameEngine.eval(boardYel) == 7520);
		assertTrue (GameEngine.eval(boardRedKing) == -12480);
		assertTrue (GameEngine.eval(boardYelKing) == 12480);
		
		//This tests the checkers board 
		copyBoard = GameEngine.copyBoard(boardRed);
		for (int i =0; i<8 ; i++){
			for (int j=0 ; j<8; j++){
					assertTrue (copyBoard[i][j] == Checkers.redNormal);
			}
		}
		
		assertTrue (GameEngine.MinMax(boardRed, 1, 2, boardRed[0],1,boardRed[2]) == inf);
		
		//Changing if depth is equal and whose turn it is. 
		//These values are quite different from actual usage, as this example would not come up,
		//but it provides a way to test extreme cases and all parts of the code.
		assertTrue (GameEngine.MinMax(boardRed, 1, 2, boardRed[0],1,boardRed[0], inf, -inf) == inf );
		assertTrue (GameEngine.MinMax(boardRed, 1, 1, boardRed[0],1,boardRed[0], inf, -inf) == -7022);
		assertTrue (GameEngine.MinMax(boardRed, 1, 2, boardRed[0],2,boardRed[0], inf, -inf) == -inf);
		assertTrue (GameEngine.MinMax(boardRed, 1, 2, boardRed[0],2,boardRed[0], inf, -inf) == -inf);
		
		assertTrue (GameEngine.MinMax(boardRed, 0, 2, boardRed[0],1,boardRed[2]) == -inf);
		assertTrue (GameEngine.MinMax(boardRed, 0, 0, boardRed[0],1,boardRed[2]) == -7405);
		assertTrue (GameEngine.MinMax(boardRed, 0, 2, boardRed[0],2,boardRed[2]) == 0);
		assertTrue (GameEngine.MinMax(boardRed, 0, 2, boardRed[0],2,boardRed[2]) == 0);
		
		assertTrue (GameEngine.MinMax(boardYel, 1, 2, boardYel[0],1,boardYel[0], inf, -inf) == inf );
		assertTrue (GameEngine.MinMax(boardYel, 1, 1, boardYel[0],1,boardYel[0], inf, -inf) == 7191);
		assertTrue (GameEngine.MinMax(boardYel, 1, 2, boardYel[0],2,boardYel[0], inf, -inf) == 7152);
		assertTrue (GameEngine.MinMax(boardYel, 1, 2, boardYel[0],2,boardYel[0], inf, -inf) == inf);
		
		assertTrue (GameEngine.MinMax(boardYel, 0, 2, boardYel[0],1,boardYel[2]) == inf);
		assertTrue (GameEngine.MinMax(boardYel, 0, 0, boardYel[0],1,boardYel[2]) == 6481);
		assertTrue (GameEngine.MinMax(boardYel, 0, 2, boardYel[0],2,boardYel[2]) == inf);
		assertTrue (GameEngine.MinMax(boardYel, 0, 2, boardYel[0],2,boardYel[2]) == inf);
		
		
		
		
		//Simple methods, take an int and return an int.
		assertTrue(GameEngine.getOpponent(4) == 2);
		assertTrue(GameEngine.getOpponent(3) == 2);
		assertTrue(GameEngine.getTurn(2) == -inf);
		//System.out.println(GameEngine.getTurn(1));
		assertTrue(GameEngine.getTurn(1) == inf);
	}

}
