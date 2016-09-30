package com.lte.msw.serverapi.main;

import com.lte.msw.serverapi.model.MinecraftServer;

public class ServerAPI {
	
	public static void main(String[] args) {
		MinecraftServer laurenceServer = new MinecraftServer();
		laurenceServer.start();
	}

}
