package com.lte.mcsm.view.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.mcsm.model.Path;
import com.lte.mcsm.model.ServerVersion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ServerVersionItem extends AnchorPane {
	
	private AnchorPane serverVersionPane;
	private ImageView imageView;
	private Label versionTextlabel;
	private Button deleteVersionButton;
	
	public ServerVersionItem(ServerVersion serverVersion) {
		try {
			this.imageView = new ImageView(new Image(new FileInputStream(Path.ServerVersionPNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.deleteVersionButton = new Button("Deinstallieren");
		this.deleteVersionButton.setLayoutX(127);
		this.deleteVersionButton.setLayoutY(65);
		this.deleteVersionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("[" + serverVersion.getName() + "] deinstalled");
			}
		});
		this.versionTextlabel = new Label("Minecraft Server Version - " + serverVersion.getName());
		this.versionTextlabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.versionTextlabel.setLayoutX(127);
		this.versionTextlabel.setLayoutY(28);
		this.imageView.setLayoutX(33);
		this.imageView.setLayoutY(28);
		this.serverVersionPane = new AnchorPane();
		this.serverVersionPane.setStyle("-fx-background-color: white; -fx-background-radius: 5");
		this.serverVersionPane.setLayoutX(552.5);
		this.serverVersionPane.setLayoutY(68);
		this.serverVersionPane.setPrefWidth(700);
		this.serverVersionPane.setPrefHeight(120);
		this.serverVersionPane.getChildren().addAll(imageView, versionTextlabel, deleteVersionButton);
		this.getChildren().add(serverVersionPane);
	}

}
