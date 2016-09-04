package com.lte.mcsm.controller;

import com.lte.mcsm.main.Program;
import com.lte.mcsm.manager.WindowManager;
import com.lte.mcsm.view.ServerVersionsWindow;

import javafx.event.ActionEvent;

public class VersionsWindowController {
	
	private ServerVersionsWindow versionsWindow;
	
	public VersionsWindowController(ServerVersionsWindow versionsWindow) {
		this.versionsWindow = versionsWindow;
	}
	
	public void installVersionHandler(ActionEvent event) {
		Program.getMainStage().setScene(WindowManager.getWindowManager().getVersionInstallWindow());
	}
	
	public void closeHandler(ActionEvent event) {
		Program.getMainStage().setScene(WindowManager.getWindowManager().getMainWindow());
	}

}
