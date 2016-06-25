package com.lte.mcsm.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.mcsm.model.Path;
import com.lte.mcsm.view.components.Desktop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;


public class CreateServerWindow extends Scene {
	
	private static AnchorPane mainPane = new AnchorPane();
	private ToolBar toolBar;
	private Button closeButton;
	
	public CreateServerWindow() {
		super(mainPane, Desktop.getScreenSize().getWidth(), Desktop.getScreenSize().getHeight());
		try {
			mainPane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream(Path.BACKGROUND)), null, null, null, null)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.closeButton = new Button("Abbrechen");
		this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("[Event] CreateServerWindow.closeButton");
			}
		});
		this.toolBar = new ToolBar();
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		this.toolBar.getItems().add(closeButton);
		mainPane.getChildren().add(toolBar);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
	}
	
}
