package com.lte.mcsm.main;

import com.lte.mcsm.model.Server;
import com.lte.mcsm.model.ServerList;
import com.lte.mcsm.model.ServerVersion;
import com.lte.mcsm.view.CreateServerWindow;
import com.lte.mcsm.view.MainWindow;
import com.lte.mcsm.view.ServerVersionsWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	
	private static Stage mainStage = null;
	private static MainWindow mainWindow = null;
	private static CreateServerWindow createServerWindow = null;
	private static ServerVersionsWindow serverVersionsWindow = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		mainWindow = new MainWindow();
		createServerWindow = new CreateServerWindow();
		serverVersionsWindow = new ServerVersionsWindow();
		primaryStage.setScene(mainWindow);
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

	public static MainWindow getMainWindow() {
		return mainWindow;
	}

	public static CreateServerWindow getCreateServerWindow() {
		return createServerWindow;
	}

	public static ServerVersionsWindow getServerVersionsWindow() {
		return serverVersionsWindow;
	}

}
