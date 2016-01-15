package application;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BatteryTest.class, BulbTest.class, ComponentTest.class,
		MotorTest.class, ResistorTest.class, WireTest.class })
public class AllTests {

}
