package com.lte.msw.serverapi.server.model.interfaces;

import com.lte.msw.serverapi.server.model.Player;

public interface IPlayerController {
	
	void addPlayer(Player player);
	void removePlayer(Player player);

}
