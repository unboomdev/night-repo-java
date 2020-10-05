package dev;

public class FizzBuzz {

	public String evaluate(int i) {

		if (isMultipleOf3(i) && isMultipleOf5(i)) {
			return "FizzBuzz";
		} else if (isMultipleOf5(i)) {
			return "Buzz";
		} else if (isMultipleOf3(i)) {
			return "Fizz";
		}

		return String.valueOf(i);
	}

	private boolean isMultipleOf5(int i) {
		return i % 5 == 0;
	}

	private boolean isMultipleOf3(int i) {
		return i % 3 == 0;
	}
}
