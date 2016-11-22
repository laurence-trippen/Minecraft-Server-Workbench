package com.lte.msw.standalone.model.threads.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ServerProcessService extends Service<Void> {
	
	private File executableFile;
	
	public ServerProcessService(File executableFile) {
		this.executableFile = executableFile;
	}
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				ProcessBuilder processBuilder = new ProcessBuilder(
						"java", 
						"-Xmx1024M",
						"-Xms1024M",
						"-jar",
						executableFile.getAbsolutePath(), 
						"nogui"
				);
				processBuilder.directory(executableFile.getParentFile());
				Process process = processBuilder.start();
				Thread consoleLogger = new Thread(new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
						try {
							String line = reader.readLine();
							while (line != null) {
								line = reader.readLine();
								consoleLog(line);
							}
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return null;
					}
				});
				consoleLogger.setDaemon(true);
				consoleLogger.start();
				return null;
			}
		};
	}
	
	private void consoleLog(String message) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				System.out.println(message);
			}
		});
	}
	
}