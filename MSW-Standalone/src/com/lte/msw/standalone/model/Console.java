package com.lte.msw.standalone.model;

import java.io.InputStream;
import java.io.OutputStream;

public class Console {
	
	private StringBuilder consoleCache;
	private InputStream consoleInputStream;
	private OutputStream consoleOutputStream;
	
	public Console(InputStream inputStream, OutputStream outputStream) {
		this.consoleCache = new StringBuilder();
		this.consoleInputStream = inputStream;
		this.consoleOutputStream = outputStream;
	}
	
	public void run() {
		
	}
	
	public String getConsoleText() {
		return consoleCache.toString();
	}

	public InputStream getConsoleInputStream() {
		return consoleInputStream;
	}

	public OutputStream getConsoleOutputStream() {
		return consoleOutputStream;
	}
	
}