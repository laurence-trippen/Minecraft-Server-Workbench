package com.lte.msw.serverapi.model.server.console;

import com.lte.msw.serverapi.model.Player;
import com.lte.msw.serverapi.model.server.MinecraftServer;

public class ConsoleInterpreter {
	
	private MinecraftServer server;
	
	public ConsoleInterpreter(MinecraftServer server) {
		this.server = server;
	}
	
	public void checkMessage(String s) {
		if (s.contains(ServerMessage.JOIN)) {
			String[] a = s.split(" ");
			server.getPlayerList().addPlayer(new Player(a[0], null));
		}
		if (s.contains(ServerMessage.LEFT)) {
			String[] a = s.split(" ");
		}
	}

}
