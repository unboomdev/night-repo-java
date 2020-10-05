package dev.run;

public class NodeModel {

	private String start;
	private String goal;
	private int value;

	public NodeModel() {
	}

	public NodeModel(String start, String goal, int value) {
		super();
		this.start = start;
		this.goal = goal;
		this.value = value;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
