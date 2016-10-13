package com.lte.msw.standalone.model.threads.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.lte.msw.standalone.model.Path;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class VersionInstallService extends Service<Void> {
	
	private final static File versionLogsDir 		= new File(Path.SERVER_CHECK + "logs");
	private final static File versionLogOldFile 	= new File(Path.SERVER_CHECK + "server.log");
	private final static File versionEulaFile 		= new File(Path.SERVER_CHECK + "eula.txt");
	private final static File versionPropertiesFile = new File(Path.SERVER_CHECK + "server.properties");
	private File sourceFile;
	private File versionTestFile;
	private ProgressBar progressBar;
	
	public VersionInstallService(File sourceFile, ProgressBar progressBar) {
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
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				if (sourceFile != null) {
					versionTestFile = new File(Path.SERVER_CHECK + sourceFile.getName());
					try {
						Files.copy(
								Paths.get(sourceFile.getAbsolutePath()),
								Paths.get(versionTestFile.getAbsolutePath())
						);
						System.out.println("Dateien kopiert ...");
						setProgress(0.4);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (versionTestFile.exists()) {
						if (runVersion()) {
							setProgress(0.6);
							System.out.println("Dateien werden überprüft ...");
							if (checkVersion()) {
								cleanTestArea(new File(Path.SERVER_CHECK));
								System.out.println("Version wird installiert ...");
								setProgress(1.0);
							}
						}
					}
				}
				System.out.println("Vorgnag wird benndet!");
				cleanTestArea(new File(Path.SERVER_CHECK));
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