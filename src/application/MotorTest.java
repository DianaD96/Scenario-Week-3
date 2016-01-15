package application;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

public class MotorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMotor() {
		Motor m = new Motor(10);
		assertTrue(m != null);
		assertThat(m.getResistance(),is(10.0));
	}

}
