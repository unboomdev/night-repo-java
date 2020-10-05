package dev;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class RomanNumberTest.
 */
public class RomanNumberTest {

	private RomanNumber rn;

	@Before
	public void setUp() {
		rn = new RomanNumber();
	}

	@Test
	public void oneTwoThree() {
		assertEquals("I", rn.convertToRoman(1));
		assertEquals("II", rn.convertToRoman(2));
		assertEquals("III", rn.convertToRoman(3));
	}

	@Test
	public void four() {
		assertEquals("IV", rn.convertToRoman(4));
	}

	@Test
	public void five() {
		assertEquals("V", rn.convertToRoman(5));
	}

	@Test
	public void sixSevenEight() {
		assertEquals("VI", rn.convertToRoman(6));
		assertEquals("VII", rn.convertToRoman(7));
		assertEquals("VIII", rn.convertToRoman(8));
	}

	@Test
	public void nine() {
		assertEquals("IX", rn.convertToRoman(9));
	}

	@After
	public void tearDown() {
		rn = null;
	}
}
