package com.lte.msw.standalone.view.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

import com.lte.msw.standalone.model.MSWStorage;
import com.lte.msw.standalone.model.ServerVersion;
import com.lte.msw.standalone.model.abstracts.ResourcePath;
import com.lte.msw.standalone.model.enums.DataStatus;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ServerVersionComponent extends MSWComponent {
	
	private ServerVersion serverVersion;
	private Pane serverVersionPane;
	private ImageView imageView;
	private Label versionTextlabel;
	private Button deleteVersionButton;
	
	public ServerVersionComponent(ServerVersion serverVersion) {
		this.serverVersion = serverVersion;
		this.initNodes();
		this.defineNodes();
		this.registerNodeEvents();
	}
	
	@Override
	protected void initNodes() {
		this.deleteVersionButton = new Button("Deinstallieren");
		this.versionTextlabel = new Label("Minecraft Server Version - " + serverVersion.getName());
		this.serverVersionPane = new Pane();
	}
	
	@Override
	protected void defineNodes() {
		try {
			this.imageView = new ImageView(new Image(new FileInputStream(ResourcePath.SERVER_VERSION_PNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		this.deleteVersionButton.setLayoutX(127);
		this.deleteVersionButton.setLayoutY(65);

		this.versionTextlabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.versionTextlabel.setLayoutX(127);
		this.versionTextlabel.setLayoutY(28);
		this.imageView.setLayoutX(33);
		this.imageView.setLayoutY(28);

		this.serverVersionPane.getStyleClass().add("msw-white-pane");
		this.serverVersionPane.setLayoutY(68);
		this.serverVersionPane.setPrefWidth(700);
		this.serverVersionPane.setPrefHeight(120);
		this.serverVersionPane.getChildren().addAll(imageView, versionTextlabel, deleteVersionButton);
		this.getChildren().add(serverVersionPane);
	}
	
	@Override
	protected void registerNodeEvents() {
		this.deleteVersionButton.setOnAction(this::onDeleteVersionEvent);
	}
	
	private void onDeleteVersionEvent(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Minecraft Server Version deinstallieren");
		alert.setHeaderText("Wollen Sie die Minecraft Server Version " + serverVersion.getName() + " wirklich deinstallieren?");
		ButtonType buttonTypeYes = new ButtonType("Ja");
		ButtonType buttonTypeNo = new ButtonType("Nein");
		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeYes) {
			DataStatus status = MSWStorage.getInstance().deleteServerVersion(serverVersion);
			switch (status) {
			case SUCCESS:
				Alert successAlert = new Alert(AlertType.INFORMATION);
				successAlert.setTitle("Deinstallation erfolgreich!");
				successAlert.setHeaderText("Minecraft Server Version " + serverVersion.getName() + " wurde erfolgreich deinstalliert!");
				successAlert.showAndWait();
				break;
			case ERROR:
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setTitle("Deinstallation fehgeschlagen!");
				errorAlert.setHeaderText("Minecraft Server Version " + serverVersion.getName() + " konnte nicht deinstalliert werden!");
				errorAlert.showAndWait();
				break;
			default:
				break;
			}
		}
	}

}