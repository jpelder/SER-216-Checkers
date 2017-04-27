/**
 * This test suite will run all existing JUnit test cases within the package
 * Date: 4 - 6 - 17
 * @author - Jeremy elder
 * @version 1.0
 */

package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CheckerMoveTest.class, CheckersTest.class, GameEngineTest.class})
public class AllTests {
	//Dummy Class used for the @RunWith tag
}
