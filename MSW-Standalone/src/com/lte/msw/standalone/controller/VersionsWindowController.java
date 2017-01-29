package com.lte.msw.standalone.controller;

import com.lte.msw.standalone.main.MSWStandalone;
import com.lte.msw.standalone.manager.SceneManager;
import com.lte.msw.standalone.view.ServerVersionsWindow;

import javafx.event.ActionEvent;

public class VersionsWindowController {
	
	public VersionsWindowController(ServerVersionsWindow versionsWindow) {

	}
	
	public void installVersionHandler(ActionEvent event) {
		MSWStandalone.getMainStage().setScene(SceneManager.getWindowManager().getVersionInstallWindow());
	}
	
	public void closeHandler(ActionEvent event) {
		MSWStandalone.getMainStage().setScene(SceneManager.getWindowManager().getMainWindow());
	}

}
