package com.lte.mcsm.main;

import com.lte.mcsm.model.Server;
import com.lte.mcsm.model.ServerList;
import com.lte.mcsm.view.MainWindow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Kommentar AlternateGUI
		primaryStage.setScene(new Scene(new MainWindow(), 1024, 768));
		primaryStage.setMaximized(true);
		primaryStage.setTitle("LTE: MCSM");
		primaryStage.show();
	}

	public static void main(String[] args) {
		ServerList.getInstance().addServer(new Server("Inge-Server"));
		launch(args);
	}

}
