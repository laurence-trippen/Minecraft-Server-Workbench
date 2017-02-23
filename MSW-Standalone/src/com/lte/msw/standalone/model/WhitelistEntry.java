package com.lte.msw.standalone.model;

public class WhitelistEntry {
	
	private String uuid;
	private String name;
	
	public WhitelistEntry(String uuid, String name) {
		this.uuid = uuid;
		this.name = name;
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
	
}