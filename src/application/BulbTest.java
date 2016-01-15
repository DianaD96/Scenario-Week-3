package application;



import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;

public class BulbTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBulb() {
		Bulb b = new Bulb();
		assertThat(b.getResistance(),is(5.0));
	}

	@Test
	public void compareBulb() {
		Bulb a = new Bulb();
		Bulb c = new Bulb();
		assertEquals("Not yet implemented", a.getResistance(), c.getResistance(), 0);
	}

}
