package dev.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dev.run.ConvertNumber;

public class ConvertNumberTest {

	private ConvertNumber a;
	
	@Before
	public void setup() {
		a = new ConvertNumber();
	}
	
	@Test
	public void test1() {
		assertEquals("999", a.convertNumer(999L));
	}
	
	@Test
	public void test2() {
		assertEquals("1.00k", a.convertNumer(1000L));
	}
	
	@Test
	public void test3() {
		assertEquals("1.46k", a.convertNumer(1459L));
	}
	
	@Test
	public void test33() {
		assertEquals("10.0k", a.convertNumer(9999L));
	}
	
	@Test
	public void test4() {
		assertEquals("17.5k", a.convertNumer(17541L));
	}
	
	@Test
	public void test5() {
		assertEquals("100k", a.convertNumer(100000L));
	}
	
	@Test
	public void test6() {
		assertEquals("123k", a.convertNumer(123400L));
	}
	
	@Test
	public void test7() {
		assertEquals("1.00m", a.convertNumer(1000000L));
	}
	
	@Test
	public void test8() {
		assertEquals("100m", a.convertNumer(100000000L));
	}
	
	@Test
	public void test9() {
		assertEquals("12.3m", a.convertNumer(12345678L));
	}
	
	@After
	public void tearDown() {
		a = null;
	}
}
