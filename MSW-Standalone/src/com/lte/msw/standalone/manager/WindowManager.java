package com.lte.msw.standalone.manager;

import com.lte.msw.standalone.view.CreateServerWindow;
import com.lte.msw.standalone.view.MainWindow;
import com.lte.msw.standalone.view.ServerVersionsWindow;
import com.lte.msw.standalone.view.ServerWindow;
import com.lte.msw.standalone.view.VersionInstallWindow;

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