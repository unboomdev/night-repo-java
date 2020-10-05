package dev;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FizzBuzzTest {

	private FizzBuzz fb;

	@Before
	public void setUp() {
		fb = new FizzBuzz();
	}

	@Test
	public void oneReturnsOne() {
		assertEquals("1", fb.evaluate(1));
	}

	@Test
	public void twoReturnsTwo() {
		assertEquals("2", fb.evaluate(2));
	}

	@Test
	public void threeReturnsFizz() {
		assertEquals("Fizz", fb.evaluate(3));
	}

	@Test
	public void fiveReturnsBuzz() {
		assertEquals("Buzz", fb.evaluate(5));
	}

	@Test
	public void sixReturnsFizz() {
		assertEquals("Fizz", fb.evaluate(6));
	}

	@Test
	public void tenReturnsBuzz() {
		assertEquals("Buzz", fb.evaluate(10));
	}

	@Test
	public void fifteenREturnsFizzBuzz() {
		assertEquals("FizzBuzz", fb.evaluate(15));
	}

	@Test
	public void thirtyReturnsFizzBuzz() {
		assertEquals("FizzBuzz", fb.evaluate(30));
	}

	@After
	public void tearDown() {
		fb = null;
	}
}
