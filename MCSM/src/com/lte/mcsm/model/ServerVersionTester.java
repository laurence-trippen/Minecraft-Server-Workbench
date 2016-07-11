package com.lte.mcsm.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerVersionTester {
	
	static File versionTestFile = null;
	final static File versionLogsDir = new File(Path.ServerCHECK + "logs");
	final static File versionLogOldFile = new File(Path.ServerCHECK + "server.log");
	final static File versionEulaFile = new File(Path.ServerCHECK + "eula.txt");
	final static File versionPropertiesFile = new File(Path.ServerCHECK + "server.properties");
	
	private static boolean runVersion() {
		ProcessBuilder processBuilder = new ProcessBuilder(
				"java",
				"-Xmx1024M",
				"-Xms1024M",
				"-jar",
				new File(Path.ServerCHECK).getAbsolutePath() + "\\" + versionTestFile.getName()
		);
		processBuilder.directory(new File(Path.ServerCHECK));
		try {
			Process process = processBuilder.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			StringBuilder stringBuilder = null;
			while ((line = br.readLine()) != null) {
				stringBuilder = new StringBuilder().append(line).append("\n");
			}
			System.out.println("Minecraft Server Console:\n" + stringBuilder.toString());
			process.destroy();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return false;
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
