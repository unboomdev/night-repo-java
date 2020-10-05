package dev.run;

import java.text.DecimalFormat;

public class ConvertNumber {

	public String convertNumer(Long number) {

		String[] suffix = new String[] { "", "k", "m", "b", "t" };
		int maxLength = 5;

		DecimalFormat df = new DecimalFormat("##0E0"); 
		String result = df.format(number);
		String[] arr = result.split("E");
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
			result = sb.toString();
		}
		
		result = result.replaceAll("E[0-9]", suffix[Character.getNumericValue(result.charAt(result.length() - 1)) / 3]);
		while (result.length() > maxLength || result.matches("[0-9]+\\.[a-z]")) {
			result = result.substring(0, result.length() - 2) + result.substring(result.length() - 1);
		}
		return result;
	}
}
