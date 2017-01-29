package com.lte.msw.standalone.manager;

import com.lte.msw.standalone.view.CreateServerScene;
import com.lte.msw.standalone.view.MainScene;
import com.lte.msw.standalone.view.ServerVersionsScene;
import com.lte.msw.standalone.view.ServerScene;
import com.lte.msw.standalone.view.VersionInstallScene;

public class SceneManager {
	
	private static SceneManager instance = null;
	private MainScene mainWindow;
	private CreateServerScene createServerWindow;
	private ServerVersionsScene serverVersionsWindow;
	private VersionInstallScene versionInstallWindow;
	private ServerScene serverWindow;
	
	public static SceneManager getWindowManager() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}
	
	private SceneManager() {
		this.mainWindow = new MainScene();
		this.createServerWindow = new CreateServerScene();
		this.serverVersionsWindow = new ServerVersionsScene();
		this.versionInstallWindow = new VersionInstallScene();
		this.serverWindow = new ServerScene();
	}

	public MainScene getMainWindow() {
		return mainWindow;
	}

	public CreateServerScene getCreateServerWindow() {
		return createServerWindow;
	}

	public ServerVersionsScene getServerVersionsWindow() {
		return serverVersionsWindow;
	}

	public VersionInstallScene getVersionInstallWindow() {
		return versionInstallWindow;
	}

	public ServerScene getServerWindow() {
		return serverWindow;
	}

}