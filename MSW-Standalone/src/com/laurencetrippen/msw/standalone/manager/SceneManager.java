package com.laurencetrippen.msw.standalone.manager;

import com.laurencetrippen.msw.standalone.view.scenes.MainScene;
import com.laurencetrippen.msw.standalone.view.scenes.ServerCreatorScene;
import com.laurencetrippen.msw.standalone.view.scenes.ServerScene;
import com.laurencetrippen.msw.standalone.view.scenes.ServerVersionsScene;
import com.laurencetrippen.msw.standalone.view.scenes.VersionInstallerScene;

public class SceneManager {
	
	private static SceneManager instance = null;
	private MainScene mainScene;
	private ServerCreatorScene serverCreatorScene;
	private ServerVersionsScene serverVersionsScene;
	private VersionInstallerScene versionInstallScene;
	private ServerScene serverScene;
	
	public static SceneManager getSceneManager() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}
	
	private SceneManager() {
		this.mainScene = new MainScene();
		this.serverCreatorScene = new ServerCreatorScene();
		this.serverVersionsScene = new ServerVersionsScene();
		this.versionInstallScene = new VersionInstallerScene();
		this.serverScene = new ServerScene();
	}

	public MainScene getMainScene() {
		return mainScene;
	}

	public ServerCreatorScene getServerCreatorScene() {
		return serverCreatorScene;
	}

	public ServerVersionsScene getServerVersionsScene() {
		return serverVersionsScene;
	}

	public VersionInstallerScene getVersionInstallScene() {
		return versionInstallScene;
	}

	public ServerScene getServerScene() {
		return serverScene;
	}

}