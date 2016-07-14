package com.lte.mcsm.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerVersionTester {
	
	static File versionTestFile = null;
	final static File versionLogsDir = new File(Path.ServerCHECK + "logs");
	final static File versionLogOldFile = new File(Path.ServerCHECK + "server.log");
	final static File versionEulaFile = new File(Path.ServerCHECK + "eula.txt");
	final static File versionPropertiesFile = new File(Path.ServerCHECK + "server.properties");
	
	private static boolean runVersion() {
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
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static boolean checkVersion() {
		if (versionEulaFile.exists() && versionPropertiesFile.exists() && versionLogsDir.exists() ||
			versionPropertiesFile.exists() && versionLogOldFile.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean testVersion(File selectedVersionJar) {
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
