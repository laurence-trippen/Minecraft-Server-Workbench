package com.laurencetrippen.msw.standalone.model;

public class Map {

	private String world;
	private String index;

	public Map(String world) {
		this.world = world;
	}

	public Map(String world, String index) {
		this.world = world;
		this.index = index;
	}

	public String getWorld() {
		return world;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

}