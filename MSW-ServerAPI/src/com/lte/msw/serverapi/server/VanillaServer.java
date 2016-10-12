package com.lte.msw.serverapi.server;

import com.lte.msw.serverapi.server.config.BannedIPAdresses;
import com.lte.msw.serverapi.server.config.BannedPlayers;
import com.lte.msw.serverapi.server.config.ServerPath;
import com.lte.msw.serverapi.server.config.ServerProperties;
import com.lte.msw.serverapi.server.config.Whitelist;
import com.lte.msw.serverapi.server.model.enums.ServerState;
import com.lte.msw.serverapi.server.model.interfaces.IServerController;
import com.lte.msw.serverapi.server.version.Version;

public class VanillaServer implements IServerController {

	private String name;
	private Version version;
	private ServerState serverState;
	private ServerPath serverPath;
	private ServerProperties serverProperties;
	private Whitelist Whitelist;
	private BannedPlayers bannedPlayers;
	private BannedIPAdresses bannedIPAdresses;

	public VanillaServer(String serverName, Version serverVersion) {
		this.name = serverName;
		this.version = serverVersion;
		this.serverPath = new ServerPath(this.getName());
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public ServerState getServerState() {
		return serverState;
	}

	public void setServerState(ServerState serverState) {
		this.serverState = serverState;
	}

	public ServerPath getServerPath() {
		return serverPath;
	}

	public void setServerPath(ServerPath serverPath) {
		this.serverPath = serverPath;
	}

	public ServerProperties getServerProperties() {
		return serverProperties;
	}

	public void setServerProperties(ServerProperties serverProperties) {
		this.serverProperties = serverProperties;
	}

	public Whitelist getWhitelist() {
		return Whitelist;
	}

	public void setWhitelist(Whitelist whitelist) {
		Whitelist = whitelist;
	}

	public BannedPlayers getBannedPlayers() {
		return bannedPlayers;
	}

	public void setBannedPlayers(BannedPlayers bannedPlayers) {
		this.bannedPlayers = bannedPlayers;
	}

	public BannedIPAdresses getBannedIPAdresses() {
		return bannedIPAdresses;
	}

	public void setBannedIPAdresses(BannedIPAdresses bannedIPAdresses) {
		this.bannedIPAdresses = bannedIPAdresses;
	}

}