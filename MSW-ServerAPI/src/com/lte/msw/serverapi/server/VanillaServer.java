package com.lte.msw.serverapi.server;

import com.lte.msw.serverapi.server.config.ServerProperties;
import com.lte.msw.serverapi.server.model.enums.ServerState;
import com.lte.msw.serverapi.server.version.Version;

public class VanillaServer {
	
	private String name;
	private Version version;
	private ServerState serverState;
	private ServerProperties serverProperties;

}
