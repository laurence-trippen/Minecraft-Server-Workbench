package com.lte.mcsm.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.mcsm.main.Program;
import com.lte.mcsm.manager.DesktopManager;
import com.lte.mcsm.manager.WindowManager;
import com.lte.mcsm.model.Path;
import com.lte.mcsm.model.Server;
import com.lte.mcsm.model.interfaces.IRefreshable;

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

public class ServerWindow extends Scene implements IRefreshable {

	private static AnchorPane mainPane = new AnchorPane();
	private Server server;
	private ToolBar toolBar;
	private Button closeButton;

	public ServerWindow() {
		super(mainPane, DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		try {
			mainPane.setBackground(new Background(
					new BackgroundImage(new Image(new FileInputStream(Path.BACKGROUND)), null, null, null, null)
			));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.closeButton = new Button("Zurück");
		this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Program.getMainStage().setScene(WindowManager.getWindowManager().getMainWindow());
				refresh();
			}
		});
		this.toolBar = new ToolBar();
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		this.toolBar.getItems().add(closeButton);
		mainPane.getChildren().addAll(toolBar);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
	}

	@Override
	public void refresh() {
		
	}

	public void setServer(Server server) {
		this.server = server;
	}

}