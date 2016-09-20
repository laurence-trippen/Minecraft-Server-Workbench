package com.lte.msw.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.msw.main.Program;
import com.lte.msw.manager.DesktopManager;
import com.lte.msw.manager.NetworkManager;
import com.lte.msw.manager.WindowManager;
import com.lte.msw.model.Path;
import com.lte.msw.model.Server;
import com.lte.msw.model.interfaces.IRefreshable;
import com.lte.msw.view.style.Style;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ServerWindow extends Scene implements IRefreshable {

	private static AnchorPane 			mainPane = new AnchorPane();
	private Server 						server;
	private ToolBar 					toolBar;
	private Button 						closeButton;
	private Pane 						serverPane;
	private ImageView					serverImageView;
	private ImageView					serverGameModeImageView;
	private ImageView					serverDifficultyImageView;
	private ImageView					serverPvpImageView;
	private Label						serverNameLabel;
	private Label						serverVersionLabel;
	private Label						serverGameModeLabel;
	private Label						serverDifficultyLabel;
	private Label						serverPvpLabel;
	private Label						serverGameModeValue;
	private Label						serverDifficultyValue;
	private Label 						serverPvpValue;
	private Label 						serverAdressLabel;
	private Button						serverControlButton;
	private Button						serverConfigureButton;
	private Button						serverBackupButton;
	private Button						serverLogButton;

	public ServerWindow() {
		super(mainPane, DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		try {
			mainPane.setBackground(new Background(
					new BackgroundImage(new Image(new FileInputStream(Path.BACKGROUND)), null, null, null, null)
			));
			this.serverImageView = new ImageView(new Image(new FileInputStream(Path.SERVER_72_PNG)));
			this.serverGameModeImageView = new ImageView(new Image(new FileInputStream(Path.GAMEMODE_32_PNG)));
			this.serverDifficultyImageView = new ImageView(new Image(new FileInputStream(Path.DIFFICULTY_32_PNG)));
			this.serverPvpImageView = new ImageView(new Image(new FileInputStream(Path.PVP_32_PNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.serverImageView.setLayoutX(60);
		this.serverImageView.setLayoutY(60);
		this.serverNameLabel = new Label();
		this.serverNameLabel.setFont(Font.font("System", FontWeight.BOLD, 22));
		this.serverNameLabel.setLayoutX(180);
		this.serverNameLabel.setLayoutY(60);
		this.serverAdressLabel = new Label();
		this.serverAdressLabel.setFont(Font.font("System", FontWeight.BOLD, 22));
		this.serverAdressLabel.setLayoutX(450);
		this.serverAdressLabel.setLayoutY(60);
		this.serverVersionLabel = new Label();
		this.serverVersionLabel.setFont(Font.font("System", 20));
		this.serverVersionLabel.setLayoutX(180);
		this.serverVersionLabel.setLayoutY(100);
		this.serverGameModeImageView.setLayoutX(60);
		this.serverGameModeImageView.setLayoutY(185);
		this.serverGameModeLabel = new Label();
		this.serverGameModeLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverGameModeLabel.setLayoutX(110);
		this.serverGameModeLabel.setLayoutY(190);
		this.serverGameModeValue = new Label();
		this.serverGameModeValue.setFont(Font.font("System", 18));
		this.serverGameModeValue.setLayoutX(330);
		this.serverGameModeValue.setLayoutY(190);
		this.serverDifficultyImageView.setLayoutX(60);
		this.serverDifficultyImageView.setLayoutY(235);
		this.serverDifficultyLabel = new Label();
		this.serverDifficultyLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverDifficultyLabel.setLayoutX(110);
		this.serverDifficultyLabel.setLayoutY(240);
		this.serverDifficultyValue = new Label();
		this.serverDifficultyValue.setFont(Font.font("System", 18));
		this.serverDifficultyValue.setLayoutX(330);
		this.serverDifficultyValue.setLayoutY(240);
		this.serverPvpImageView.setLayoutX(60);
		this.serverPvpImageView.setLayoutY(285);
		this.serverPvpLabel = new Label();
		this.serverPvpLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverPvpLabel.setLayoutX(110);
		this.serverPvpLabel.setLayoutY(290);
		this.serverPvpValue = new Label();
		this.serverPvpValue.setFont(Font.font("System", 18));
		this.serverPvpValue.setLayoutX(330);
		this.serverPvpValue.setLayoutY(290);
		this.closeButton = new Button("Zurück");
		this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Program.getMainStage().setScene(WindowManager.getWindowManager().getMainWindow());
				refresh();
			}
		});
		this.serverPane = new Pane();
		this.serverPane.setStyle(Style.WHITE_PANE);
		this.serverPane.setLayoutX(560);
		this.serverPane.setLayoutY(220);
		this.serverPane.setPrefWidth(800);
		this.serverPane.setPrefHeight(460);
		this.serverLogButton = new Button("Server Log");
		this.serverLogButton.setStyle(Style.BUTTON_RIGHT_RADIUS);
		this.serverLogButton.setPrefWidth(150);
		this.serverLogButton.setPrefHeight(25);
		this.serverLogButton.setLayoutX(500);
		this.serverLogButton.setLayoutY(370);
		this.serverBackupButton = new Button("Server Backup");
		this.serverBackupButton.setStyle(Style.BUTTON_NON_RADIUS);
		this.serverBackupButton.setPrefWidth(150);
		this.serverBackupButton.setPrefHeight(25);
		this.serverBackupButton.setLayoutX(350);
		this.serverBackupButton.setLayoutY(370);
		this.serverConfigureButton = new Button("Server Konfigurieren");
		this.serverConfigureButton.setStyle(Style.BUTTON_LEFT_RADIUS);
		this.serverConfigureButton.setPrefWidth(150);
		this.serverConfigureButton.setPrefHeight(25);
		this.serverConfigureButton.setLayoutX(200);
		this.serverConfigureButton.setLayoutY(370);
		this.serverControlButton = new Button("Starten");
		this.serverControlButton.setPrefWidth(100);
		this.serverControlButton.setPrefHeight(25);
		this.serverControlButton.setLayoutX(60);
		this.serverControlButton.setLayoutY(370);
		this.serverControlButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		this.serverPane.getChildren().addAll(
				serverImageView, 
				serverNameLabel,
				serverVersionLabel,
				serverGameModeLabel,
				serverDifficultyLabel,
				serverPvpLabel,
				serverGameModeImageView,
				serverDifficultyImageView,
				serverPvpImageView,
				serverGameModeValue,
				serverDifficultyValue,
				serverPvpValue,
				serverAdressLabel,
				serverControlButton,
				serverConfigureButton,
				serverBackupButton,
				serverLogButton
		);
		this.toolBar = new ToolBar();
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		this.toolBar.getItems().add(closeButton);
		mainPane.getChildren().addAll(toolBar, serverPane);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
	}

	@Override
	public void refresh() {
		
	}

	public void setServer(Server server) {
		this.server = server;
		this.serverNameLabel.setText(server.getName());
		this.serverVersionLabel.setText(server.getServerVersion().getName());
		this.serverGameModeLabel.setText("Spielemodus:");
		switch (server.getServerProperties().getGameMode()) {
		case CREATIVE:
			this.serverGameModeValue.setText("Kreativmodus");
			break;
		case HARDCORE:
			this.serverGameModeValue.setText("Hardcore");
			break;
		case SURVIVAL:
			this.serverGameModeValue.setText("Überlebensmodus");
			break;
		case VISITOR:
			this.serverGameModeValue.setText("Besuchermodus");
			break;
		}
		this.serverDifficultyLabel.setText("Schwierigkeitsgrad:");
		switch (server.getServerProperties().getDifficulty()) {
		case EASY:
			this.serverDifficultyValue.setText("Einfach");
			break;
		case HARD:
			this.serverDifficultyValue.setText("Schwer");
			break;
		case NORMAL:
			this.serverDifficultyValue.setText("Normal");
			break;
		case PEACEFUL:
			this.serverDifficultyValue.setText("Friedlich");
			break;
		}
		this.serverPvpLabel.setText("Player vs. Player:");
		this.serverPvpValue.setText(server.getServerProperties().isEnabledPVP() ? "Aktiviert" : "Deaktiviert");
		this.serverAdressLabel.setText(NetworkManager.getMyIPAdress() + ":" + server.getServerProperties().getServerPort());
	}

}