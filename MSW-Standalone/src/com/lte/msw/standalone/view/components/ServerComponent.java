package com.lte.msw.standalone.view.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.msw.standalone.main.MSWStandalone;
import com.lte.msw.standalone.manager.SceneManager;
import com.lte.msw.standalone.model.Server;
import com.lte.msw.standalone.model.abstracts.ResourcePath;
import com.lte.msw.standalone.model.interfaces.IRefreshable;
import com.lte.msw.standalone.view.scenes.ServerScene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ServerComponent extends AnchorPane implements IRefreshable {

	private AnchorPane serverPane;
	private MenuBar serverBar;
	private Menu serverMenu;
	private MenuItem miServerItem;
	private ImageView imageView;
	private Label lbServerName;
	private Label lbServerVersion;
	private Label lbServerPlayer;
	private SplitMenuButton splitMenuButton;
	private MenuItem miStart;
	private MenuItem miStop;
	private MenuItem miShow;
	private Server server;

	public ServerComponent(Server server) {
		this.server = server;
		try {
			this.imageView = new ImageView(new Image(new FileInputStream(ResourcePath.SERVER_PNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.splitMenuButton = new SplitMenuButton();
		this.splitMenuButton.setText(ServerMenuComponent.OPTIONS);
		this.splitMenuButton.setPrefWidth(113);
		this.splitMenuButton.setPrefHeight(25);
		this.splitMenuButton.setLayoutX(335);
		this.splitMenuButton.setLayoutY(92);
		this.splitMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switch (splitMenuButton.getText()) {
				case ServerMenuComponent.START:
					System.out.println("[" + server.getName() + "] Starten");
					break;
				case ServerMenuComponent.STOP:
					System.out.println("[" + server.getName() + "] Stoppen");
					break;
				case ServerMenuComponent.SHOW:
					ServerScene serverScene = SceneManager.getSceneManager().getServerScene();
					serverScene.setServer(server);
					MSWStandalone.getMainStage().setScene(serverScene);
					System.out.println("[" + server.getName() + "] Anzeigen");
					break;
				default:
					System.out.println("[" + server.getName() + "] Default");
					break;
				}
			}
		});
		this.miStart = new MenuItem("Starten");
		this.miStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				splitMenuButton.setText(ServerMenuComponent.START);
			}
		});
		this.miStop = new MenuItem("Stoppen");
		this.miStop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				splitMenuButton.setText(ServerMenuComponent.STOP);
			}
		});
		this.miShow = new MenuItem("Anzeigen");
		this.miShow.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				splitMenuButton.setText(ServerMenuComponent.SHOW);
			}
		});
		this.splitMenuButton.getItems().addAll(miStart, miStop, miShow);
		this.lbServerName = new Label();
		this.lbServerName.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.lbServerName.setLayoutX(106);
		this.lbServerName.setLayoutY(41);
		this.lbServerName.setText(server.getName());
		this.lbServerVersion = new Label(server.getServerVersion().getName());
		this.lbServerVersion.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.lbServerVersion.setLayoutX(405);
		this.lbServerVersion.setLayoutY(41);
		this.lbServerPlayer = new Label("0 / " + server.getServerProperties().getMaxPlayers());
		this.lbServerPlayer.setFont(Font.font("System", 12));
		this.lbServerPlayer.setLayoutX(106);
		this.lbServerPlayer.setLayoutY(65);
		this.imageView.setLayoutX(26);
		this.imageView.setLayoutY(41);
		this.miServerItem = new MenuItem("Test");
		this.serverMenu = new Menu("Server");
		this.serverMenu.getItems().add(miServerItem);
		this.serverBar = new MenuBar();
		this.serverBar.setLayoutX(0.00);
		this.serverBar.setLayoutY(0.00);
		this.serverBar.setPrefWidth(468.0);
		this.serverBar.setPrefHeight(25.00);
		this.serverBar.getMenus().add(serverMenu);
		this.serverPane = new AnchorPane();
		this.serverPane.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		this.serverPane.setLayoutX(89);
		this.serverPane.setLayoutY(68);
		this.serverPane.setPrefWidth(468.0);
		this.serverPane.setPrefHeight(130.0);
		this.serverPane.getChildren().addAll(
				serverBar, 
				imageView, 
				lbServerName, 
				lbServerVersion, 
				lbServerPlayer, 
				splitMenuButton
		);
		this.getChildren().add(serverPane);
	}
	
	@Override
	public void refresh() {
		splitMenuButton.setText(ServerMenuComponent.OPTIONS);
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

}