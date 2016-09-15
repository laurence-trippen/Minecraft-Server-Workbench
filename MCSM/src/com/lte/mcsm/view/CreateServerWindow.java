package com.lte.mcsm.view;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.lte.mcsm.main.Program;
import com.lte.mcsm.manager.DesktopManager;
import com.lte.mcsm.manager.WindowManager;
import com.lte.mcsm.model.Path;
import com.lte.mcsm.model.Server;
import com.lte.mcsm.model.ServerCreator;
import com.lte.mcsm.model.ServerList;
import com.lte.mcsm.model.ServerVersion;
import com.lte.mcsm.model.enums.DataStatus;
import com.lte.mcsm.model.interfaces.IRefreshable;
import com.lte.mcsm.view.style.Style;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class CreateServerWindow extends Scene implements IRefreshable {
	
	private static AnchorPane 			mainPane = new AnchorPane();
	private static File 				oldServerJar = null;
	private static File 				newServerJar = null;
	private ToolBar 					toolBar;
	private Button 						closeButton;
	private Pane 						createServerPane;
	private Button 						createServerButton;
	private Label 						createServerLabel;
	private Label 						serverNameLabel;
	private Label 						serverVersionLabel;
	private Label 						serverEulaLabel;
	private ImageView 					createServerImageView;
	private TextField 					serverNameTextField;
	private ChoiceBox<ServerVersion> 	serverVersionChoiceBox;
	private CheckBox 					serverEulaCheckBox;
	private Hyperlink 					serverEulaHyperlink;
	private Pane 						installationPane;
	private ProgressBar 				progressBar;
	private Label 						progressLabel;
	
	public CreateServerWindow() {
		super(mainPane, DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		try {
			mainPane.setBackground(new Background(new BackgroundImage(new Image(
					new FileInputStream(Path.BACKGROUND)), 
					null,
					null,
					null,
					null
			)));
			this.createServerImageView = new ImageView(new Image(new FileInputStream(
					Path.CREATE_SERVER_PNG
			)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.progressLabel = new Label();
		this.progressLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.progressLabel.setLayoutX(400);
		this.progressLabel.setLayoutY(40);
		this.progressBar = new ProgressBar(0);
		this.progressBar.setLayoutX(40);
		this.progressBar.setLayoutY(40);
		this.progressBar.setPrefWidth(340);
		this.progressBar.setPrefHeight(20);
		this.installationPane = new Pane();
		this.installationPane.setStyle("-fx-background-color: white; -fx-background-radius: 5");
		this.installationPane.setPrefWidth(600);
		this.installationPane.setPrefHeight(100);
		this.installationPane.setLayoutX(660);
		this.installationPane.setLayoutY(780);
		this.installationPane.setVisible(false);
		this.installationPane.getChildren().addAll(progressBar, progressLabel);
		this.closeButton = new Button("Abbrechen");
		this.toolBar = new ToolBar();
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		this.toolBar.getItems().add(closeButton);
		this.serverNameLabel = new Label("Servername:");
		this.serverNameLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverNameLabel.setLayoutX(90);
		this.serverNameLabel.setLayoutY(170);
		this.serverNameTextField = new TextField();
		this.serverNameTextField.setPrefWidth(215);
		this.serverNameTextField.setLayoutX(260);
		this.serverNameTextField.setLayoutY(170);
		this.serverVersionLabel = new Label("Serverversion:");
		this.serverVersionLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverVersionLabel.setLayoutX(90);
		this.serverVersionLabel.setLayoutY(230);
		this.serverVersionChoiceBox = new ChoiceBox<>();
		this.serverVersionChoiceBox.setItems(ServerList.getServerList().getServerVersions());
		this.serverVersionChoiceBox.getSelectionModel().selectFirst();
		this.serverVersionChoiceBox.setPrefWidth(80);
		this.serverVersionChoiceBox.setLayoutX(260);
		this.serverVersionChoiceBox.setLayoutY(230);
		this.serverEulaLabel = new Label("Server EULA:");
		this.serverEulaLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverEulaLabel.setLayoutX(90);
		this.serverEulaLabel.setLayoutY(290);
		this.serverEulaCheckBox = new CheckBox("EULA akzeptieren");
		this.serverEulaCheckBox.setLayoutX(260);
		this.serverEulaCheckBox.setLayoutY(295);
		this.serverEulaHyperlink = new Hyperlink("Minecraft EULA");
		this.serverEulaHyperlink.setLayoutX(388);
		this.serverEulaHyperlink.setLayoutY(293);
		this.createServerImageView.setLayoutX(90);
		this.createServerImageView.setLayoutY(50);
		this.createServerLabel = new Label("Minecraft Server erstellen");
		this.createServerLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
		this.createServerLabel.setLayoutX(185);
		this.createServerLabel.setLayoutY(62);
		this.createServerPane = new Pane();
		this.createServerPane.setStyle(Style.WHITE_PANE);
		this.createServerPane.setPrefWidth(600);
		this.createServerPane.setPrefHeight(450);
		this.createServerPane.setLayoutX(660);
		this.createServerPane.setLayoutY(300);
		this.createServerButton = new Button("Server erstellen");
		this.createServerButton.setPrefWidth(120);
		this.createServerButton.setPrefHeight(30);
		this.createServerButton.setLayoutX(355);
		this.createServerButton.setLayoutY(350);
		this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				refresh();
				Program.getMainStage().setScene(WindowManager.getWindowManager().getMainWindow());
			}
		});
		this.serverEulaHyperlink.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Desktop.getDesktop().browse(new URI("https://account.mojang.com/documents/minecraft_eula"));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		this.createServerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (serverNameTextField.getText().equals("")) {
					serverNameTextField.setStyle("-fx-border-color: red; -fx-border-radius: 3");
				} else {
					serverNameTextField.setStyle("");
				}
				if (!serverEulaCheckBox.isSelected()) {
					serverEulaCheckBox.setStyle("-fx-border-color: red; -fx-border-radius: 3");
				} else {
					serverEulaCheckBox.setStyle("");
				}
				if ((!serverNameTextField.getText().equals("")) && serverEulaCheckBox.isSelected()) {
					if (ServerList.getServerList().existServer(serverNameTextField.getText())) {
						closeButton.setDisable(true);
						createServerButton.setDisable(true);
						serverNameTextField.setDisable(true);
						serverVersionChoiceBox.setDisable(true);
						serverEulaCheckBox.setDisable(true);
						if (ServerCreator.createServerDirectory(Path.SERVER_DIRECTORY + serverNameTextField.getText())) {
							oldServerJar = new File(serverVersionChoiceBox.getSelectionModel().getSelectedItem().getPath());
							newServerJar = new File(Path.SERVER_DIRECTORY + serverNameTextField.getText() + "/" + oldServerJar.getName());
							if (ServerCreator.copyServerJar(oldServerJar.getAbsolutePath(), newServerJar.getAbsolutePath())) {
								if (ServerCreator.runServerJar(newServerJar.getAbsolutePath(), newServerJar.getParentFile().getPath(), true)) {
									if (ServerCreator.editEula(Path.SERVER_DIRECTORY + serverNameTextField.getText() + "/eula.txt")) {
										if (ServerCreator.runServerJar(newServerJar.getAbsolutePath(), newServerJar.getParentFile().getPath(), false)) {
											if (ServerCreator.checkServer(serverNameTextField.getText())) {
												DataStatus status = ServerList.getServerList().addServer(new Server(
														serverNameTextField.getText(),
														new ServerVersion(
																serverVersionChoiceBox.getSelectionModel().getSelectedItem().getName(),
																newServerJar.getPath()
												)));
												switch (status) {
												case Succcess:
													Alert successAlert = new Alert(AlertType.INFORMATION);
													successAlert.setTitle("Server erstellen erfolgreich!");
													successAlert.setHeaderText(serverNameTextField.getText());
													successAlert.setContentText("Minecraft Server " + serverNameTextField.getText() + " wurde erfolgreich erstellt!");
													successAlert.showAndWait();
													break;
												case Error:
													Alert errorAlert = new Alert(AlertType.ERROR);
													errorAlert.setTitle("Installation fehlgeschlagen!");
													errorAlert.setHeaderText(serverNameTextField.getText());
													errorAlert.setContentText("Fehler beim erstellen von " + serverNameTextField.getText() + " !");
													errorAlert.showAndWait();
													break;
												default:
													break;
												}
												refresh();
												Program.getMainStage().setScene(WindowManager.getWindowManager().getMainWindow());
											} else {
												Alert errorAlert = new Alert(AlertType.ERROR);
												errorAlert.setTitle("Installation fehlgeschlagen!");
												errorAlert.setHeaderText(serverNameTextField.getText());
												errorAlert.setContentText("Fehler beim erstellen von " + serverNameTextField.getText() + " !");
												errorAlert.showAndWait();
												refresh();
												Program.getMainStage().setScene(WindowManager.getWindowManager().getMainWindow());
											}
										}
									}
								}
							}
						}
					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Fehler!");
						alert.setContentText("Servername \"" + serverNameTextField.getText() + " \" existiert schon!");
						alert.showAndWait();
					}
				}
			}
		});
		this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				refresh();
				Program.getMainStage().setScene(WindowManager.getWindowManager().getMainWindow());
			}
		});
		this.createServerPane.getChildren().addAll(
				createServerLabel,
				createServerButton,
				createServerImageView, 
				serverNameLabel,
				serverNameTextField,
				serverVersionLabel,
				serverVersionChoiceBox,
				serverEulaLabel,
				serverEulaCheckBox,
				serverEulaHyperlink
		);
		mainPane.getChildren().addAll(toolBar, createServerPane, installationPane);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
	}
	
	@Override
	public void refresh() {
		this.serverNameTextField.setText("");
		this.serverNameTextField.setStyle("");
		this.serverEulaCheckBox.setSelected(false);
		this.serverEulaCheckBox.setStyle("");
		this.serverVersionChoiceBox.getSelectionModel().selectFirst();
		this.createServerButton.setDisable(false);
		this.installationPane.setDisable(false);
		this.progressBar.setProgress(0.00);
		this.progressLabel.setText("");
	}
	
}