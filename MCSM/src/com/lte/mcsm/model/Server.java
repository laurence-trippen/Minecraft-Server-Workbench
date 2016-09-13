package com.lte.mcsm.model;

import java.util.Date;

import static com.lte.mcsm.config.AppConfig.dateFormat;
import com.lte.mcsm.model.enums.ServerState;
import com.lte.mcsm.model.interfaces.IServerController;

public class Server implements IServerController {
	
	private int id;
	private String name;
	private String description;
	private String creationDate;
	private ServerPath serverPath;
	private ServerVersion serverVersion;
	private ServerState serverState;
	private ServerProperties serverProperties;
	private ServerOPS serverOPS;
	private ServerWhitelist serverWhitelist;
	private ServerBannedIps serverBannedIps;
	private ServerBannedPlayers serverBannedPlayers;
	
	public Server(String name, ServerVersion serverVersion) {
		this.id = 0;
		this.name = name;
		this.description = "leer";
		this.serverPath = new ServerPath(this.getName());
		this.serverVersion = serverVersion;
		this.serverState = ServerState.Stopped;
		this.serverProperties = new ServerProperties(this.getServerPath().getProperties());
		this.serverOPS = new ServerOPS(this.getServerPath().getOps());
		this.serverWhitelist = new ServerWhitelist(this.getServerPath().getWhitlelist());
		this.serverBannedIps = new ServerBannedIps(this.getServerPath().getBannedIps());
		this.serverBannedPlayers = new ServerBannedPlayers(this.getServerPath().getBannedPlayers());
	}
	
	public Server(String name, ServerVersion serverVersion, int id) {
		this.id = id;
		this.name = name;
		this.description = "leer";
		this.creationDate = dateFormat.format(new Date());
		this.serverPath = new ServerPath(this.getName());
		this.serverVersion = serverVersion;
		this.serverState = ServerState.Stopped;
		this.serverProperties = new ServerProperties(this.getServerPath().getProperties());
		this.serverOPS = new ServerOPS(this.getServerPath().getOps());
		this.serverWhitelist = new ServerWhitelist(this.getServerPath().getWhitlelist());
		this.serverBannedIps = new ServerBannedIps(this.getServerPath().getBannedIps());
		this.serverBannedPlayers = new ServerBannedPlayers(this.getServerPath().getBannedPlayers());
	}
	
	@Override
	public void init() {
			
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void stop() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public ServerState getServerState() {
		return serverState;
	}

	public ServerPath getServerPath() {
		return serverPath;
	}

	public void setServerPath(ServerPath serverPath) {
		this.serverPath = serverPath;
	}

	public ServerVersion getServerVersion() {
		return serverVersion;
	}

	public void setServerVersion(ServerVersion serverVersion) {
		this.serverVersion = serverVersion;
	}

	public void setServerState(ServerState serverState) {
		this.serverState = serverState;
	}
	
	public ServerProperties getServerProperties() {
		return serverProperties;
	}

	public void setServerProperties(ServerProperties serverProperties) {
		this.serverProperties = serverProperties;
	}
	
	public ServerOPS getServerOPS() {
		return serverOPS;
	}

	public void setServerOPS(ServerOPS serverOPS) {
		this.serverOPS = serverOPS;
	}

	public ServerWhitelist getServerWhitelist() {
		return serverWhitelist;
	}

	public void setServerWhitelist(ServerWhitelist serverWhitelist) {
		this.serverWhitelist = serverWhitelist;
	}

	public ServerBannedIps getServerBannedIps() {
		return serverBannedIps;
	}

	public void setServerBannedIps(ServerBannedIps serverBannedIps) {
		this.serverBannedIps = serverBannedIps;
	}

	public ServerBannedPlayers getServerBannedPlayers() {
		return serverBannedPlayers;
	}

	public void setServerBannedPlayers(ServerBannedPlayers serverBannedPlayers) {
		this.serverBannedPlayers = serverBannedPlayers;
	}

}