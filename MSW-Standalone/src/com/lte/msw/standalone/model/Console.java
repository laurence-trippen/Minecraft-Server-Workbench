package com.lte.msw.standalone.model;

import java.io.InputStream;
import java.io.OutputStream;

public class Console {
	
	private String consoleLog;
	private InputStream consoleInputStream;
	private OutputStream consoleOutputStream;
	
	public Console(InputStream inputStream, OutputStream outputStream) {
		this.consoleInputStream = inputStream;
		this.consoleOutputStream = outputStream;
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