package com.lte.msw.serverapi.server.config;

import java.util.List;

import com.lte.msw.serverapi.server.model.Player;
import com.lte.msw.serverapi.server.model.interfaces.IPlayerController;

public class Whitelist implements IPlayerController {
	
	private List<Player> playerWhitelist;
	
	@Override
	public void addPlayer(Player player) {
		
	}
	
	@Override
	public void removePlayer(Player player) {
		
	}
	
	public List<Player> getPlayerWhitelist() {
		return playerWhitelist;
	}

	public void setPlayerWhitelist(List<Player> playerWhitelist) {
		this.playerWhitelist = playerWhitelist;
	}
	
}