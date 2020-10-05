package dev.run;

import java.text.DecimalFormat;

public class ConvertNumber {

	public String convertNumer(Long number) {

		String[] suffix = new String[] { "", "k", "m", "b", "t" };
		int maxLength = 5;

		String r = new DecimalFormat("##0E0").format(number);
		String[] arr = r.split("E");
		if (arr[0].length() < 3) {
			StringBuilder sb = new StringBuilder();
			boolean first = true;
			while (sb.toString().length() <= 3) {
				if (first) {
					sb.append(arr[0] + ".");
					first = false;
				} else {
					sb.append("0");
				}
			}
			arr[0] = arr[0].replace(arr[0], sb.toString() + "E");
			sb = new StringBuilder();
			for (String string : arr) {
				sb.append(string);
			}
			r = sb.toString();
		}
		
		r = r.replaceAll("E[0-9]", suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
		while (r.length() > maxLength || r.matches("[0-9]+\\.[a-z]")) {
			r = r.substring(0, r.length() - 2) + r.substring(r.length() - 1);
		}
		return r;
	}
}
