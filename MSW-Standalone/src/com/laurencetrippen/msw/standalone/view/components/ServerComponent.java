package com.laurencetrippen.msw.standalone.view.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.laurencetrippen.msw.standalone.main.MSWStandalone;
import com.laurencetrippen.msw.standalone.manager.SceneManager;
import com.laurencetrippen.msw.standalone.model.Server;
import com.laurencetrippen.msw.standalone.model.abstracts.ResourcePath;
import com.laurencetrippen.msw.standalone.model.interfaces.IRefreshable;
import com.laurencetrippen.msw.standalone.view.scenes.ServerScene;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ServerComponent extends MSWComponent implements IRefreshable {
	
	private static final String START = "Starten";
	private static final String STOP = "Stoppen";
	private static final String SHOW = "Anzeigen";
	
	private Pane serverPane;
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
		this.initNodes();
		this.defineNodes();
		this.registerNodeEvents();
	}
	
	@Override
	protected void initNodes() {
		this.splitMenuButton = new SplitMenuButton();
		this.miStart = new MenuItem(START);
		this.miStop = new MenuItem(STOP);
		this.miShow = new MenuItem(SHOW);
		this.lbServerName = new Label();
		this.lbServerVersion = new Label(server.getServerVersion().getName());
		this.lbServerPlayer = new Label("0 / " + server.getServerProperties().getMaxPlayers());
		this.miServerItem = new MenuItem("Test");
		this.serverMenu = new Menu("Server");
		this.serverBar = new MenuBar();
		this.serverPane = new Pane();
	}

	@Override
	protected void defineNodes() {
		this.setPrefWidth(635);
		this.setPrefHeight(312);
		
		try {
			this.imageView = new ImageView(new Image(new FileInputStream(ResourcePath.SERVER_PNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		this.splitMenuButton.setText(SHOW);
		this.splitMenuButton.setPrefWidth(113);
		this.splitMenuButton.setPrefHeight(25);
		this.splitMenuButton.setLayoutX(335);
		this.splitMenuButton.setLayoutY(92);
		this.splitMenuButton.getItems().addAll(miStart, miStop, miShow);

		this.lbServerName.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.lbServerName.setLayoutX(106);
		this.lbServerName.setLayoutY(41);
		this.lbServerName.setText(server.getName());

		this.lbServerVersion.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.lbServerVersion.setLayoutX(405);
		this.lbServerVersion.setLayoutY(41);

		this.lbServerPlayer.setFont(Font.font("System", 12));
		this.lbServerPlayer.setLayoutX(106);
		this.lbServerPlayer.setLayoutY(65);
		this.imageView.setLayoutX(26);
		this.imageView.setLayoutY(41);

		this.serverMenu.getItems().add(miServerItem);
		this.serverBar.setLayoutX(0.00);
		this.serverBar.setLayoutY(0.00);
		this.serverBar.setPrefWidth(468.0);
		this.serverBar.setPrefHeight(25.00);
		this.serverBar.getMenus().add(serverMenu);

		this.serverPane.getStyleClass().add("msw-white-pane");
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
	protected void registerNodeEvents() {
		this.splitMenuButton.setOnAction(this::onSplitButtonEvent);
		this.miStart.setOnAction(this::onSelectStartEvent);
		this.miStop.setOnAction(this::onSelectStopEvent);
		this.miShow.setOnAction(this::onSelectShowEvent);
	}
	
	@Override
	public void refresh() {
		splitMenuButton.setText(SHOW);
	}
	

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	private void onSplitButtonEvent(ActionEvent event) {
		switch (splitMenuButton.getText()) {
		case START:
			System.out.println("[" + server.getName() + "] Starten");
			break;
		case STOP:
			System.out.println("[" + server.getName() + "] Stoppen");
			break;
		case SHOW:
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
	
	private void onSelectStartEvent(ActionEvent event) {
		splitMenuButton.setText(START);
	}
	
	private void onSelectStopEvent(ActionEvent event) {
		splitMenuButton.setText(STOP);
	}
	
	private void onSelectShowEvent(ActionEvent event) {
		splitMenuButton.setText(SHOW);
	}

}