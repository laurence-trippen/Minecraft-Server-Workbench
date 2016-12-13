package com.lte.msw.standalone.model.threads.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.lte.msw.standalone.model.ServerVersion;
import com.lte.msw.standalone.model.abstracts.ResourcePath;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class ServerCreateService extends Service<Void> {
	
	private String serverName;
	private String serverPath;
	private File oldServerJar;
	private File newServerJar;
	private ProgressBar progressBar;
	
	public ServerCreateService(String serverName, ServerVersion serverVersion, ProgressBar progressBar) {
		this.serverName = serverName;
		this.serverPath = ResourcePath.SERVER_DIRECTORY + serverName;
		this.oldServerJar = new File(serverVersion.getPath());
		this.newServerJar = new File(serverPath + "/" + oldServerJar.getName());
		this.progressBar = progressBar;
	}
	
	private boolean createServerDirectory() {
		File serverDirectory = new File(serverPath);
		if (!serverDirectory.exists()) {
			serverDirectory.mkdir();
			if (serverDirectory.exists()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean copyServerJar() {
		try {
			Files.copy(
				Paths.get(oldServerJar.getAbsolutePath()),
				Paths.get(newServerJar.getAbsolutePath())
			);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean runServerJar(boolean initMode) {
		Process process = null;
		ProcessBuilder processBuilder = new ProcessBuilder(
				"java",
				"-Xmx1024M",
				"-Xms1024M",
				"-jar",
				new File(newServerJar.getAbsolutePath()).getAbsolutePath(),
				"nogui"
		);
		processBuilder.directory(new File(newServerJar.getParentFile().getPath()));
		try {
			process = processBuilder.start();
			if (initMode) {
				process.waitFor();
			} else {
				Thread.sleep(30000);
				process.destroy();
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean editEula() {
		try {
			String eula = null;
			StringBuilder stringBuilder = new StringBuilder();
			BufferedReader bReader = new BufferedReader(new FileReader(serverPath + ResourcePath.EULA));
			while ((eula = bReader.readLine()) != null) {
				stringBuilder.append(eula + "\n");
			}
			bReader.close();
			FileWriter fileWriter = new FileWriter(serverPath + ResourcePath.EULA);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(stringBuilder.toString().replace("eula=false", "eula=true"));
			bufferedWriter.close();
			fileWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean checkServer() {
		if (
		new File(serverPath + "/logs").exists() &&
		new File(serverPath  + "/world").exists() &&
		new File(serverPath  + serverName + "/banned-ips.json").exists() &&
		new File(serverPath  + serverName + "/banned-players.json").exists() &&
		new File(serverPath  + serverName + "/eula.txt").exists() &&
		new File(serverPath  + serverName + "/ops.json").exists() &&
		new File(serverPath  + serverName + "/server.properties").exists() &&
		new File(serverPath  + serverName + "/usercache.json").exists() &&
		new File(serverPath  + serverName + "/whitelist.json").exists()
		) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				if (createServerDirectory()) {
					setProgress(0.3);
					if (copyServerJar()) {	
						setProgress(0.4);
						if (runServerJar(true)) {
							setProgress(0.5);
							if (editEula()) {
								setProgress(0.7);
								if (runServerJar(false)) {
									setProgress(1.0);
									if (checkServer()) {
										return null;
									}
								}
							}
						}
					}
				}
				return null;
			}
		};
	}
	
	private void setProgress(double progress) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setProgress(progress);
			}
		});
	}

}