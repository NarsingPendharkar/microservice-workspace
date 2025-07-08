package com.task.tasks.entity;

public enum Priority {
	
	LOW("Low"),
	MEDIUM("Medium"),
	HIGH("High"),
	URGENT("Urgent");

	private final String level;

	Priority(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

}
