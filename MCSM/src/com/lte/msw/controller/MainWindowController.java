package com.lte.msw.controller;

import com.lte.mcsm.manager.WindowManager;
import com.lte.msw.main.Program;
import com.lte.msw.view.MainWindow;

import javafx.event.ActionEvent;

public class MainWindowController {
	
	private MainWindow mainWindow;
	
	public MainWindowController(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void addServerHandler(ActionEvent event) {
		mainWindow.refresh();
		Program.getMainStage().setScene(WindowManager.getWindowManager().getCreateServerWindow());
	}
	
	public void showVersionsHandler(ActionEvent event) {
		mainWindow.refresh();
		Program.getMainStage().setScene(WindowManager.getWindowManager().getServerVersionsWindow());
	}

}