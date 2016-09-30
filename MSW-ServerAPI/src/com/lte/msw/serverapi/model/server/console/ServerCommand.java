package com.lte.msw.serverapi.model.server.console;

public class ServerCommand {
	
	private String key;
	private String command;
	
	public ServerCommand(String key, String command) {
		this.key = key;
		this.command = command;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}