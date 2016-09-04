package com.lte.mcsm.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerCreator {
	
	private static String newServerDirectory;
	private final static File logsDir 			= new File(newServerDirectory + "/logs");
	private final static File worldDir 			= new File(newServerDirectory + "/world");
	private final static File bannedIpFile 		= new File(newServerDirectory + "/banned-ips.json");
	private final static File bannedPlayersFile = new File(newServerDirectory + "/banned-players.json");
	private final static File eulaFile 			= new File(newServerDirectory + "/eula.txt");
	private final static File opsFile 			= new File(newServerDirectory + "/ops.json");
	private final static File propertiesFile 	= new File(newServerDirectory + "/server.properties");
	private final static File usercacheFile 	= new File(newServerDirectory + "/usercache.json");
	private final static File whitelistFile 	= new File(newServerDirectory + "/whitelist.json");
	
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
			ServerLogger logger = new ServerLogger(process.getInputStream());
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
	
	public static boolean checkServer() {
		if (
				logsDir.exists() && 
				worldDir.exists() && 
				bannedIpFile.exists() && 
				bannedPlayersFile.exists() && 
				eulaFile.exists() && 
				opsFile.exists() &&
				propertiesFile.exists() &&
				usercacheFile.exists() &&
				whitelistFile.exists()
		) {
			return true;
		} else {
			return false;
		}
	}

}