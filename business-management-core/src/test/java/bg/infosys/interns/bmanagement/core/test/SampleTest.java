package bg.infosys.interns.bmanagement.core.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SampleTest {
	@Test
	public void sampleTest() {
		assertTrue("Let's test".equalsIgnoreCase("LeT'S tEsT"));
	}
	
	@Test
	public void ciTest() {
		assertTrue(true);
	}
}
