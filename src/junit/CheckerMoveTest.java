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
	
	private CheckerMove move;

	@Before
	public void setUp() throws Exception {
		move = new CheckerMove();
	}

	@Test
	public void gettingCorrectIndex() {
		
		int[] indexOne = move.getIndex(0,0);
		int[] indexTwo = move.getIndex(399, 399);
		int[] indexThree = move.getIndex(215, 200);

		assertEquals("The index is equivalent",0,indexOne[0]);
		assertEquals("The index is equivalent",0,indexOne[1]);	
		
		assertEquals("The index is equivalent",7,indexTwo[0]);
		assertEquals("The index is equivalent",7,indexTwo[1]);
		
		assertNotSame("Index is not equivalent to given variables",7,indexThree[0]);
		assertNotSame("Index is not equivalent to givent variables",6,indexThree[1]);
		
	}
	
	@Test
	public void checkForColourVariable(){
		
		int colorOne = move.colour(1);
		int colorTwo = move.colour(3);
		int colorThree = move.colour(0);
		int colorFour = move.colour(4);
		
		assertEquals("This is checker piece for color red",1,colorOne);
		assertNotSame("This is checker piece for color yellow",2,colorTwo);
		assertEquals("This is an empty location",0,colorThree);
		assertNotSame("This is an empty location",0,colorFour);
	}
	
	@Test
	public void canCapturePieceOnBoard(){
		//Red = 1
		//Yellow = 2
		//Red King = 3
		//Yellow King = 4
		//Empty = 0
		
		int[][] boardOne = new int[8][8];
		int[][] boardTwo = new int[8][8];
		int[][] boardThree = new int[8][8];
		int[][] boardFour = new int[8][8];
		int[][] boardFive = new int[8][8];
		int[][] boardSix = new int[8][8];
		
		
		boardOne[5][4] = 2;
		boardOne[6][3] = 1;
		boardOne[1][4] = 4;
		boardOne[2][5] = 3;
		
		boardTwo[3][6] = 4;
		boardTwo[2][5] = 1;
		
		boardThree[0][5] = 2;
		boardThree[1][4] = 1;
		
		boardFour[2][3] = 4;
		boardFour[3][4] = 1;
		
		boardFive[3][3] = 3;
		boardFive[2][2] = 2;
		boardSix[6][6] = 4;
		boardSix[5][5] = 1;

		
		assertTrue(move.canCapture(boardOne, 1));
		assertTrue(move.canCapture(boardOne, 2));
		assertFalse(move.canCapture(boardOne, 4));
		assertFalse(move.canCapture(boardOne, 3));
		
		assertTrue(move.canCapture(boardTwo, 1));
		assertFalse(move.canCapture(boardTwo, 4));
		
		assertFalse(move.canCapture(boardThree, 1));
		assertTrue(move.canCapture(boardThree, 2));
		
		assertFalse(move.canCapture(boardFour, 1));
		
		assertFalse(move.canCapture(boardFive,2));
		assertTrue(move.canCapture(boardFive, 1));
	}
	
	@Test
	public void isAnEmptyLocationTest(){
		int[][] board = new int[8][8];
		board[4][3] = 1;
		board[4][2] = 4;
		
		assertFalse("Not an empty location",move.isEmpty(board, 4, 3));
		assertTrue("An empty location",move.isEmpty(board,3,2));
	}
	
	@Test
	public void isThisALegalMoveTest(){
		int[][] board = new int[8][8];
		
		board[3][5] = 2; //Yellow
		board[5][5] = 1; //Red
		board[2][5] = 3; //Red King
		board[6][5] = 4; //Yellow King
		
		assertEquals("This is a legal move for a normal yellow",1,move.isMoveLegal(board,3,5,4,4,2));
		assertEquals("This move is illegal for a normal yellow due to out of range",2,move.isMoveLegal(board, 3, 5, 0, 7, 2));
		assertEquals("This move is illegal for a normal yellow due to going backwards",2,move.isMoveLegal(board,3,5,2,6,2));
		
		assertEquals("This is a legal move for a normal red",1,move.isMoveLegal(board, 5, 5, 4, 6, 1));
		assertEquals("This is illegal move for a normal red due to out of range",2,move.isMoveLegal(board, 5, 5, 0, 7, 1));
		assertEquals("This is illegal move for a normal red due to backwards",2,move.isMoveLegal(board, 5, 5, 4, 4, 1));
		
		assertEquals("This is a legal move for King Red",1,move.isMoveLegal(board, 2, 5, 1, 4, 3));
		assertEquals("This is a legal move for Kind Red",1,move.isMoveLegal(board, 2, 5, 1, 6, 3));
		assertEquals("This is illegal move for King Red",2,move.isMoveLegal(board, 2, 5, 8, 8, 3));
		
		assertEquals("This is a legal move for King Yellow",1,move.isMoveLegal(board, 6, 5, 5, 4, 4));
		assertEquals("This is a legal move for Kind Yellow",1,move.isMoveLegal(board, 6, 5, 5, 6, 4));
		assertEquals("This is illegal move for King Yellow",2,move.isMoveLegal(board, 6, 5, 8, 8, 4));
	}

	@Test
	public void canAPieceWalkTest(){

		int[][] boardOne = new int[8][8];
		int[][] boardTwo = new int[8][8];
		
		
		boardOne[5][6] = 2;
		boardOne[4][5] = 2;
		boardOne[6][5] = 2;
		boardOne[4][7] = 2;
		boardOne[6][7] = 2;
		
		assertFalse("This piece is boxed in",move.canWalk(boardOne,5,6));
		assertTrue("This piece can move",move.canWalk(boardOne, 4, 5));
		
		boardOne[5][6] = 4;
		boardOne[4][5] = 4;
		
		assertFalse("This piece is boxed in",move.canWalk(boardOne,5,6));
		assertTrue("This piece can move",move.canWalk(boardOne, 4, 5));
		
		boardTwo[5][6] = 1;
		boardTwo[4][5] = 1;
		boardTwo[6][5] = 1;
		boardTwo[4][7] = 1;
		boardTwo[6][7] = 1;
		
		assertFalse("This piece is boxed in",move.canWalk(boardTwo,5,6));
		assertTrue("This piece can move",move.canWalk(boardTwo, 4, 5));
		
		boardTwo[5][6] = 3;
		boardTwo[4][5] = 3;
		
		assertFalse("This piece is boxed in",move.canWalk(boardTwo,5,6));
		assertTrue("This piece can move",move.canWalk(boardTwo, 4, 5));
	}
	
	@Test
	public void canApplyTheMoveTest(){
		
		
	}

}
