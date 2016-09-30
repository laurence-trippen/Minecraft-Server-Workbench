package com.lte.msw.serverapi.model;

import com.lte.msw.serverapi.model.interfaces.IServerController;

public class MinecraftServer implements IServerController {
	
	private String serverName;
	private PlayerList playerList;
	private ConsoleInterpreter consoleInterpreter;
	
	public MinecraftServer() {
		this.playerList = new PlayerList();
		this.consoleInterpreter = new ConsoleInterpreter(this);
	}
	
	@Override
	public void start() {
		consoleInterpreter.checkMessage("SeriousLaw joined the game.");
		consoleInterpreter.checkMessage("TheDust98 joined the game.");
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

	public PlayerList getPlayerList() {
		return playerList;
	}

	public void setPlayerList(PlayerList playerList) {
		this.playerList = playerList;
	}

	public ConsoleInterpreter getConsoleInterpreter() {
		return consoleInterpreter;
	}

	public void setConsoleInterpreter(ConsoleInterpreter consoleInterpreter) {
		this.consoleInterpreter = consoleInterpreter;
	}

}
