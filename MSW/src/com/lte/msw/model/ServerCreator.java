package com.lte.msw.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerCreator {
	
	public static boolean createServerDirectory(String newServerDir) {
		File serverDirectory = new File(newServerDir);
		if (!serverDirectory.exists()) {
			serverDirectory.mkdir();
			if (serverDirectory.exists()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean copyServerJar(String versionJar, String newServerDir) {
		try {
			Files.copy(
				Paths.get(versionJar),
				Paths.get(newServerDir)
			);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean runServerJar(String src, String dir, boolean initMode) {
		Process process = null;
		ProcessBuilder processBuilder = new ProcessBuilder(
				"java",
				"-Xmx1024M",
				"-Xms1024M",
				"-jar",
				new File(src).getAbsolutePath(),
				"nogui"
		);
		processBuilder.directory(new File(dir));
		try {
			process = processBuilder.start();
			ServerLogger logger = new ServerLogger(process.getInputStream(), null);
			Thread thread = new Thread(logger, "ServerLogger");
			thread.start();
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
	
	public static boolean editEula(String path) {
		try {
			String eula = null;
			StringBuilder stringBuilder = new StringBuilder();
			BufferedReader bReader = new BufferedReader(new FileReader(path));
			while ((eula = bReader.readLine()) != null) {
				stringBuilder.append(eula + "\n");
			}
			bReader.close();
			FileWriter fileWriter = new FileWriter(path);
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
	
	public static boolean checkServer(String newServerName) {
		if (
		new File(Path.SERVER_DIRECTORY + newServerName + "/logs").exists() &&
		new File(Path.SERVER_DIRECTORY + newServerName + "/world").exists() &&
		new File(Path.SERVER_DIRECTORY + newServerName + "/banned-ips.json").exists() &&
		new File(Path.SERVER_DIRECTORY + newServerName + "/banned-players.json").exists() &&
		new File(Path.SERVER_DIRECTORY + newServerName + "/eula.txt").exists() &&
		new File(Path.SERVER_DIRECTORY + newServerName + "/ops.json").exists() &&
		new File(Path.SERVER_DIRECTORY + newServerName + "/server.properties").exists() &&
		new File(Path.SERVER_DIRECTORY + newServerName + "/usercache.json").exists() &&
		new File(Path.SERVER_DIRECTORY + newServerName + "/whitelist.json").exists()
		) {
			return true;
		} else {
			return false;
		}
	}

}