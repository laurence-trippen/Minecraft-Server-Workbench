package com.lte.mcsm.controller;

import com.lte.mcsm.view.CreateServerWindow;
import com.lte.mcsm.view.MainWindow;
import com.lte.mcsm.view.ServerVersionsWindow;
import com.lte.mcsm.view.VersionInstallWindow;

public class WindowManager {
	
	private static WindowManager instance = null;
	private MainWindow mainWindow;
	private CreateServerWindow createServerWindow;
	private ServerVersionsWindow serverVersionsWindow;
	private VersionInstallWindow versionInstallWindow;
	
	public static WindowManager getInstance() {
		if (instance == null) {
			instance = new WindowManager();
		}
		return instance;
	}
	
	private WindowManager() {
		this.mainWindow = new MainWindow();
		this.createServerWindow = new CreateServerWindow();
		this.serverVersionsWindow = new ServerVersionsWindow();
		this.versionInstallWindow = new VersionInstallWindow();
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public CreateServerWindow getCreateServerWindow() {
		return createServerWindow;
	}

	public ServerVersionsWindow getServerVersionsWindow() {
		return serverVersionsWindow;
	}

	public VersionInstallWindow getVersionInstallWindow() {
		return versionInstallWindow;
	}

}
