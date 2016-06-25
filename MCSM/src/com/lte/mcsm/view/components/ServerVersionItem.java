package com.lte.mcsm.view.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lte.mcsm.model.Path;
import com.lte.mcsm.model.ServerVersion;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ServerVersionItem extends AnchorPane {
	
	private AnchorPane serverVersionPane;
	private ImageView imageView;
	private Label versionTextlabel;
	private Label serverVersionLabel;
	private Button deleteVersionButton;
	private ServerVersion serverVersion;
	
	public ServerVersionItem(ServerVersion serverVersion) {
		this.serverVersion = serverVersion;
		try {
			this.imageView = new ImageView(new Image(new FileInputStream(Path.ServerVersionPNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
