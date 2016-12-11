package com.lte.msw.standalone.model;

import java.io.InputStream;
import java.io.OutputStream;

public class ServerConsole {
	
	private String consoleLog;
	private InputStream consoleInputStream;
	private OutputStream consoleOutputStream;
	
	public ServerConsole(InputStream inputStream) {
		this.consoleInputStream = inputStream;
	}
	
	public void start() {
		
	}

	public String getConsoleLog() {
		return consoleLog;
	}

	public void setConsoleLog(String consoleLog) {
		this.consoleLog = consoleLog;
	}

	public InputStream getConsoleInputStream() {
		return consoleInputStream;
	}

	public void setConsoleInputStream(InputStream consoleInputStream) {
		this.consoleInputStream = consoleInputStream;
	}

	public OutputStream getConsoleOutputStream() {
		return consoleOutputStream;
	}

	public void setConsoleOutputStream(OutputStream consoleOutputStream) {
		this.consoleOutputStream = consoleOutputStream;
	}
	
}