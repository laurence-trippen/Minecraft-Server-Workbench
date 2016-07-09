package com.lte.mcsm.main;

import com.lte.mcsm.controller.WindowManager;
import com.lte.mcsm.model.Server;
import com.lte.mcsm.model.ServerList;
import com.lte.mcsm.model.ServerVersion;

import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	
	private static Stage mainStage = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		primaryStage.setScene(WindowManager.getInstance().getMainWindow());
		primaryStage.setMaximized(true);
		primaryStage.setTitle("LTE: MCSM");
		primaryStage.show();
	}

	public static void main(String[] args) {
		Server server = new Server("JSON-Server");
		ServerList.getInstance().addServer(server);
		ServerList.getInstance().addServerVersion(new ServerVersion("1.7.9"));
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
	
	public static Stage getMainStage() {
		return mainStage;
	}

}
