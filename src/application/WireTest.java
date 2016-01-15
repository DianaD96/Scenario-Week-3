package application;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WireTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testWire() {
		Wire wire = new Wire();
		assertNotNull(wire);
	}

	@Test
	public void testGetSecondOutput() {
		Wire wire1 = new Wire();
		Wire wire2 = new Wire();
		wire1.setSecondOutput(wire2);
		assertThat(wire1.getSecondOutput(),is(wire2));
	}

	@Test
	public void testGetSecondInput() {
		Wire wire1 = new Wire();
		Wire wire2 = new Wire();
		wire1.setSecondInput(wire2);
		assertThat(wire1.getSecondInput(),is(wire2));
	}

	@Test
	public void testSetSecondOutput() {
		Wire wire1 = new Wire();
		Wire wire2 = new Wire();
		wire1.setSecondOutput(wire2);
		assertThat(wire1.getSecondOutput(),is(wire2));
		Wire wire3 = new Wire();
		wire1.setSecondOutput(wire3);
		assertThat(wire1.getSecondOutput(),is(wire3));
	}

	@Test
	public void testSetSecondInput() {
		Wire wire1 = new Wire();
		Wire wire2 = new Wire();
		wire1.setSecondInput(wire2);
		assertThat(wire1.getSecondInput(),is(wire2));
		Wire wire3 = new Wire();
		wire1.setSecondInput(wire3);
		assertThat(wire1.getSecondInput(),is(wire3));
	}

	@Test
	public void testHasSecondOutput() {
		Wire wire1 = new Wire();
		Wire wire2 = new Wire();
		wire1.setSecondOutput(wire2);
		assertTrue(wire1.hasSecondOutput());
	}

	@Test
	public void testHasSecondInput() {
		Wire wire1 = new Wire();
		Wire wire2 = new Wire();
		wire1.setSecondInput(wire2);
		assertTrue(wire1.hasSecondInput());
	}

}
