package com.lte.msw.standalone.controller;

import com.lte.msw.standalone.main.MSWStandalone;
import com.lte.msw.standalone.manager.SceneManager;
import com.lte.msw.standalone.view.scenes.ServerVersionsScene;

import javafx.event.ActionEvent;

public class VersionsWindowController {
	
	public VersionsWindowController(ServerVersionsScene versionsWindow) {

	}
	
	public void installVersionHandler(ActionEvent event) {
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getVersionInstallScene());
	}
	
	public void closeHandler(ActionEvent event) {
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getMainScene());
	}

}
