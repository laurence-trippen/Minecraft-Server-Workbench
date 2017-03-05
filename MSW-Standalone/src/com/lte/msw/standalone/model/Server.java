package com.lte.msw.standalone.model;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lte.msw.standalone.model.enums.ServerState;
import com.lte.msw.standalone.model.interfaces.IServerControl;
import com.lte.msw.standalone.model.threads.services.ServerProcessorService;

public class Server implements IServerControl {
	
	private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
	
	private int id;
	private String name;
	private String description;
	private String creationDate;
	private ServerPath serverPath;
	private ServerVersion serverVersion;
	private ServerState serverState;
	private ServerProperties serverProperties;
	private Operators serverOPS;
	private Whitelist serverWhitelist;
	private BannedAdresses serverBannedIps;
	private BannedPlayers serverBannedPlayers;
	private ServerProcessorService serverProcessService;
	
	public Server(String name, ServerVersion serverVersion) {
		this.id = 0;
		this.name = name;
		this.description = "leer";
		this.serverPath = new ServerPath(this.getName());
		this.serverVersion = serverVersion;
		this.serverState = ServerState.STOPPED;
		this.serverProperties = new ServerProperties(this.getServerPath().getProperties());
		this.serverOPS = new Operators(this.getServerPath().getOps());
		this.serverWhitelist = new Whitelist(this.getServerPath().getWhitlelist());
		this.serverBannedIps = new BannedAdresses(this.getServerPath().getBannedIps());
		this.serverBannedPlayers = new BannedPlayers(this.getServerPath().getBannedPlayers());
		this.serverProcessService = new ServerProcessorService(new File(serverVersion.getPath()));
	}
	
	public Server(String name, ServerVersion serverVersion, int id) {
		this.id = id;
		this.name = name;
		this.description = "leer";
		this.creationDate = dateFormat.format(new Date());
		this.serverPath = new ServerPath(this.getName());
		this.serverVersion = serverVersion;
		this.serverState = ServerState.STOPPED;
		this.serverProperties = new ServerProperties(this.getServerPath().getProperties());
		this.serverOPS = new Operators(this.getServerPath().getOps());
		this.serverWhitelist = new Whitelist(this.getServerPath().getWhitlelist());
		this.serverBannedIps = new BannedAdresses(this.getServerPath().getBannedIps());
		this.serverBannedPlayers = new BannedPlayers(this.getServerPath().getBannedPlayers());
		this.serverProcessService = new ServerProcessorService(new File(serverVersion.getPath()));
	}
	
	@Override
	public void init() {
		this.getServerProperties().load();
	}
	
	@Override
	public void start() {
		if (serverState == ServerState.STOPPED) {
			this.serverProcessService.start();
			this.setServerState(ServerState.STARTED);
		} else {
			System.out.println("Server " + this.getName() + "ist bereits gestartet!");
		}
	}
	
	@Override
	public void stop() {
		if (serverState == ServerState.STARTED) {
			this.serverProcessService.cancel();
		} else {
			System.out.println("Server " + this.getName() + "ist bereits gestoppt!");
		}
	}
	
	public boolean isWorldGenerated() {
		if (new File(serverPath.getWorld() + "/level.dat").exists()) {
			return true;
		}
		return false;
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
	
	public Operators getServerOPS() {
		return serverOPS;
	}

	public void setServerOPS(Operators serverOPS) {
		this.serverOPS = serverOPS;
	}

	public Whitelist getServerWhitelist() {
		return serverWhitelist;
	}

	public void setServerWhitelist(Whitelist serverWhitelist) {
		this.serverWhitelist = serverWhitelist;
	}

	public BannedAdresses getServerBannedIps() {
		return serverBannedIps;
	}

	public void setServerBannedIps(BannedAdresses serverBannedIps) {
		this.serverBannedIps = serverBannedIps;
	}

	public BannedPlayers getServerBannedPlayers() {
		return serverBannedPlayers;
	}

	public void setServerBannedPlayers(BannedPlayers serverBannedPlayers) {
		this.serverBannedPlayers = serverBannedPlayers;
	}

}