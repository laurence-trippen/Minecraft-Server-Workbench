package com.lte.mcsm.main;

import com.lte.mcsm.manager.WindowManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	
	private static Stage mainStage = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		primaryStage.setScene(WindowManager.getWindowManager().getMainWindow());
		primaryStage.setMaximized(true);
		primaryStage.setWidth(1920);
		primaryStage.setHeight(1200);
		primaryStage.setTitle("Minecraft Server Workbench");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getMainStage() {
		return mainStage;
	}

}