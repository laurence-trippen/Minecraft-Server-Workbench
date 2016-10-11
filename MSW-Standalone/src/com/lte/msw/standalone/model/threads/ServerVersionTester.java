package com.lte.msw.standalone.model.threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.lte.msw.standalone.model.Path;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class ServerVersionTester implements Runnable {

	private final static File versionLogsDir 		= new File(Path.SERVER_CHECK + "logs");
	private final static File versionLogOldFile 	= new File(Path.SERVER_CHECK + "server.log");
	private final static File versionEulaFile 		= new File(Path.SERVER_CHECK + "eula.txt");
	private final static File versionPropertiesFile = new File(Path.SERVER_CHECK + "server.properties");
	private volatile boolean successful = false;
	private File sourceFile;
	private File versionTestFile;
	private ProgressBar progressBar;

	public ServerVersionTester(File sourceFile, ProgressBar progressBar) {
		this.sourceFile = sourceFile;
		this.progressBar = progressBar;
		this.versionTestFile = null;
	}

	private void cleanTestArea(File path) {
		for (File file : path.listFiles()) {
			if (file.isDirectory()) {
				cleanTestArea(file);
			}
			file.delete();
		}
	}

	private boolean checkVersion() {
		if (versionEulaFile.exists() && versionPropertiesFile.exists() && versionLogsDir.exists()
				|| versionPropertiesFile.exists() && versionLogOldFile.exists()) {
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
				new File(Path.SERVER_CHECK).getAbsolutePath() + "/" + versionTestFile.getName(),
				"nogui"
				);
		processBuilder.directory(new File(Path.SERVER_CHECK));
		try {
			process = processBuilder.start();
			Thread.sleep(20000);
			process.destroy();
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
	
	@Override
	public void run() {
		if (sourceFile != null) {
			versionTestFile = new File(Path.SERVER_CHECK + sourceFile.getName());
			try {
				Files.copy(
						Paths.get(sourceFile.getAbsolutePath()),
						Paths.get(versionTestFile.getAbsolutePath())
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (versionTestFile.exists()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						progressBar.setProgress(0.5);
					}
				});
				if (runVersion()) {
					if (checkVersion()) {
						cleanTestArea(new File(Path.SERVER_CHECK));
						successful = true;
					}
				}
			}
		}
		cleanTestArea(new File(Path.SERVER_CHECK));
	}

	public boolean isSuccessful() {
		return successful;
	}

}