package com.lte.msw.standalone.view.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.lte.msw.standalone.main.MSWStandalone;
import com.lte.msw.standalone.manager.DesktopManager;
import com.lte.msw.standalone.manager.GridManager;
import com.lte.msw.standalone.manager.SceneManager;
import com.lte.msw.standalone.model.Server;
import com.lte.msw.standalone.model.ServerList;
import com.lte.msw.standalone.model.abstracts.ResourcePath;
import com.lte.msw.standalone.model.interfaces.IFetchable;
import com.lte.msw.standalone.model.interfaces.IRefreshable;
import com.lte.msw.standalone.view.components.ServerComponent;

import javafx.event.ActionEvent;
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
import javafx.scene.layout.RowConstraints;

public class MainScene extends MSWScene implements IRefreshable, IFetchable {

	private AnchorPane mainPane;
	private ToolBar toolBar;
	private Button addServerButton;
	private Button showServerVersions;
	private Image newServerImage;
	private Image showServerVersionsImage;
	private ScrollPane scrollPane;
	private AnchorPane anchorPane;
	private GridPane gridPane;
	private ArrayList<ServerComponent> serverItems;

	public MainScene() {
		super(new AnchorPane(), DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		this.initNodes();
		this.defineNodes();
		this.registerNodeEvents();
		this.fetch();
	}
	
	@Override
	protected void initNodes() {
		this.getStylesheets().clear();
		this.getStylesheets().add(getClass().getResource(ResourcePath.CSS).toExternalForm());
		this.mainPane = (AnchorPane)this.getRoot();
		this.serverItems = new ArrayList<ServerComponent>();
		this.anchorPane = new AnchorPane();
		this.gridPane = new GridPane();
		this.scrollPane = new ScrollPane();
		this.addServerButton = new Button("Server erstellen");
		this.showServerVersions = new Button("Server Versionen");
		this.toolBar = new ToolBar();
		try {
			this.newServerImage = new Image(new FileInputStream(ResourcePath.NEW_SERVER_PNG));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.showServerVersionsImage = new Image(new FileInputStream(ResourcePath.VERSIONS_24_PNG));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void defineNodes() {
		this.addServerButton.setGraphic(new ImageView(newServerImage));
		this.showServerVersions.setGraphic(new ImageView(showServerVersionsImage));
//		try {
//			this.anchorPane.setBackground(new Background(new BackgroundImage(new Image(
//					new FileInputStream(ResourcePath.BACKGROUND)), 
//					null, 
//					null, 
//					null, 
//					null
//			)));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		this.gridPane.getRowConstraints().add(new RowConstraints(312));
		this.gridPane.getColumnConstraints().addAll(
				new ColumnConstraints(635),
				new ColumnConstraints(635),
				new ColumnConstraints(635) 
		);
		
		this.anchorPane.setPrefWidth(DesktopManager.getScreenSize().getWidth());
		this.anchorPane.setPrefHeight(DesktopManager.getScreenSize().getHeight());
		this.anchorPane.getChildren().add(gridPane);
		
		try {
			this.scrollPane.setBackground(new Background(new BackgroundImage(new Image(
					new FileInputStream(ResourcePath.BACKGROUND)), 
					null, 
					null, 
					null, 
					null
					)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.scrollPane.setLayoutX(0.00);
		this.scrollPane.setLayoutY(41.0);
		this.scrollPane.setContent(anchorPane);
		
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.getItems().addAll(addServerButton, showServerVersions);
		
		mainPane.getChildren().addAll(toolBar, scrollPane);
		
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
		
		AnchorPane.setTopAnchor(scrollPane, 41.0);
		AnchorPane.setLeftAnchor(scrollPane, 0.00);
		AnchorPane.setRightAnchor(scrollPane, 0.00);
		AnchorPane.setBottomAnchor(scrollPane, 0.00);
		
		AnchorPane.setTopAnchor(gridPane, 0.00);
		AnchorPane.setLeftAnchor(gridPane, 0.00);
		AnchorPane.setRightAnchor(gridPane, 0.00);
	}

	@Override
	protected void registerNodeEvents() {
		this.addServerButton.setOnAction(this::addServerHandler);
		this.showServerVersions.setOnAction(this::showVersionsHandler);
	}
	
	@Override
	public void refresh() {
		for (ServerComponent sItem : serverItems) {
			sItem.refresh();
		}
	}
	
	@Override
	public void fetch() {
		int x = 0;
		int y = 0;
		int counter = 3;
		ServerList serverList = ServerList.getServerList();
		if (serverList.getServerCount() != 0) {	
			gridPane.getChildren().clear();
			serverItems.clear();
			for (Server server : serverList.getServer()) {
				server.init();
				if (serverList.getServerCount() > counter) {
					counter += 3;
					this.gridPane.getRowConstraints().add(new RowConstraints(312));
				}
				ServerComponent serverItem = new ServerComponent(server);
				serverItems.add(serverItem);
				GridPane.setConstraints(serverItem, x, y);
				x = GridManager.getX(x);
				y = GridManager.getY(y, x);
				gridPane.getChildren().add(serverItem);
			}
			x = 0;
			y = 0;
		}
	}
	
	private void addServerHandler(ActionEvent event) {
		this.refresh();
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getCreateServerScene());
	}
	
	private void showVersionsHandler(ActionEvent event) {
		this.refresh();
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getServerVersionsScene());
	}

}