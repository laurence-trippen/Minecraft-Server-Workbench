package com.lte.msw.standalone.manager;

import com.lte.msw.standalone.view.scenes.CreateServerScene;
import com.lte.msw.standalone.view.scenes.MainScene;
import com.lte.msw.standalone.view.scenes.ServerScene;
import com.lte.msw.standalone.view.scenes.ServerVersionsScene;
import com.lte.msw.standalone.view.scenes.VersionInstallScene;

public class SceneManager {
	
	private static SceneManager instance = null;
	private MainScene mainScene;
	private CreateServerScene createServerScene;
	private ServerVersionsScene serverVersionsScene;
	private VersionInstallScene versionInstallScene;
	private ServerScene serverScene;
	
	public static SceneManager getSceneManager() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}
	
	private SceneManager() {
		this.mainScene = new MainScene();
		this.createServerScene = new CreateServerScene();
		this.serverVersionsScene = new ServerVersionsScene();
		this.versionInstallScene = new VersionInstallScene();
		this.serverScene = new ServerScene();
	}

	public MainScene getMainScene() {
		return mainScene;
	}

	public CreateServerScene getCreateServerScene() {
		return createServerScene;
	}

	public ServerVersionsScene getServerVersionsScene() {
		return serverVersionsScene;
	}

	public VersionInstallScene getVersionInstallScene() {
		return versionInstallScene;
	}

	public ServerScene getServerScene() {
		return serverScene;
	}

}