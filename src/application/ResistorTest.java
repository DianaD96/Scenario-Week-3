package application;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;

public class ResistorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testResistor() {
		Resistor resistor1 = new Resistor(100);
		assertThat(resistor1.getResistance(),is(100.0));
	}

}
