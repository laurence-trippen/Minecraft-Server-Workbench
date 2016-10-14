package com.lte.msw.serverapi.server.config;

import com.lte.msw.serverapi.core.ResourcePath;

public class ServerPath {
	
	private String eula;
	private String ops;
	private String whitelist;
	private String bannedIps;
	private String bannedPlayers;
	private String usercache;
	private String properties;
	private String world;
	private String logs;
	private String latestLog;
	
	public ServerPath(String serverName) {
		this.eula 			= ResourcePath.SERVER_DIRECTORY + serverName + ResourcePath.EULA;
		this.ops 			= ResourcePath.SERVER_DIRECTORY + serverName + ResourcePath.OPS;
		this.whitelist 		= ResourcePath.SERVER_DIRECTORY + serverName + ResourcePath.WHITELIST;
		this.bannedIps 		= ResourcePath.SERVER_DIRECTORY + serverName + ResourcePath.BANNED_IPS;
		this.bannedPlayers 	= ResourcePath.SERVER_DIRECTORY + serverName + ResourcePath.BANNED_PLAYERS;
		this.usercache 		= ResourcePath.SERVER_DIRECTORY + serverName + ResourcePath.USERCACHE;
		this.properties 	= ResourcePath.SERVER_DIRECTORY + serverName + ResourcePath.PROPERTIES;
		this.world 			= ResourcePath.SERVER_DIRECTORY + serverName + ResourcePath.WORLD;
		this.logs 			= ResourcePath.SERVER_DIRECTORY + serverName + ResourcePath.LOGS;
		this.latestLog  	= ResourcePath.SERVER_DIRECTORY + serverName + ResourcePath.LATEST_LOG;
	}

	public String getEula() {
		return eula;
	}

	public String getOps() {
		return ops;
	}

	public String getWhitlelist() {
		return whitelist;
	}

	public String getBannedIps() {
		return bannedIps;
	}

	public String getBannedPlayers() {
		return bannedPlayers;
	}

	public String getUsercache() {
		return usercache;
	}

	public String getProperties() {
		return properties;
	}

	public String getWorld() {
		return world;
	}

	public String getLog() {
		return logs;
	}

	public String getLatest_log() {
		return latestLog;
	}
	
}