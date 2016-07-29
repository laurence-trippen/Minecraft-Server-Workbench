package com.lte.mcsm.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.lte.mcsm.controller.DesktopManager;
import com.lte.mcsm.controller.WindowManager;
import com.lte.mcsm.main.Program;
import com.lte.mcsm.model.Path;
import com.lte.mcsm.model.ServerList;
import com.lte.mcsm.model.ServerVersion;
import com.lte.mcsm.model.interfaces.IFetchable;
import com.lte.mcsm.view.components.ServerVersionItem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class ServerVersionsWindow extends Scene implements IFetchable {
	
	private static AnchorPane mainPane = new AnchorPane();
	private ToolBar toolBar;
	private Pane leftSpacerPane;
	private Pane rightSpacerPane;
	private Button installVersionButton;
	private Button closeButton;
	private ScrollPane scrollPane;
	private AnchorPane anchorPane;
	private GridPane gridPane;
	private Image installVersionImage;
	private ArrayList<ServerVersionItem> serverVersionItems;
		
	public ServerVersionsWindow() {
		super(mainPane, DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		this.serverVersionItems = new ArrayList<ServerVersionItem>();
		try {
			this.installVersionImage = new Image(new FileInputStream(Path.ServerVersionsPNG));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.gridPane = new GridPane();
		this.gridPane.getColumnConstraints().add(new ColumnConstraints(1905));
		this.gridPane.getRowConstraints().add(new RowConstraints(210));
		this.anchorPane = new AnchorPane();
		try {
			this.anchorPane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream(Path.BACKGROUND)), null, null, null, null)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.anchorPane.setPrefWidth(1905);
		this.anchorPane.setPrefHeight(2000);
		this.anchorPane.getChildren().add(gridPane);
		this.scrollPane = new ScrollPane();
		this.scrollPane.setLayoutX(0.00);
		this.scrollPane.setLayoutY(41.0);
		this.scrollPane.setContent(anchorPane);
		this.installVersionButton = new Button("Server Version installieren");
		this.installVersionButton.setGraphic(new ImageView(installVersionImage));
		this.installVersionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Program.getMainStage().setScene(WindowManager.getWindowManager().getVersionInstallWindow());
			}
		});
		this.closeButton = new Button("Zurück");
		this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Program.getMainStage().setScene(WindowManager.getWindowManager().getMainWindow());
			}
		});
		this.leftSpacerPane = new Pane();
		HBox.setHgrow(leftSpacerPane, Priority.SOMETIMES);
		this.rightSpacerPane = new Pane();
		HBox.setHgrow(rightSpacerPane, Priority.SOMETIMES);
		this.toolBar = new ToolBar();
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.getItems().addAll(installVersionButton, leftSpacerPane, rightSpacerPane, closeButton);
		mainPane.getChildren().addAll(toolBar, scrollPane);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
		AnchorPane.setLeftAnchor(scrollPane, 0.00);
		AnchorPane.setTopAnchor(scrollPane, 41.0);
		AnchorPane.setRightAnchor(scrollPane, 0.00);
		AnchorPane.setBottomAnchor(scrollPane, 0.00);
		AnchorPane.setLeftAnchor(gridPane, 0.00);
		AnchorPane.setTopAnchor(gridPane, 0.00);
		AnchorPane.setRightAnchor(gridPane, 0.00);
		fetch();
	}

	@Override
	public void fetch() {
		int counter = 0;
		ServerList serverList = ServerList.getServerList();
		if (serverList.getServerVersionCounter() != 0) {
			for (ServerVersion serverVersion : serverList.getServerVersions()) {
				if (counter > 0) {
					this.gridPane.getRowConstraints().add(new RowConstraints(210));
				}
				ServerVersionItem serverVersionItem = new ServerVersionItem(serverVersion);
				serverVersionItems.add(serverVersionItem);
				GridPane.setConstraints(serverVersionItem, 0, counter);
				this.gridPane.getChildren().add(serverVersionItem);
				counter++;
			}
		}
	}

}
