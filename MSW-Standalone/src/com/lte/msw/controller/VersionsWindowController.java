package com.lte.msw.controller;

import com.lte.msw.main.MSWStandalone;
import com.lte.msw.manager.WindowManager;
import com.lte.msw.view.ServerVersionsWindow;

import javafx.event.ActionEvent;

public class VersionsWindowController {
	
	public VersionsWindowController(ServerVersionsWindow versionsWindow) {

	}
	
	public void installVersionHandler(ActionEvent event) {
		MSWStandalone.getMainStage().setScene(WindowManager.getWindowManager().getVersionInstallWindow());
	}
	
	public void closeHandler(ActionEvent event) {
		MSWStandalone.getMainStage().setScene(WindowManager.getWindowManager().getMainWindow());
	}

}
