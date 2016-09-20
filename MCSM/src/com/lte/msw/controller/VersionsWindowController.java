package com.lte.msw.controller;

import com.lte.msw.main.Program;
import com.lte.msw.manager.WindowManager;
import com.lte.msw.view.ServerVersionsWindow;

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
