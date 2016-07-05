package com.lte.mcsm.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.mcsm.main.Program;
import com.lte.mcsm.model.Path;
import com.lte.mcsm.model.interfaces.IRefreshable;
import com.lte.mcsm.view.components.Desktop;
import com.lte.mcsm.view.components.WindowManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class VersionInstallWindow extends Scene implements IRefreshable {
	
	private static AnchorPane mainPane = new AnchorPane();
	private Pane installSetupPane;
	private ToolBar toolBar;
	private Button closeButton;
	private Label versionNameLabel;
	private Label versionJarLabel;
	private Label installVersionLabel;
	private TextField versionNameTextField;
	private Button versionJarButton;
	private Button installVersionButton;
	private ImageView installVersionImageView;
	private Separator headerSeperator;
	private Separator footerSeperator;
	
	public VersionInstallWindow() {
		super(mainPane, Desktop.getScreenSize().getWidth(), Desktop.getScreenSize().getHeight());
		try {
			mainPane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream(Path.BACKGROUND)), null, null, null, null)));
			this.installVersionImageView = new ImageView(new Image(new FileInputStream(Path.InstallVersionPNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
//				Stage fileChooserStage = new Stage();
//				fileChooserStage.centerOnScreen();
//				FileChooser fileChooser = new FileChooser();
//				fileChooser.setTitle("Minecraft Server JAR auswählen");
//				fileChooser.getExtensionFilters().addAll(
//				         new ExtensionFilter("JAR Archive", "*.jar")
//				);
//				File selectedFile = fileChooser.showOpenDialog(fileChooserStage);
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
				versionNameTextField.setDisable(true);
				versionJarButton.setDisable(true);
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
				headerSeperator,
				footerSeperator
		);
		this.closeButton = new Button("Abbrechen");
		this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				refresh();
				Program.getMainStage().setScene(WindowManager.getInstance().getServerVersionsWindow());
			}
		});
		this.toolBar = new ToolBar();
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		this.toolBar.getItems().add(closeButton);
		mainPane.getChildren().addAll(toolBar, installSetupPane);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
	}
	
	@Override
	public void refresh() {
		versionNameTextField.setText(null);
		versionNameTextField.setDisable(false);
		versionJarButton.setDisable(false);
	}
	
}
