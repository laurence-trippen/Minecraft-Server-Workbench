package com.lte.msw.serverapi.model.server;

public class MinecraftVersion {
	
	private String name;
	private String path;
	
	public MinecraftVersion(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}