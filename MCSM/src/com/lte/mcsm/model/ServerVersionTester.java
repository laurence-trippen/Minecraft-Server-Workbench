package com.lte.mcsm.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerVersionTester {
	
	private final static File versionLogsDir = new File(Path.ServerCHECK + "logs");
	private final static File versionLogOldFile = new File(Path.ServerCHECK + "server.log");
	private final static File versionEulaFile = new File(Path.ServerCHECK + "eula.txt");
	private final static File versionPropertiesFile = new File(Path.ServerCHECK + "server.properties");
	private File versionTestFile;
	
	public ServerVersionTester() {
		this.versionTestFile = null;
	}
	
	private boolean checkVersion() {
		if (versionEulaFile.exists() && versionPropertiesFile.exists() && versionLogsDir.exists() ||
			versionPropertiesFile.exists() && versionLogOldFile.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean runVersion() {
		Process process = null;
		ProcessBuilder processBuilder = new ProcessBuilder(
				"java",
				"-Xmx1024M",
				"-Xms1024M",
				"-jar",
				new File(Path.ServerCHECK).getAbsolutePath() + "\\" + versionTestFile.getName()
		);
		processBuilder.directory(new File(Path.ServerCHECK));
		try {
			process = processBuilder.start();
			process.waitFor();
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
	
	public boolean testVersion(File selectedVersionJar) {
		if (selectedVersionJar != null) {
			versionTestFile = new File(Path.ServerCHECK + selectedVersionJar.getName());
			try {
				Files.copy(
						Paths.get(selectedVersionJar.getAbsolutePath()), 
						Paths.get(Path.ServerCHECK + selectedVersionJar.getName())
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (versionTestFile.exists()) {
				if (runVersion()) {
					if (checkVersion()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
