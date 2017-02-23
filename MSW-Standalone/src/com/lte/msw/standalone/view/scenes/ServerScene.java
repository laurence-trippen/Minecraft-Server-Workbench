package com.lte.msw.standalone.view.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.msw.standalone.main.MSWStandalone;
import com.lte.msw.standalone.manager.DesktopManager;
import com.lte.msw.standalone.manager.NetworkManager;
import com.lte.msw.standalone.manager.SceneManager;
import com.lte.msw.standalone.model.Server;
import com.lte.msw.standalone.model.abstracts.ResourcePath;
import com.lte.msw.standalone.model.interfaces.IRefreshable;

import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ServerScene extends MSWScene implements IRefreshable {

	private AnchorPane 					mainPane;
	private Pane 						serverPane;
	private Pane						consolePane;
	private Pane						vboxPane;
	private HBox						hBox;
	private VBox						vBox;
	private Server 						server;
	private ToolBar 					toolBar;
	private Button 						closeButton;
	private Button						consoleExecuteButton;
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
	private Label						consoleLabel;
	private Button						serverControlButton;
	private Button						serverConfigureButton;
	private Button						serverBackupButton;
	private Button						serverLogButton;
	private TextArea					consoleTextArea;
	private TextField					consoleTextField;
	
	public ServerScene() {
		super(new AnchorPane(), DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		this.initNodes();
		this.defineNodes();
		this.registerNodeEvents();
	}
	
	@Override
	protected void initNodes() {
		this.mainPane = (AnchorPane)this.getRoot();
		this.consoleLabel = new Label("Server Konsole:");
		this.serverNameLabel = new Label();
		this.serverAdressLabel = new Label();
		this.serverVersionLabel = new Label();
		this.serverGameModeLabel = new Label();
		this.serverGameModeValue = new Label();
		this.serverDifficultyLabel = new Label();
		this.serverDifficultyValue = new Label();
		this.serverPvpLabel = new Label();
		this.serverPvpValue = new Label();
		this.serverPane = new Pane();
		this.consolePane = new Pane();
		this.vboxPane = new Pane();
		this.hBox = new HBox();
		this.vBox = new VBox();
		this.consoleTextArea = new TextArea();
		this.consoleTextField = new TextField();
		this.closeButton = new Button("Zurück");
		this.serverLogButton = new Button("Server Log");
		this.serverBackupButton = new Button("Server Backup");
		this.consoleExecuteButton = new Button("Ausführen");
		this.serverConfigureButton = new Button("Server Konfigurieren");
		this.serverControlButton = new Button("Starten");
	}

	@Override
	protected void defineNodes() {
		this.getStylesheets().clear();
		this.getStylesheets().add(getClass().getResource(ResourcePath.CSS).toExternalForm());
		try {
			mainPane.setBackground(new Background(
					new BackgroundImage(new Image(new FileInputStream(ResourcePath.BACKGROUND)), null, null, null, null)
			));
			this.serverImageView = new ImageView(new Image(new FileInputStream(ResourcePath.SERVER_72_PNG)));
			this.serverGameModeImageView = new ImageView(new Image(new FileInputStream(ResourcePath.GAMEMODE_32_PNG)));
			this.serverDifficultyImageView = new ImageView(new Image(new FileInputStream(ResourcePath.DIFFICULTY_32_PNG)));
			this.serverPvpImageView = new ImageView(new Image(new FileInputStream(ResourcePath.PVP_32_PNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.serverImageView.setLayoutX(60);
		this.serverImageView.setLayoutY(60);

		this.serverNameLabel.setFont(Font.font("System", FontWeight.BOLD, 22));
		this.serverNameLabel.setLayoutX(180);
		this.serverNameLabel.setLayoutY(60);

		this.serverAdressLabel.setFont(Font.font("System", FontWeight.BOLD, 22));
		this.serverAdressLabel.setLayoutX(450);
		this.serverAdressLabel.setLayoutY(60);

		this.serverVersionLabel.setFont(Font.font("System", 20));
		this.serverVersionLabel.setLayoutX(180);
		this.serverVersionLabel.setLayoutY(100);
		
		this.serverGameModeImageView.setLayoutX(60);
		this.serverGameModeImageView.setLayoutY(185);
		
		this.serverGameModeLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverGameModeLabel.setLayoutX(110);
		this.serverGameModeLabel.setLayoutY(190);

		this.serverGameModeValue.setFont(Font.font("System", 18));
		this.serverGameModeValue.setLayoutX(330);
		this.serverGameModeValue.setLayoutY(190);
		
		this.serverDifficultyImageView.setLayoutX(60);
		this.serverDifficultyImageView.setLayoutY(235);

		this.serverDifficultyLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverDifficultyLabel.setLayoutX(110);
		this.serverDifficultyLabel.setLayoutY(240);

		this.serverDifficultyValue.setFont(Font.font("System", 18));
		this.serverDifficultyValue.setLayoutX(330);
		this.serverDifficultyValue.setLayoutY(240);
		
		this.serverPvpImageView.setLayoutX(60);
		this.serverPvpImageView.setLayoutY(285);

		this.serverPvpLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverPvpLabel.setLayoutX(110);
		this.serverPvpLabel.setLayoutY(290);

		this.serverPvpValue.setFont(Font.font("System", 18));
		this.serverPvpValue.setLayoutX(330);
		this.serverPvpValue.setLayoutY(290);

		this.serverPane.getStyleClass().add("msw-white-pane");
		this.serverPane.setPrefWidth(800);
		this.serverPane.setPrefHeight(460);
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
		
		this.consoleLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.consoleLabel.setLayoutX(30);
		this.consoleLabel.setLayoutY(30);

		this.consoleTextArea.setEditable(false);
		this.consoleTextArea.setLayoutX(30);
		this.consoleTextArea.setLayoutY(60);
		this.consoleTextArea.setPrefWidth(740);
		this.consoleTextArea.setPrefHeight(170);

		this.consoleTextField.setPromptText("Befehl eingeben");
		this.consoleTextField.getStyleClass().add("msw-left-border-radius");
		this.consoleTextField.setLayoutX(30);
		this.consoleTextField.setLayoutY(250);
		this.consoleTextField.setPrefWidth(600);

		this.consoleExecuteButton.getStyleClass().add("msw-right-border-radius");
		this.consoleExecuteButton.setLayoutX(630);
		this.consoleExecuteButton.setLayoutY(250);
		this.consoleExecuteButton.setPrefWidth(140);

		this.consolePane.getStyleClass().add("msw-white-pane");
		this.consolePane.setLayoutY(500);
		this.consolePane.setPrefWidth(800);
		this.consolePane.setPrefHeight(300);
		this.consolePane.getChildren().addAll(
				consoleTextArea,
				consoleTextField,
				consoleLabel,
				consoleExecuteButton
		);
		this.serverLogButton.getStyleClass().add("msw-right-border-radius");
		this.serverLogButton.setPrefWidth(150);
		this.serverLogButton.setPrefHeight(25);
		this.serverLogButton.setLayoutX(550);
		this.serverLogButton.setLayoutY(370);
		
		this.serverBackupButton.getStyleClass().add("msw-none-border-radius");
		this.serverBackupButton.setPrefWidth(150);
		this.serverBackupButton.setPrefHeight(25);
		this.serverBackupButton.setLayoutX(400);
		this.serverBackupButton.setLayoutY(370);
		
		this.serverConfigureButton.getStyleClass().add("msw-left-border-radius");
		this.serverConfigureButton.setPrefWidth(150);
		this.serverConfigureButton.setPrefHeight(25);
		this.serverConfigureButton.setLayoutX(250);
		this.serverConfigureButton.setLayoutY(370);
		
		this.serverControlButton.setPrefWidth(140);
		this.serverControlButton.setPrefHeight(25);
		this.serverControlButton.setLayoutX(60);
		this.serverControlButton.setLayoutY(370);
		
		this.toolBar = new ToolBar();
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		this.toolBar.getItems().add(closeButton);
		
		this.vboxPane.setPrefHeight(800);
		this.vboxPane.getChildren().addAll(serverPane, consolePane);
		
		this.vBox.setAlignment(Pos.CENTER);
		this.vBox.setPrefWidth(800);
		this.vBox.getChildren().add(vboxPane);
		
		this.hBox.setAlignment(Pos.CENTER);
		this.hBox.getChildren().add(vBox);
		
		mainPane.getChildren().addAll(hBox, toolBar);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(hBox, 0.0);
		AnchorPane.setLeftAnchor(hBox, 0.0);
		AnchorPane.setRightAnchor(hBox, 0.0);
		AnchorPane.setBottomAnchor(hBox, 0.0);
	}

	@Override
	protected void registerNodeEvents() {
		this.closeButton.setOnAction(this::onCloseEvent);
		this.consoleExecuteButton.setOnAction(this::onConsoleExecuteEvent);
		this.serverControlButton.setOnAction(this::onServerStartEvent);
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
	
	private void onCloseEvent(ActionEvent event) {
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getMainScene());
		refresh();
	}
	
	private void onConsoleExecuteEvent(ActionEvent event) {
		System.out.println("Execeute: " + consoleTextField.getText());
		consoleTextField.setText(null);
	}
	
	private void onServerStartEvent(ActionEvent event) {
		server.start();
	}

}