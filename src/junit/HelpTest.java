package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import checkers.gui.Help;

public class HelpTest {

	@Test
	public void buildHelpMenu() {
		
		assertNotNull(new Help());
	}
}
