package com.lte.mcsm.manager;

import com.lte.msw.view.CreateServerWindow;
import com.lte.msw.view.MainWindow;
import com.lte.msw.view.ServerVersionsWindow;
import com.lte.msw.view.ServerWindow;
import com.lte.msw.view.VersionInstallWindow;

public class WindowManager {
	
	private static WindowManager instance = null;
	private MainWindow mainWindow;
	private CreateServerWindow createServerWindow;
	private ServerVersionsWindow serverVersionsWindow;
	private VersionInstallWindow versionInstallWindow;
	private ServerWindow serverWindow;
	
	public static WindowManager getWindowManager() {
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
		this.serverWindow = new ServerWindow();
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

	public ServerWindow getServerWindow() {
		return serverWindow;
	}

}