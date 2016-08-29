package com.lte.mcsm.model;

import java.util.Date;

import static com.lte.mcsm.config.AppConfig.dateFormat;
import com.lte.mcsm.model.enums.ServerState;
import com.lte.mcsm.model.interfaces.IServerController;

import javafx.scene.image.Image;

public class Server implements IServerController {
	
	private int id;
	private String name;
	private String description;
	private String creationDate;
//	private Date lastRunDate;
//	private Date startDate;
//	private Date runtime;
	private ServerVersion serverVersion;
	private ServerState serverState;
	private ServerProperties serverProperties;
	private ServerOPS serverOPS;
	private ServerWhitelist serverWhitelist;
	private ServerBannedIps serverBannedIps;
	private ServerBannedPlayers serverBannedPlayers;
	private Image serverImage;

	public Server(String name) {
		this.id = 0;
		this.name = name;
		this.description = "leer";
		this.creationDate = dateFormat.format(new Date());
		this.serverVersion = null;
		this.serverState = ServerState.Stopped;
		this.serverProperties = new ServerProperties();
		this.serverOPS = new ServerOPS();
		this.serverWhitelist = new ServerWhitelist();
		this.serverBannedIps = new ServerBannedIps();
		this.serverBannedPlayers = new ServerBannedPlayers();
		this.serverImage = null;
	}
	
	public Server(String name, int id) {
		this.id = id;
		this.name = name;
		this.description = "leer";
		this.creationDate = dateFormat.format(new Date());
		this.serverVersion = null;
		this.serverState = ServerState.Stopped;
		this.serverProperties = new ServerProperties();
		this.serverOPS = new ServerOPS();
		this.serverWhitelist = new ServerWhitelist();
		this.serverBannedIps = new ServerBannedIps();
		this.serverBannedPlayers = new ServerBannedPlayers();
		this.serverImage = null;
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

//	public Date getLastRunDate() {
//		return lastRunDate;
//	}
//
//	public void setLastRunDate(Date lastRunDate) {
//		this.lastRunDate = lastRunDate;
//	}
//
//	public Date getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//	}
//
//	public Date getRuntime() {
//		return runtime;
//	}
//
//	public void setRuntime(Date runtime) {
//		this.runtime = runtime;
//	}

	public ServerState getServerState() {
		return serverState;
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

	public Image getServerImage() {
		return serverImage;
	}

	public void setServerImage(Image serverImage) {
		this.serverImage = serverImage;
	}

}