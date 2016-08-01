package com.lte.mcsm.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.mcsm.controller.DesktopManager;
import com.lte.mcsm.controller.WindowManager;
import com.lte.mcsm.main.Program;
import com.lte.mcsm.model.Path;
import com.lte.mcsm.model.ServerList;
import com.lte.mcsm.model.ServerVersion;
import com.lte.mcsm.model.interfaces.IRefreshable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
	
	private static AnchorPane mainPane = new AnchorPane();
	private ToolBar toolBar;
	private Button closeButton;
	private Pane createServerPane;
	private Label createServerLabel;
	private Label serverNameLabel;
	private Label serverVersionLabel;
	private ImageView createServerImageView;
	private TextField serverNameTextField;
	private ChoiceBox<ServerVersion> serverVersionChoiceBox;
	
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
					Path.CreateServerPNG
			)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.closeButton = new Button("Abbrechen");
		this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				refresh();
				Program.getMainStage().setScene(WindowManager.getWindowManager().getMainWindow());
			}
		});
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
		this.serverVersionLabel = new Label("Server Version:");
		this.serverVersionLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverVersionLabel.setLayoutX(90);
		this.serverVersionLabel.setLayoutY(230);
		this.serverVersionChoiceBox = new ChoiceBox<>();
		this.serverVersionChoiceBox.setItems(ServerList.getServerList().getServerVersions());
		this.serverVersionChoiceBox.getSelectionModel().selectFirst();
		this.serverVersionChoiceBox.setPrefWidth(80);
		this.serverVersionChoiceBox.setLayoutX(260);
		this.serverVersionChoiceBox.setLayoutY(230);
		this.createServerImageView.setLayoutX(90);
		this.createServerImageView.setLayoutY(50);
		this.createServerLabel = new Label("Minecraft Server erstellen");
		this.createServerLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
		this.createServerLabel.setLayoutX(185);
		this.createServerLabel.setLayoutY(62);
		this.createServerPane = new Pane();
		this.createServerPane.setStyle("-fx-background-color: white; -fx-background-radius: 5");
		this.createServerPane.setPrefWidth(600);
		this.createServerPane.setPrefHeight(450);
		this.createServerPane.setLayoutX(660);
		this.createServerPane.setLayoutY(300);
		this.createServerPane.getChildren().addAll(
				createServerLabel, 
				createServerImageView, 
				serverNameLabel,
				serverNameTextField,
				serverVersionLabel,
				serverVersionChoiceBox
		);
		mainPane.getChildren().addAll(toolBar, createServerPane);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
	}
	
	@Override
	public void refresh() {
		this.serverNameTextField.setText("");
	}
	
}