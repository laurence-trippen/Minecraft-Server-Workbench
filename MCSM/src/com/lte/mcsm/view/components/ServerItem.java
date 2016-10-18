package com.lte.mcsm.view.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.mcsm.model.Server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class ServerItem extends AnchorPane {

	private static final String SERVER_PNG = "src/program-images/Server.png";
	private ImageView imageView;
	private Label label;
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
		this.imageView.setLayoutX(78);
		this.imageView.setLayoutY(20);
		this.label = new Label(server.getName());
		this.label.setFont(new Font("System", 19));
		this.label.setPrefWidth(252);
		this.label.setAlignment(Pos.CENTER);
		this.label.setLayoutY(126);
		this.splitMenuButton = new SplitMenuButton();
		this.splitMenuButton.setText(ServerMenu.OPTIONS);
		this.splitMenuButton.setPrefWidth(100);
		this.splitMenuButton.setLayoutX(76);
		this.splitMenuButton.setLayoutY(170);
		this.splitMenuButton.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				switch (splitMenuButton.getText()) {
				case ServerMenu.START:
					System.out.println("Starten");
					break;
				case ServerMenu.STOP:
					System.out.println("Stoppen");
					break;
				case ServerMenu.SHOW:
					System.out.println("Anzeigen");
					break;
					default:
						System.out.println("Default");
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
		this.getChildren().addAll(imageView, label, splitMenuButton);
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
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