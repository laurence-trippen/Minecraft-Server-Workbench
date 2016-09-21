package com.lte.msw.model;

import java.io.File;
import java.io.IOException;

public class ServerProcessor implements Runnable {

	private File executablePath;

	public ServerProcessor(File executablePath) {
		this.executablePath = executablePath;
	}

	@Override
	public void run() {
		Process process = null;
		ProcessBuilder processBuilder = new ProcessBuilder(
				"java", 
				"-Xmx1024M", 
				"-Xms1024M", 
				"-jar",
				executablePath.getAbsolutePath(), 
				"nogui"
		);
		processBuilder.directory(executablePath.getParentFile());
		try {
			process = processBuilder.start();
			ServerLogger serverLogger = new ServerLogger(process.getInputStream());
			new Thread(serverLogger).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}