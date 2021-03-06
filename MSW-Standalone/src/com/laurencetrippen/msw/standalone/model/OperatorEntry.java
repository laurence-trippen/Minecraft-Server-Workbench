package com.laurencetrippen.msw.standalone.model;

public class OperatorEntry {
	
	private String uuid;
	private String name;
	private String level;
	
	public OperatorEntry(String uuid, String name, String level) {
		this.uuid = uuid;
		this.name = name;
		this.level = level;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}