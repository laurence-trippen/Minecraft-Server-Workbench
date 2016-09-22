package com.lte.msw.model;

import java.io.File;
import java.io.IOException;

import javafx.scene.control.TextArea;

public class ServerProcessor implements Runnable {

	private File executablePath;
	private TextArea console;

	public ServerProcessor(File executablePath, TextArea console) {
		this.executablePath = executablePath;
		this.console = console;
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
			new Thread(new ServerLogger(process.getInputStream(), console)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}