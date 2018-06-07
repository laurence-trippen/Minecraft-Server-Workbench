package com.laurencetrippen.msw.standalone.main;

import com.laurencetrippen.msw.standalone.manager.SceneManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class MSWStandalone extends Application {
	
	private static Stage mainStage = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		primaryStage.setScene(SceneManager.getSceneManager().getMainScene());
		primaryStage.setMaximized(true);
		primaryStage.setWidth(1920);
		primaryStage.setHeight(1080);
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