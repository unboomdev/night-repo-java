package dev;

import java.text.DecimalFormat;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ConvertNumber {

	public String formatNumber(Long number) {

		NavigableMap<Long, String> suffixes = new TreeMap<>();
		suffixes.put(1_000L, "K");
		suffixes.put(1_000_000L, "M");
		suffixes.put(1_000_000_000L, "B");
		suffixes.put(1_000_000_000_000L, "T");

		// Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
		if (number == Long.MIN_VALUE) {
			return formatNumber(Long.MIN_VALUE + 1);
		}

		if (number < 0) {
			return "-" + formatNumber(-number);
		}

		if (number < 1000) {
			return Long.toString(number); // deal with easy case
		}

		if (number > 100000000000000L) { // maximum of requirement
			return "99T";
		}

		Entry<Long, String> e = suffixes.floorEntry(number);
		Long divideBy = e.getKey();
		String suffix = e.getValue();

		double truncated = (number / (divideBy / 10d)) / 10d; // divide by NavigableMap
		DecimalFormat df;
		if ((int) truncated < 10) {
			df = new DecimalFormat("#.##");
		} else if ((int) truncated < 100) {
			df = new DecimalFormat("#.#");
		} else {
			df = new DecimalFormat("###");
		}
		String result = df.format(truncated);
		return result + suffix;
	}
}
