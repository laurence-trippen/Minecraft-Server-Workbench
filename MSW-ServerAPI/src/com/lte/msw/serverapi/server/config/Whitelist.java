package com.lte.msw.serverapi.server.config;

import java.util.List;

import com.lte.msw.serverapi.server.model.Player;
import com.lte.msw.serverapi.server.model.interfaces.IPlayerController;
import com.lte.msw.serverapi.server.model.interfaces.IWhiteListController;

public class Whitelist implements IPlayerController, IWhiteListController {
	
	private boolean isEnabled;
	private List<Player> playerWhitelist;

	@Override
	public void addPlayer(Player player) {

	}

	@Override
	public void removePlayer(Player player) {

	}

	@Override
	public void listAllPlayers() {
		
	}

	@Override
	public void enable() {
		
	}

	@Override
	public void disable() {
		
	}

	@Override
	public void relaod() {
		
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public List<Player> getPlayerWhitelist() {
		return playerWhitelist;
	}

	public void setPlayerWhitelist(List<Player> playerWhitelist) {
		this.playerWhitelist = playerWhitelist;
	}

}