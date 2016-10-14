package com.lte.msw.standalone.model;

public class ServerOperatorEntry {
	
	private String uuid;
	private String name;
	private String level;
	
	public ServerOperatorEntry(String uuid, String name, String level) {
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