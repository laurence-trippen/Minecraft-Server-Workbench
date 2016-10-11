package com.lte.msw.model;

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
		this.eula 			= Path.SERVER_DIRECTORY + serverName + Path.EULA;
		this.ops 			= Path.SERVER_DIRECTORY + serverName + Path.OPS;
		this.whitelist 		= Path.SERVER_DIRECTORY + serverName + Path.WHITELIST;
		this.bannedIps 		= Path.SERVER_DIRECTORY + serverName + Path.BANNED_IPS;
		this.bannedPlayers 	= Path.SERVER_DIRECTORY + serverName + Path.BANNED_PLAYERS;
		this.usercache 		= Path.SERVER_DIRECTORY + serverName + Path.USERCACHE;
		this.properties 	= Path.SERVER_DIRECTORY + serverName + Path.PROPERTIES;
		this.world 			= Path.SERVER_DIRECTORY + serverName + Path.WORLD;
		this.logs 			= Path.SERVER_DIRECTORY + serverName + Path.LOGS;
		this.latestLog  	= Path.SERVER_DIRECTORY + serverName + Path.LATEST_LOG;
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