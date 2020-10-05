package dev;

public class RomanNumber {

	private static final String[] SYMBOLS = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
	private static final int[] VALUES = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };

	public String convertToRoman(int n) {

		StringBuilder sb = new StringBuilder();

		int remaining = n;

		for (int i = 0; i < VALUES.length; i++) {
			remaining = convert(remaining, VALUES[i], SYMBOLS[i], sb);
		}

		return sb.toString();
	}

	private int convert(int remaining, int value, String roman, StringBuilder sb) {

		while (remaining >= value) {
			sb.append(roman);
			remaining -= value;
		}
		return remaining;
	}
}
