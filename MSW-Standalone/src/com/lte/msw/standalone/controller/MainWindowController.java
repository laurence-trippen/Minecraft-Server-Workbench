package com.lte.msw.standalone.controller;

import com.lte.msw.standalone.main.MSWStandalone;
import com.lte.msw.standalone.manager.WindowManager;
import com.lte.msw.standalone.view.MainWindow;

import javafx.event.ActionEvent;

public class MainWindowController {
	
	private MainWindow mainWindow;
	
	public MainWindowController(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void addServerHandler(ActionEvent event) {
		mainWindow.refresh();
		MSWStandalone.getMainStage().setScene(WindowManager.getWindowManager().getCreateServerWindow());
	}
	
	public void showVersionsHandler(ActionEvent event) {
		mainWindow.refresh();
		MSWStandalone.getMainStage().setScene(WindowManager.getWindowManager().getServerVersionsWindow());
	}

}