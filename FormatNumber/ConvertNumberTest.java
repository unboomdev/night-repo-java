package dev.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dev.ConvertNumber;

public class ConvertNumberTest {

	private ConvertNumber a;

	@Before
	public void setup() {
		a = new ConvertNumber();
	}

	@Test
	public void test1() {
		assertEquals("999", a.formatNumber(999L));
	}

	@Test
	public void test2() {
		assertEquals("1K", a.formatNumber(1000L));
	}

	@Test
	public void test3() {
		assertEquals("1.46K", a.formatNumber(1459L));
	}

	@Test
	public void test33() {
		assertEquals("10K", a.formatNumber(9999L));
	}

	@Test
	public void test4() {
		assertEquals("17.5K", a.formatNumber(17541L));
	}

	@Test
	public void test5() {
		assertEquals("100K", a.formatNumber(100000L));
	}

	@Test
	public void test6() {
		assertEquals("123K", a.formatNumber(123400L));
	}

	@Test
	public void test7() {
		assertEquals("1M", a.formatNumber(1000000L));
	}

	@Test
	public void test8() {
		assertEquals("100M", a.formatNumber(100000000L));
	}

	@Test
	public void test9() {
		assertEquals("12.3M", a.formatNumber(12345678L));
	}

	@After
	public void tearDown() {
		a = null;
	}
}
