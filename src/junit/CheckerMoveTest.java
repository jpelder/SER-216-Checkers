/**
 * This is a junit test file
 * Date: 4 - 6 - 17
 * @author - 
 * @version 1.0
 */

package junit;

import static org.junit.Assert.*;

import java.util.Vector;

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
		
		board[5][4] = 1;
		board[6][5] = 2;
		board[7][6] = 2;
		
		assertEquals("This is an illegal move for normal red",2,move.isMoveLegal(board, 5, 4, 7, 6, 1));
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
		int[][] board = new int[8][8];
		
		board[4][5] = 2;
		
		assertEquals("The move cannot be applied",2,move.ApplyMove(board, 4, 5, 5, 5));
		assertEquals("The move was applied",1,move.ApplyMove(board, 4, 5, 5, 4));
		
		board[2][5] = 2;
		board[3][4] = 1;
		
		assertEquals("The move is a capture and there for legal",1,move.ApplyMove(board, 2, 5, 4, 3));
		assertEquals("The move is illegal due to already existing piece",2,move.ApplyMove(board, 2, 5, 3, 4));
		
		board[1][1] = 2;
		
		assertEquals("This is legal move, converts piece to a Yellow King",1,move.ApplyMove(board,1,1, 0,0));
		assertEquals("This is illegal move, converts piece to a Yellow King but moves illegally",2,move.ApplyMove(board,1,1, 0,1));
		
		board[1][6] = 1;
		
		assertEquals("This is legal move, converts piece to a Yellow King",1,move.ApplyMove(board,1,6, 0,7));
		assertEquals("This is illegal move, converts piece to a Yellow King but moves illegally",2,move.ApplyMove(board,1,6, 1,7));
		
	}
	
	@Test
	public void moveGeneratorTest(){
		int[][] boardOne = new int[8][8];
		int[][] boardTwo = new int[8][8];
		
		boardOne[2][5] = 2;
		boardOne[3][4] = 1;
		
		
		Vector<int[]> moveListOne = move.generateMoves(boardOne, 1);
		
		int[] testArrayOne = new int[4];
		int[] testArrayTwo = new int[4];
		
		//Tests that the Generated move goes into the capture loop due
		//to conditional statement
		testArrayOne[0] = 3;
		testArrayOne[1] = 4;
		testArrayOne[2] = 1;
		testArrayOne[3] = 6;
		
		int[] actualArray = moveListOne.firstElement();
		assertEquals(actualArray[0],testArrayOne[0]);
		
		boardTwo[3][2] = 1;
		boardTwo [2][5] = 2;
		
		Vector<int[]> moveListTwo = move.generateMoves(boardTwo, 1);
		
		testArrayTwo[0] = 3;
		testArrayTwo[1] = 2;
		testArrayTwo[2] = 4;
		testArrayTwo[3] = 3;
		
		
		assertFalse(moveListTwo.contains(testArrayTwo));
	}
	
	@Test
	public void areThereNoMoreMovesTest(){
		int[][] board = new int[8][8];
		
		board[0][0] = 2;
		board[0][1] = 1;

		assertTrue(move.noMovesLeft(board, 2));
		assertFalse(move.noMovesLeft(board, 1));
		
		board[4][5] = 4;
		
		assertTrue(move.noMovesLeft(board, 4));
	}
	
	@Test
	public void moveComputerTest(){
		
		int[][] board = new int[8][8];
		
		board[5][2] = 1;
		int[] moveArray = {5,2,6,3};
		
		move.moveComputer(board, moveArray);
		
		assertFalse("This location is not empty",move.isEmpty(board, 6, 3));
		
	}
	@Test
	public void forceCaptureTest(){
		int[][] board = new int[8][8];
		
		board[4][3] = 1;
		board[5][4] = 2;
		board[5][6] = 2;
		
		Vector<int[]> moveList = move.generateMoves(board, 1);
		int[] moveArray = {4,3,6,5};
		move.forceCaptures(board,moveArray,moveList,10);
		
		int[] moveArrayTwo = moveList.firstElement();
		
		assertFalse(moveArrayTwo.equals(moveArray));
	}
}
