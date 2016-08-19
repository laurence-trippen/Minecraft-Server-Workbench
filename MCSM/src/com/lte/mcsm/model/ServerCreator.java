package com.lte.mcsm.model;

import java.io.File;
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
	
	public static boolean runServerJar() {
		return false;
	}

}