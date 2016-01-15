package application;



import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BatteryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBattery() {
		Battery b = new Battery(10);
		assertThat(b.getVoltage(),is(10.0));
	}

	@Test
	public void checkSameBattery() {
		Battery a = new Battery(5);
		Battery c = new Battery(5);
		assertEquals("Not yet implemented", a.getVoltage(), c.getVoltage(), 0);
	}
	
	@Test
	public void checkDifferentBatteries(){
		Battery b = new Battery(5);
		Battery d = new Battery(10);
		assertNotEquals("Not yet implemented", d.getVoltage(), b.getVoltage());		
	}
}
