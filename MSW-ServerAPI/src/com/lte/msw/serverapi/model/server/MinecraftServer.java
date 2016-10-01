package com.lte.msw.serverapi.model.server;

import com.lte.msw.serverapi.model.interfaces.IServerController;

public class MinecraftServer implements IServerController {
	
	private String serverName;
	private MinecraftVersion minecraftVersion;
	private PlayerList playerList;
	
	public MinecraftServer(String serverName, MinecraftVersion minecraftVersion) {
		this.serverName = serverName;
		this.minecraftVersion = minecraftVersion;
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void stop() {
		
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public MinecraftVersion getMinecraftVersion() {
		return minecraftVersion;
	}

	public void setMinecraftVersion(MinecraftVersion minecraftVersion) {
		this.minecraftVersion = minecraftVersion;
	}

	public PlayerList getPlayerList() {
		return playerList;
	}

	public void setPlayerList(PlayerList playerList) {
		this.playerList = playerList;
	}

}