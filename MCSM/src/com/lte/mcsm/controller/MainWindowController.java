package com.lte.mcsm.controller;

import com.lte.mcsm.main.Program;
import com.lte.mcsm.manager.WindowManager;
import com.lte.mcsm.view.MainWindow;

import javafx.event.ActionEvent;

public class MainWindowController {
	
	private MainWindow mainWindow;
	
	public MainWindowController(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void addServerButtonHandler(ActionEvent event) {
		mainWindow.refresh();
		Program.getMainStage().setScene(WindowManager.getWindowManager().getCreateServerWindow());
	}
	
	public void showVersionsHandler(ActionEvent event) {
		mainWindow.refresh();
		Program.getMainStage().setScene(WindowManager.getWindowManager().getServerVersionsWindow());
	}

}
