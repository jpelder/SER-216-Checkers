package junit;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import checkers.gui.GameWin;

public class GameWinTest {
	
	GameWin gameWin;// = null;
	Point point;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		point = new Point();
		gameWin = new GameWin("Test Player", point);
	}

	@After
	public void tearDown() throws Exception {
		point = null;
		gameWin = null;
	}

	@Test
	public void test() {
		assertTrue (gameWin.getPoint().equals(point));
	}

}