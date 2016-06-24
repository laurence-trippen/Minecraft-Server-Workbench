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
		primaryStage.setScene(new Scene(new MainWindow(), 1024, 768));
		primaryStage.setFullScreen(true); 
		primaryStage.setMaximized(true);
		primaryStage.setTitle("LTE: MCSM");
		primaryStage.show();
	}

	public static void main(String[] args) {
		Server server = new Server("JSON-Server");
		ServerList.getInstance().addServer(server);
		server.getServerOPS().readEntries();
		server.getServerOPS().printEntries();
		server.getServerWhitelist().readEntries();
		server.getServerWhitelist().printEntries();
		server.getServerBannedPlayers().readEntries();
		server.getServerBannedPlayers().printEntries();
		server.getServerBannedIps().readEntries();
		server.getServerBannedIps().printEntries();
		launch(args);
	}

}
