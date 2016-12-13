package com.lte.msw.serverapi.server.config;

import java.util.List;

import com.lte.msw.serverapi.server.model.Player;
import com.lte.msw.serverapi.server.model.interfaces.IPlayerController;

public class BannedPlayers implements IPlayerController {

	private List<Player> bannedPlayers;

	@Override
	public void addPlayer(Player player) {
		
	}

	@Override
	public void removePlayer(Player player) {
		
	}

	public List<Player> getBannedPlayers() {
		return bannedPlayers;
	}

	public void setBannedPlayers(List<Player> bannedPlayers) {
		this.bannedPlayers = bannedPlayers;
	}

}