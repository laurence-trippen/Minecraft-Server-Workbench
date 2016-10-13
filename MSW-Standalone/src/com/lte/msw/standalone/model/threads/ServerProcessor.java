package com.lte.msw.standalone.model.threads;

import java.io.File;
import java.io.IOException;

import javafx.scene.control.TextArea;

public class ServerProcessor implements Runnable {

	private File executablePath;

	public ServerProcessor(File executablePath, TextArea console) {
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
			Thread loggerThread = new Thread(new ServerLogger(process.getInputStream()));
			loggerThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}