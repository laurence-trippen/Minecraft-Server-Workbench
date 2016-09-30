package com.lte.msw.serverapi.model;

import java.util.ArrayList;

public class PlayerList {
	
	private ArrayList<Player> players;
	
	public PlayerList() {
		this.players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
		System.out.println(player.getName() + " joined the server.");
	}
	
	public void printPlayers() {
		for (Player player : players) {
			System.out.println(player.getName());
		}
	}

}