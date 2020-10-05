package dev.run;

public class InputModel {

	private String start;
	private String goal;

	public InputModel() {
	}

	public InputModel(String start, String goal) {
		super();
		this.start = start;
		this.goal = goal;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}
}
