package com.lte.mcsm.view.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.mcsm.model.Server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ServerItem extends AnchorPane {

	private static final String SERVER_PNG = "src/program-images/Server.png";
	private AnchorPane serverPane;
	private MenuBar serverBar;
	private Menu serverMenu;
	private MenuItem miServerItem;
	private ImageView imageView;
	private Label lbServerName;
	private Label lbServerVersion;
	private Label lbServerPlayer;
	private SplitMenuButton splitMenuButton;
	private MenuItem miStart;
	private MenuItem miStop;
	private MenuItem miShow;
	private Server server;

	public ServerItem(Server server) {
		this.server = server;
		try {
			this.imageView = new ImageView(new Image(new FileInputStream(SERVER_PNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.splitMenuButton = new SplitMenuButton();
		this.splitMenuButton.setText(ServerMenu.OPTIONS);
		this.splitMenuButton.setPrefWidth(113);
		this.splitMenuButton.setPrefHeight(25);
		this.splitMenuButton.setLayoutX(335);
		this.splitMenuButton.setLayoutY(92);
		this.splitMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switch (splitMenuButton.getText()) {
				case ServerMenu.START:
					System.out.println("[" + server.getName() + "] Starten");
					break;
				case ServerMenu.STOP:
					System.out.println("[" + server.getName() + "] Stoppen");
					break;
				case ServerMenu.SHOW:
					System.out.println("[" + server.getName() + "] Anzeigen");
					break;
				default:
					System.out.println("[" + server.getName() + "] Default");
				}
			}
		});
		this.miStart = new MenuItem("Starten");
		this.miStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				splitMenuButton.setText(ServerMenu.START);
			}
		});
		this.miStop = new MenuItem("Stoppen");
		this.miStop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				splitMenuButton.setText(ServerMenu.STOP);
			}
		});
		this.miShow = new MenuItem("Anzeigen");
		this.miShow.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				splitMenuButton.setText(ServerMenu.SHOW);
			}
		});
		this.splitMenuButton.getItems().addAll(miStart, miStop, miShow);
		this.lbServerName = new Label();
		this.lbServerName.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.lbServerName.setLayoutX(106);
		this.lbServerName.setLayoutY(41);
		this.lbServerName.setText(server.getName());
		this.lbServerVersion = new Label("1.9.4");
		this.lbServerVersion.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.lbServerVersion.setLayoutX(234);
		this.lbServerVersion.setLayoutY(41);
		this.lbServerPlayer = new Label("0 / 20");
		this.lbServerPlayer.setFont(Font.font("System", 12));
		this.lbServerPlayer.setLayoutX(106);
		this.lbServerPlayer.setLayoutY(65);
		this.imageView.setLayoutX(26);
		this.imageView.setLayoutY(41);
		this.miServerItem = new MenuItem("Test");
		this.serverMenu = new Menu("Server");
		this.serverMenu.getItems().add(miServerItem);
		this.serverBar = new MenuBar();
		this.serverBar.setLayoutX(0.00);
		this.serverBar.setLayoutY(0.00);
		this.serverBar.setPrefWidth(468.0);
		this.serverBar.setPrefHeight(25.00);
		this.serverBar.getMenus().add(serverMenu);
		this.serverPane = new AnchorPane();
		this.serverPane.setStyle("-fx-background-color: white; -fx-background-radius: 5");
		this.serverPane.setLayoutX(89);
		this.serverPane.setLayoutY(68);
		this.serverPane.setPrefWidth(468.0);
		this.serverPane.setPrefHeight(130.0);
		this.serverPane.getChildren().addAll(
				serverBar, 
				imageView, 
				lbServerName, 
				lbServerVersion, 
				lbServerPlayer, 
				splitMenuButton
		);
		this.getChildren().add(serverPane);
	}

	public AnchorPane getServerPane() {
		return serverPane;
	}

	public void setServerPane(AnchorPane serverPane) {
		this.serverPane = serverPane;
	}

	public MenuBar getServerBar() {
		return serverBar;
	}

	public void setServerBar(MenuBar serverBar) {
		this.serverBar = serverBar;
	}

	public Menu getServerMenu() {
		return serverMenu;
	}

	public void setServerMenu(Menu serverMenu) {
		this.serverMenu = serverMenu;
	}

	public MenuItem getMiServerItem() {
		return miServerItem;
	}

	public void setMiServerItem(MenuItem miServerItem) {
		this.miServerItem = miServerItem;
	}

	public Label getLbServerName() {
		return lbServerName;
	}

	public void setLbServerName(Label lbServerName) {
		this.lbServerName = lbServerName;
	}

	public Label getLbServerVersion() {
		return lbServerVersion;
	}

	public void setLbServerVersion(Label lbServerVersion) {
		this.lbServerVersion = lbServerVersion;
	}

	public Label getLbServerPlayer() {
		return lbServerPlayer;
	}

	public void setLbServerPlayer(Label lbServerPlayer) {
		this.lbServerPlayer = lbServerPlayer;
	}

	public MenuItem getMiStart() {
		return miStart;
	}

	public void setMiStart(MenuItem miStart) {
		this.miStart = miStart;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public SplitMenuButton getSplitMenuButton() {
		return splitMenuButton;
	}

	public void setSplitMenuButton(SplitMenuButton splitMenuButton) {
		this.splitMenuButton = splitMenuButton;
	}

	public MenuItem getMiStop() {
		return miStop;
	}

	public void setMiStop(MenuItem miStop) {
		this.miStop = miStop;
	}

	public MenuItem getMiShow() {
		return miShow;
	}

	public void setMiShow(MenuItem miShow) {
		this.miShow = miShow;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

}