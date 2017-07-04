package com.lte.msw.standalone.view.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.lte.msw.standalone.main.MSWStandalone;
import com.lte.msw.standalone.manager.DesktopManager;
import com.lte.msw.standalone.manager.SceneManager;
import com.lte.msw.standalone.model.MSWStorage;
import com.lte.msw.standalone.model.ServerVersion;
import com.lte.msw.standalone.model.abstracts.ResourcePath;
import com.lte.msw.standalone.model.interfaces.IFetchable;
import com.lte.msw.standalone.view.components.ServerVersionComponent;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class ServerVersionsScene extends MSWScene implements IFetchable {
	
	private AnchorPane mainPane;
	private HBox hBox;
	private ToolBar toolBar;
	private Pane leftSpacerPane;
	private Pane rightSpacerPane;
	private Button installVersionButton;
	private Button closeButton;
	private ScrollPane scrollPane;
	private AnchorPane anchorPane;
	private GridPane gridPane;
	private Image installVersionImage;
	private ArrayList<ServerVersionComponent> serverVersionItems;
	
	public ServerVersionsScene() {
		super(new AnchorPane(), DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		this.initNodes();
		this.defineNodes();
		this.registerNodeEvents();
		this.fetch();
	}
	
	@Override
	protected void initNodes() {
		this.mainPane = (AnchorPane)this.getRoot();
		this.hBox = new HBox();
		this.serverVersionItems = new ArrayList<ServerVersionComponent>();
		this.gridPane = new GridPane();
		this.anchorPane = new AnchorPane();
		this.scrollPane = new ScrollPane();
		this.installVersionButton = new Button("Version installieren");
		this.closeButton = new Button("Zurück");
		this.leftSpacerPane = new Pane();
		this.rightSpacerPane = new Pane();
		this.toolBar = new ToolBar();
	}
	
	@Override
	protected void defineNodes() {
		this.getStylesheets().clear();
		this.getStylesheets().add(getClass().getResource(ResourcePath.CSS).toExternalForm());
		
		try {
			this.installVersionImage = new Image(new FileInputStream(ResourcePath.SERVER_VERSIONS_PNG));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.installVersionButton.setGraphic(new ImageView(installVersionImage));
		
		this.toolBar.getItems().addAll(
			installVersionButton,
			leftSpacerPane, 
			rightSpacerPane, 
			closeButton
		);
		
		this.gridPane.getColumnConstraints().add(new ColumnConstraints(700));
		this.gridPane.getRowConstraints().add(new RowConstraints(210));
		
		this.hBox.setAlignment(Pos.CENTER);
		this.hBox.getChildren().add(gridPane);
		
		this.anchorPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(5));
		this.anchorPane.getChildren().add(hBox);
		
		this.scrollPane.setLayoutX(0.00);
		this.scrollPane.setLayoutY(41.0);
		this.scrollPane.setContent(anchorPane);
		try {
			this.scrollPane.setBackground(new Background(new BackgroundImage(
					new Image(new FileInputStream(ResourcePath.BACKGROUND)), 
					BackgroundRepeat.REPEAT, 
					BackgroundRepeat.REPEAT, 
					BackgroundPosition.DEFAULT, 
					BackgroundSize.DEFAULT
					)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		mainPane.getChildren().addAll(toolBar, scrollPane);
		
		HBox.setHgrow(leftSpacerPane, Priority.SOMETIMES);
		HBox.setHgrow(rightSpacerPane, Priority.SOMETIMES);
		
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
		AnchorPane.setTopAnchor(hBox, 0.0);
		AnchorPane.setLeftAnchor(hBox, 0.0);
		AnchorPane.setRightAnchor(hBox, 0.0);
		AnchorPane.setBottomAnchor(hBox, 0.0);
	}
	
	@Override
	protected void registerNodeEvents() {
		this.closeButton.setOnAction(this::closeHandler);
		this.installVersionButton.setOnAction(this::installVersionHandler);
	}

	@Override
	public void fetch() {
		int counter = 0;
		MSWStorage serverList = MSWStorage.getInstance();
		if (serverList.getServerVersionCounter() != 0) {
			gridPane.getChildren().clear();
			serverVersionItems.clear();
			for (ServerVersion serverVersion : serverList.getServerVersions()) {
				if (counter > 0) {
					this.gridPane.getRowConstraints().add(new RowConstraints(210));
				}
				ServerVersionComponent serverVersionItem = new ServerVersionComponent(serverVersion);
				serverVersionItems.add(serverVersionItem);
				GridPane.setConstraints(serverVersionItem, 0, counter);
				this.gridPane.getChildren().add(serverVersionItem);
				counter++;
			}
		}
	}
	
	private void installVersionHandler(ActionEvent event) {
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getVersionInstallScene());
	}
	
	private void closeHandler(ActionEvent event) {
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getMainScene());
	}

}