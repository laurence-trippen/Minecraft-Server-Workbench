package com.lte.mcsm.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.lte.mcsm.controller.DesktopManager;
import com.lte.mcsm.controller.GridManager;
import com.lte.mcsm.controller.WindowManager;
import com.lte.mcsm.main.Program;
import com.lte.mcsm.model.Path;
import com.lte.mcsm.model.Server;
import com.lte.mcsm.model.ServerList;
import com.lte.mcsm.model.interfaces.IFetchable;
import com.lte.mcsm.model.interfaces.IRefreshable;
import com.lte.mcsm.view.components.ServerItem;

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
import javafx.scene.layout.RowConstraints;

public class MainWindow extends Scene implements IRefreshable, IFetchable {

	private static int x = 0;
	private static int y = 0;
	private static AnchorPane mainPane = new AnchorPane();
	private ToolBar toolBar;
	private Button addServerButton;
	private Button showServerVersions;
	private Image newServerImage;
	private Image showServerVersionsImage;
	private ScrollPane scrollPane;
	private AnchorPane anchorPane;
	private GridPane gridPane;
	private ArrayList<ServerItem> serverItems;

	public MainWindow() {
		super(mainPane, DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		this.serverItems = new ArrayList<ServerItem>();
		this.anchorPane = new AnchorPane();
		this.anchorPane.setPrefWidth(1905);
		this.anchorPane.setPrefHeight(2000);
		try {
			this.newServerImage = new Image(new FileInputStream(Path.NewServerPNG));
			this.showServerVersionsImage = new Image(new FileInputStream(Path.ServerVersionsPNG));
			this.anchorPane.setBackground(new Background(new BackgroundImage(new Image(
					new FileInputStream(Path.BACKGROUND)), 
					null, 
					null, 
					null, 
					null
			)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.gridPane = new GridPane();
		this.gridPane.getColumnConstraints().addAll(
				new ColumnConstraints(635), 
				new ColumnConstraints(635),
				new ColumnConstraints(635) 
		);
		this.gridPane.getRowConstraints().add(new RowConstraints(312));
		this.anchorPane.getChildren().add(gridPane);
		this.scrollPane = new ScrollPane();
		this.scrollPane.setLayoutX(0.00);
		this.scrollPane.setLayoutY(41.0);
		this.scrollPane.setContent(anchorPane);
		this.addServerButton = new Button("Server erstellen");
		this.addServerButton.setGraphic(new ImageView(newServerImage));
		this.addServerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				refresh();
				Program.getMainStage().setScene(WindowManager.getWindowManager().getCreateServerWindow());
			}
		});
		this.showServerVersions = new Button("Server Versionen");
		this.showServerVersions.setGraphic(new ImageView(showServerVersionsImage));
		this.showServerVersions.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				refresh();
				Program.getMainStage().setScene(WindowManager.getWindowManager().getServerVersionsWindow());
			}
		});
		this.toolBar = new ToolBar();
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.getItems().addAll(addServerButton, showServerVersions);
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
	public void refresh() {
		for (ServerItem sItem : serverItems) {
			sItem.refresh();
		}
	}
	
	@Override
	public void fetch() {
		System.out.println("fetch");
		int counter = 3;
		ServerList serverList = ServerList.getServerList();
		if (serverList.getServerCount() != 0) {
			if (serverItems.size() == 0) {				
				for (Server server : serverList.getServer()) {
					if (serverList.getServerCount() > counter) {
						counter += 3;
						this.gridPane.getRowConstraints().add(new RowConstraints(312));
					}
					ServerItem serverItem = new ServerItem(server);
					serverItems.add(serverItem);
					GridPane.setConstraints(serverItem, x, y);
					x = GridManager.getX(x);
					y = GridManager.getY(y, x);
					gridPane.getChildren().add(serverItem);
				}
			}
			if (serverItems.size() > 0) {
				for (Server server : serverList.getServer()) {
					if (serverList.getServerCount() > counter) {
						counter += 3;
						this.gridPane.getRowConstraints().add(new RowConstraints(312));
					}
					boolean inList = false;
					for (ServerItem item : serverItems) {
						if (item.getServer().getId() == server.getId()) {
							inList = true;
						}
					}
					if (!inList) {
						ServerItem serverItem = new ServerItem(server);
						serverItems.add(serverItem);
						GridPane.setConstraints(serverItem, x, y);
						x = GridManager.getX(x);
						y = GridManager.getY(y, x);
						gridPane.getChildren().add(serverItem);
						inList = false;
					}
				}
			}
		}
	}

}