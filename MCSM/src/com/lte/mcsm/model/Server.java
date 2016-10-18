package com.lte.mcsm.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lte.mcsm.model.enums.ServerState;

import javafx.scene.image.Image;

public class Server {
	
	private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
	private int id;
	private String name;
	private String description;
	private String creationDate;
//	private Date lastRunDate;
//	private Date startDate;
//	private Date runtime;
	private ServerState serverState;
	private ServerProperties serverProperties;
	private Image serverImage;

	public Server(String name) {
		this.id = 0;
		this.name = name;
		this.description = "leer";
		this.creationDate = dateFormat.format(new Date());
		this.serverState = ServerState.Stopped;
		this.serverProperties = new ServerProperties();
		this.serverImage = null;
	}
	
	public Server(String name, int id) {
		this.id = id;
		this.name = name;
		this.description = "leer";
		this.creationDate = dateFormat.format(new Date());
		this.serverState = ServerState.Stopped;
		this.serverProperties = new ServerProperties();
		this.serverImage = null;
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

	public void setServerState(ServerState serverState) {
		this.serverState = serverState;
	}
	
	public ServerProperties getServerProperties() {
		return serverProperties;
	}

	public void setServerProperties(ServerProperties serverProperties) {
		this.serverProperties = serverProperties;
	}

	public Image getServerImage() {
		return serverImage;
	}

	public void setServerImage(Image serverImage) {
		this.serverImage = serverImage;
	}

}
