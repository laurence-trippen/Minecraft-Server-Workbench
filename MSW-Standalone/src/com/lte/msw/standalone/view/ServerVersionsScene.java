package com.lte.msw.standalone.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.lte.msw.standalone.controller.VersionsWindowController;
import com.lte.msw.standalone.manager.DesktopManager;
import com.lte.msw.standalone.model.ServerList;
import com.lte.msw.standalone.model.ServerVersion;
import com.lte.msw.standalone.model.abstracts.ResourcePath;
import com.lte.msw.standalone.model.interfaces.IFetchable;
import com.lte.msw.standalone.view.components.ServerVersionItem;

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

public class ServerVersionsScene extends Scene implements IFetchable {
	
	private static AnchorPane mainPane = new AnchorPane();
	private VersionsWindowController windowController;
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
		
	public ServerVersionsScene() {
		super(mainPane, DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		this.windowController = new VersionsWindowController(this);
		this.serverVersionItems = new ArrayList<ServerVersionItem>();
		try {
			this.installVersionImage = new Image(new FileInputStream(ResourcePath.SERVER_VERSIONS_PNG));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.gridPane = new GridPane();
		this.gridPane.getColumnConstraints().add(new ColumnConstraints(1905));
		this.gridPane.getRowConstraints().add(new RowConstraints(210));
		this.anchorPane = new AnchorPane();
		try {
			this.anchorPane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream(ResourcePath.BACKGROUND)), null, null, null, null)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.anchorPane.setPrefWidth(DesktopManager.getScreenSize().getWidth());
		this.anchorPane.setPrefHeight(2000);
		this.anchorPane.getChildren().add(gridPane);
		this.scrollPane = new ScrollPane();
		this.scrollPane.setLayoutX(0.00);
		this.scrollPane.setLayoutY(41.0);
		this.scrollPane.setContent(anchorPane);
		this.installVersionButton = new Button("Server Version installieren");
		this.installVersionButton.setGraphic(new ImageView(installVersionImage));
		this.installVersionButton.setOnAction(windowController::installVersionHandler);
		this.closeButton = new Button("Zurück");
		this.closeButton.setOnAction(windowController::closeHandler);
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
			gridPane.getChildren().clear();
			serverVersionItems.clear();
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