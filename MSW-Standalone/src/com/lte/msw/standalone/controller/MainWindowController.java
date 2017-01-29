package com.lte.msw.standalone.controller;

import com.lte.msw.standalone.main.MSWStandalone;
import com.lte.msw.standalone.manager.SceneManager;
import com.lte.msw.standalone.view.MainScene;

import javafx.event.ActionEvent;

public class MainWindowController {
	
	private MainScene mainWindow;
	
	public MainWindowController(MainScene mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void addServerHandler(ActionEvent event) {
		mainWindow.refresh();
		MSWStandalone.getMainStage().setScene(SceneManager.getWindowManager().getCreateServerWindow());
	}
	
	public void showVersionsHandler(ActionEvent event) {
		mainWindow.refresh();
		MSWStandalone.getMainStage().setScene(SceneManager.getWindowManager().getServerVersionsWindow());
	}

}