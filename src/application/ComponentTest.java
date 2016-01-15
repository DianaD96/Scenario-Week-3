package application;



import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComponentTest {

	@Before
	public void setUp() throws Exception {
		Component comp1 = new Component(10, 20, 30);
		Component comp2 = new Component(20,30,40);
	}

	@Test
	public void testConstructor() {
		Component comp1 = new Component(10, 20, 30);
		assertThat(comp1.getPositionX(),is(10.0));
		assertThat(comp1.getPositionY(),is(20.0));
	}
	
	@Test
	public void testGetId() {
		Component comp1 = new Component(10, 20, 30);
		assertNull(comp1.getId());
	}

	@Test
	public void testSetId() {
		Component comp1 = new Component(10,20,30);
		comp1.setId("Blah");
		assertThat(comp1.getId(), is("Blah"));
	}

	@Test
	public void testGetInput() {
		Component comp1 = new Component(10, 20, 30);
		assertNull(comp1.getInput());
	}

	@Test
	public void testGetOutput() {
		Component comp1 = new Component(10, 20, 30);
		assertNull(comp1.getOutput());
	}

	@Test
	public void testGetVoltage() {
		Component comp1 = new Component(10, 20, 30);
		comp1.setVoltage(1);
		assertThat(comp1.getVoltage(),is(1.0));
	}

	@Test
	public void testGetResistance() {
		Component comp1 = new Component(10, 20, 30);
		comp1.setResistance(2);
		assertThat(comp1.getResistance(),is(2.0));
		}

	@Test
	public void testGetCurrent() {
		Component comp1 = new Component(10, 20, 30);
		comp1.setCurrent(3);
		assertThat(comp1.getCurrent(),is(3.0));
	}

	@Test
	public void testGetPositionX() {
		Component comp1 = new Component(10, 20, 30);
		assertThat(comp1.getPositionX(),is(10.0));
	}

	@Test
	public void testSetVoltage() {
		Component comp1 = new Component(10, 20, 30);
		comp1.setVoltage(100);
		assertThat(comp1.getVoltage(),is(100.0));
	}

	@Test
	public void testSetCurrent() {
		Component comp1 = new Component(10, 20, 30);
		comp1.setCurrent(200);
		assertThat(comp1.getCurrent(),is(200.0));
	}

	@Test
	public void testSetResistance() {
		Component comp1 = new Component(10, 20, 30);
		comp1.setResistance(300);
		assertThat(comp1.getResistance(),is(300.0));
	}

	@Test
	public void testSetImage() {
		Component comp1 = new Component(10, 20, 30);
		comp1.setImage("imageA");
		assertThat(comp1.getImage(),is("imageA"));
	}

	@Test
	public void testSetPositionX() {
		Component comp1 = new Component(10, 20, 30);
		comp1.setPositionX(300);
		assertThat(comp1.getPositionX(),is(300.0));
	}

	@Test
	public void testSetInput() {
		Component comp1 = new Component(10, 20, 30);
		Component comp2 = new Component(30,20,10);
		comp1.setInput(comp2);
		comp2.setInput(comp1);
		assertThat(comp1.getInput(),is(comp2));
		assertThat(comp2.getInput(),is(comp1));
	}

	@Test
	public void testSetOutput() {
		Component comp1 = new Component(10, 20, 30);
		Component comp2 = new Component(30,20,10);
		comp1.setOutput(comp2);
		comp2.setOutput(comp1);
		assertThat(comp1.getOutput(),is(comp2));
		assertThat(comp2.getOutput(),is(comp1));
	}

}
