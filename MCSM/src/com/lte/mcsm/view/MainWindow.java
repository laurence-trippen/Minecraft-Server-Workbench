package com.lte.mcsm.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.mcsm.model.Server;
import com.lte.mcsm.model.ServerList;
import com.lte.mcsm.view.components.ServerItem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MainWindow extends AnchorPane {

	private static final String NEW_SERVER_PNG = "src/program-images/newServer.png";
	private static int x = 0;
	private static int y = 0;
	private ToolBar toolBar;
	private Button addServerButton;
	private Image newServerImage;
	private ScrollPane scrollPane;
	private AnchorPane anchorPane;
	private GridPane gridPane;

	public MainWindow() {
		try {
			this.newServerImage = new Image(new FileInputStream(NEW_SERVER_PNG));
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
		this.anchorPane = new AnchorPane();
		this.anchorPane.setStyle("-fx-background-color: gray");
		this.anchorPane.setPrefWidth(1905);
		this.anchorPane.setPrefHeight(2000);
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
				
			}
		});
		this.toolBar = new ToolBar();
		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.getItems().add(addServerButton);
		this.getChildren().addAll(toolBar, scrollPane);
		setLeftAnchor(toolBar, 0.00);
		setTopAnchor(toolBar, 0.00);
		setRightAnchor(toolBar, 0.00);
		setLeftAnchor(scrollPane, 0.00);
		setTopAnchor(scrollPane, 41.0);
		setRightAnchor(scrollPane, 0.00);
		setBottomAnchor(scrollPane, 0.00);
		setLeftAnchor(gridPane, 0.00);
		setTopAnchor(gridPane, 0.00);
		setRightAnchor(gridPane, 0.00);
		loadServer();
	}
	
	private void loadServer() {
		int counter = 3;
		ServerList serverList = ServerList.getInstance();
		if (serverList.getServerCount() != 0) {
			for (Server server : serverList.getServer()) {
				if (serverList.getServerCount() > counter) {
					counter += 3;
					this.gridPane.getRowConstraints().add(new RowConstraints(312));
				}
				ServerItem serverItem = new ServerItem(server);
				GridPane.setConstraints(serverItem, x, y);
				x = calcX(x);
				y = calcY(y, x);
				gridPane.getChildren().add(serverItem);
			}
		}
	}
	
	private static int calcX(int x) {
		if (x < 4) {
			++x;
		}
		if (x == 3) {
			x = 0;
		}
		return x;
	}

	private static int calcY(int y, int x) {
		if (x == 0) {
			++y;
		}
		return y;
	}

}
