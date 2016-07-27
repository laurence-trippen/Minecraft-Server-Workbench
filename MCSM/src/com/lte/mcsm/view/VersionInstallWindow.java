package com.lte.mcsm.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.mcsm.controller.DesktopManager;
import com.lte.mcsm.controller.WindowManager;
import com.lte.mcsm.main.Program;
import com.lte.mcsm.model.Path;
import com.lte.mcsm.model.ServerList;
import com.lte.mcsm.model.ServerVersion;
import com.lte.mcsm.model.ServerVersionTester;
import com.lte.mcsm.model.enums.DataStatus;
import com.lte.mcsm.model.interfaces.IRefreshable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class VersionInstallWindow extends Scene implements IRefreshable {
	
	private static AnchorPane mainPane = new AnchorPane();
	private File selectedJarFile;
	private Pane installSetupPane;
	private Pane installationPane;
	private ToolBar toolBar;
	private Button closeButton;
	private Label versionNameLabel;
	private Label versionJarLabel;
	private Label installVersionLabel;
	private Label jarNameLabel;
	private Label progressLabel;
	private TextField versionNameTextField;
	private Button versionJarButton;
	private Button installVersionButton;
	private ImageView installVersionImageView;
	private ImageView jarVersionImageView;
	private Separator headerSeperator;
	private Separator footerSeperator;
	private ProgressBar progressBar;
	
	public VersionInstallWindow() {
		super(mainPane, DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		try {
			mainPane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream(Path.BACKGROUND)), null, null, null, null)));
			this.installVersionImageView = new ImageView(new Image(new FileInputStream(Path.InstallVersionPNG)));
			this.jarVersionImageView = new ImageView(new Image(new FileInputStream(Path.JarPNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.progressLabel = new Label("JAR Datei wird überprüft ...");
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
		this.installationPane.setLayoutY(740);
		this.installationPane.setVisible(false);
		this.installationPane.getChildren().addAll(progressBar, progressLabel);
		this.jarVersionImageView.setLayoutX(80);
		this.jarVersionImageView.setLayoutY(278);
		this.jarVersionImageView.setVisible(false);
		this.jarNameLabel = new Label();
		this.jarNameLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.jarNameLabel.setLayoutX(120);
		this.jarNameLabel.setLayoutY(285);
		this.jarNameLabel.setVisible(false);
		this.versionNameLabel = new Label("Server Versions Name:");
		this.versionNameLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.versionNameLabel.setLayoutX(80);
		this.versionNameLabel.setLayoutY(140);
		this.versionNameTextField = new TextField();
		this.versionNameTextField.setPrefWidth(210);
		this.versionNameTextField.setLayoutX(290);
		this.versionNameTextField.setLayoutY(140);
		this.versionJarLabel = new Label("Server Version: (*.jar)");
		this.versionJarLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.versionJarLabel.setLayoutX(80);
		this.versionJarLabel.setLayoutY(200);
		this.versionJarButton = new Button("Minecraft-Server JAR auswählen ...");
		this.versionJarButton.setPrefWidth(210);
		this.versionJarButton.setLayoutX(290);
		this.versionJarButton.setLayoutY(200);
		this.versionJarButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage fileChooserStage = new Stage();
				fileChooserStage.centerOnScreen();
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Minecraft Server JAR auswählen");
				fileChooser.getExtensionFilters().addAll(
				         new ExtensionFilter("Minecraft Server JAR", "*.jar")
				);
				selectedJarFile = fileChooser.showOpenDialog(fileChooserStage);
				if (selectedJarFile != null) {
					jarVersionImageView.setVisible(true);
					jarNameLabel.setVisible(true);
					jarNameLabel.setText(selectedJarFile.getName());
				}
			}
		});
		this.headerSeperator = new Separator();
		this.headerSeperator.setLayoutX(80);
		this.headerSeperator.setLayoutY(105);
		this.headerSeperator.setPrefWidth(420);
		this.footerSeperator = new Separator();
		this.footerSeperator.setLayoutX(80);
		this.footerSeperator.setLayoutY(260);
		this.footerSeperator.setPrefWidth(420);
		this.installVersionLabel = new Label("Server Version installieren");
		this.installVersionLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
		this.installVersionLabel.setLayoutX(165);
		this.installVersionLabel.setLayoutY(37);
		this.installVersionImageView.setLayoutX(80);
		this.installVersionImageView.setLayoutY(30);
		this.installVersionButton = new Button("Installieren");
		this.installVersionButton.setPrefWidth(100);
		this.installVersionButton.setPrefHeight(30);
		this.installVersionButton.setLayoutX(400);
		this.installVersionButton.setLayoutY(280);
		this.installVersionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (versionNameTextField.getText().equals("")) {
					versionNameTextField.setStyle("-fx-border-color: red; -fx-border-radius: 3");
				} else {
					versionNameTextField.setStyle("");
				}
				if (selectedJarFile == null) {
					versionJarButton.setStyle("-fx-border-color: red; -fx-border-radius: 3");
				} else {
					versionJarButton.setStyle("");
				}
				if ((!versionNameTextField.getText().equals("")) && selectedJarFile != null) {		
					Alert alert = new Alert(AlertType.INFORMATION);
					installationPane.setVisible(true);
					progressBar.setProgress(0.2);
					closeButton.setDisable(true);
					versionNameTextField.setDisable(true);
					versionJarButton.setDisable(true);
					installVersionButton.setDisable(true);
					if (new ServerVersionTester().testVersion(selectedJarFile)) {
						progressBar.setProgress(1.0);
						DataStatus status = ServerList.getServerList().addServerVersion(new ServerVersion(
								versionNameTextField.getText(), ""));
						switch (status) {
						case Succcess:
							progressLabel.setText("Installation erfolgreich!");
							alert.setTitle("Installation Erfolgreich!");
							alert.setHeaderText(versionNameTextField.getText());
							alert.setContentText("Minecraft Version " + versionNameTextField.getText() + " wurde erfolgreich installiert!\n");
							alert.showAndWait();
							break;
						case Exists:
							progressLabel.setText("Installation fehlgeschlagen!");
							alert.setAlertType(AlertType.ERROR);
							alert.setTitle("Installation fehlgeschlagen!");
							alert.setHeaderText(versionNameTextField.getText());
							alert.setContentText("Minecraft Version " + versionNameTextField.getText() + " existiert schon!\n");
							alert.showAndWait();
						default:
							break;
						}
						refresh();
						Program.getMainStage().setScene(WindowManager.getWindowManager().getServerVersionsWindow());
					} else {
						alert.setAlertType(AlertType.ERROR);
						alert.setTitle("Falsches JAR Archive");
						alert.setHeaderText("Keine Minecraft Server JAR");
						alert.setContentText(selectedJarFile.getName() + " ist keine Minecraft Server JAR!");
						alert.showAndWait();
						refresh();
						Program.getMainStage().setScene(WindowManager.getWindowManager().getServerVersionsWindow());
					}
				}
			}
		});
		this.installSetupPane = new Pane();
		this.installSetupPane.setStyle("-fx-background-color: white; -fx-background-radius: 5");
		this.installSetupPane.setPrefWidth(600);
		this.installSetupPane.setPrefHeight(400);
		this.installSetupPane.setLayoutX(660);
		this.installSetupPane.setLayoutY(300);
		this.installSetupPane.getChildren().addAll(
				versionNameLabel, 
				versionNameTextField, 
				versionJarLabel,
				versionJarButton,
				installVersionButton,
				installVersionImageView,
				installVersionLabel,
				jarNameLabel,
				jarVersionImageView,
				headerSeperator,
				footerSeperator
		);
		this.closeButton = new Button("Abbrechen");
		this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				refresh();
				Program.getMainStage().setScene(WindowManager.getWindowManager().getServerVersionsWindow());
			}
		});
		this.toolBar = new ToolBar();
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		this.toolBar.getItems().add(closeButton);
		mainPane.getChildren().addAll(toolBar, installSetupPane, installationPane);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
	}
	
	@Override
	public void refresh() {
		closeButton.setDisable(false);
		installVersionButton.setDisable(false);
		versionNameTextField.setText(null);
		versionNameTextField.setDisable(false);
		versionJarButton.setDisable(false);
		jarVersionImageView.setVisible(false);
		jarNameLabel.setVisible(false);
		jarNameLabel.setText("");
		selectedJarFile = null;
		installationPane.setVisible(false);
		versionNameTextField.setStyle("");
		versionJarButton.setStyle("");
	}
	
}