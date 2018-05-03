package com.laurencetrippen.msw.standalone.view.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.laurencetrippen.msw.standalone.main.MSWStandalone;
import com.laurencetrippen.msw.standalone.manager.DesktopManager;
import com.laurencetrippen.msw.standalone.manager.SceneManager;
import com.laurencetrippen.msw.standalone.model.MSWStorage;
import com.laurencetrippen.msw.standalone.model.Server;
import com.laurencetrippen.msw.standalone.model.abstracts.ResourcePath;
import com.laurencetrippen.msw.standalone.model.interfaces.IFetchable;
import com.laurencetrippen.msw.standalone.model.interfaces.IRefreshable;
import com.laurencetrippen.msw.standalone.view.components.ServerComponent;

import javafx.event.ActionEvent;
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
import javafx.scene.layout.FlowPane;

public class MainScene extends MSWScene implements IRefreshable, IFetchable {

	private AnchorPane mainPane;
	private ToolBar toolBar;
	private Button addServerButton;
	private Button showServerVersions;
	private Image newServerImage;
	private Image showServerVersionsImage;
	private ScrollPane scrollPane;
	private AnchorPane anchorPane;
	private FlowPane flowPane;
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
		this.mainPane = (AnchorPane)this.getRoot();
		this.serverItems = new ArrayList<ServerComponent>();
		this.anchorPane = new AnchorPane();
		this.scrollPane = new ScrollPane();
		this.flowPane = new FlowPane();
		this.addServerButton = new Button("Server erstellen");
		this.showServerVersions = new Button("Minecraft Versionen");
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
		this.getStylesheets().clear();
		this.getStylesheets().add(getClass().getResource(ResourcePath.CSS).toExternalForm());
		this.addServerButton.setGraphic(new ImageView(newServerImage));
		this.showServerVersions.setGraphic(new ImageView(showServerVersionsImage));
		
		this.anchorPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(15));
		this.anchorPane.getChildren().add(flowPane);
		
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
		
		AnchorPane.setTopAnchor(flowPane, 0.00);
		AnchorPane.setLeftAnchor(flowPane, 0.00);
		AnchorPane.setRightAnchor(flowPane, 0.00);
	}

	@Override
	protected void registerNodeEvents() {
		this.addServerButton.setOnAction(this::onAddServerEvent);
		this.showServerVersions.setOnAction(this::onShowVersionsEvent);
	}
	
	@Override
	public void refresh() {
		for (ServerComponent sItem : serverItems) {
			sItem.refresh();
		}
	}
	
	@Override
	public void fetch() {
		MSWStorage serverList = MSWStorage.getInstance();
		flowPane.getChildren().clear();
		serverItems.clear();
		for (Server server : serverList.getServer()) {
			server.init();
			ServerComponent serverComponent = new ServerComponent(server);
			serverItems.add(serverComponent);
			flowPane.getChildren().add(serverComponent);
		}
	}
	
	private void onAddServerEvent(ActionEvent event) {
		this.refresh();
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getServerCreatorScene());
	}
	
	private void onShowVersionsEvent(ActionEvent event) {
		this.refresh();
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getServerVersionsScene());
	}

}