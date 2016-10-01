package com.lte.msw.serverapi.main;

import com.lte.msw.serverapi.model.server.MinecraftServer;
import com.lte.msw.serverapi.model.server.MinecraftVersion;

public class ServerAPI {
	
	public static void main(String[] args) {
		MinecraftServer laurenceServer = new MinecraftServer("Laurence-Server", new MinecraftVersion("1.10.2", "resources/versions/1.10.2.jar"));
		laurenceServer.start();
	}

}
