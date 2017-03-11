package com.lte.msw.standalone.view.scenes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.lte.msw.standalone.main.MSWStandalone;
import com.lte.msw.standalone.manager.DesktopManager;
import com.lte.msw.standalone.manager.SceneManager;
import com.lte.msw.standalone.model.Server;
import com.lte.msw.standalone.model.ServerList;
import com.lte.msw.standalone.model.ServerVersion;
import com.lte.msw.standalone.model.abstracts.ResourcePath;
import com.lte.msw.standalone.model.enums.DataStatus;
import com.lte.msw.standalone.model.interfaces.IRefreshable;
import com.lte.msw.standalone.model.services.ServerCreatorService;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ServerCreatorScene extends MSWScene implements IRefreshable {

	private AnchorPane mainPane;
	private HBox hBox;
	private VBox vBox;
	private Pane vboxPane;
	private Pane createServerPane;
	private Pane installationPane;
	private Button closeButton;
	private Button createServerButton;
	private Label createServerLabel;
	private Label serverNameLabel;
	private Label serverVersionLabel;
	private Label serverEulaLabel;
	private Label progressLabel;
	private ImageView createServerImageView;
	private TextField serverNameTextField;
	private ChoiceBox<ServerVersion> serverVersionChoiceBox;
	private CheckBox serverEulaCheckBox;
	private Hyperlink serverEulaHyperlink;
	private ToolBar toolBar;
	private ProgressBar progressBar;
	
	public ServerCreatorScene() {
		super(new AnchorPane(), DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		this.initNodes();
		this.defineNodes();
		this.registerNodeEvents();
	}
	
	@Override
	protected void initNodes() {
		this.mainPane = (AnchorPane)this.getRoot();
		this.hBox = new HBox();
		this.vBox = new VBox();
		this.vboxPane = new Pane();
		this.progressLabel = new Label("Server wird erstellt ...");
		this.progressBar = new ProgressBar(0);
		this.installationPane = new Pane();
		this.closeButton = new Button("Abbrechen");
		this.toolBar = new ToolBar();
		this.serverNameLabel = new Label("Servername:");
		this.serverNameTextField = new TextField();
		this.serverVersionLabel = new Label("Serverversion:");
		this.serverVersionChoiceBox = new ChoiceBox<>();
		this.serverEulaLabel = new Label("Server EULA:");
		this.serverEulaCheckBox = new CheckBox("EULA akzeptieren");
		this.serverEulaHyperlink = new Hyperlink("Minecraft EULA");
		this.createServerLabel = new Label("Minecraft Server erstellen");
		this.createServerPane = new Pane();
		this.createServerButton = new Button("Server erstellen");
		try {
			mainPane.setBackground(new Background(
					new BackgroundImage(new Image(new FileInputStream(ResourcePath.BACKGROUND)), null, null, null, null)));
			this.createServerImageView = new ImageView(new Image(new FileInputStream(ResourcePath.CREATE_SERVER_PNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void defineNodes() {
		this.getStylesheets().clear();
		this.getStylesheets().add(getClass().getResource(ResourcePath.CSS).toExternalForm());
		
		this.progressLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.progressLabel.setLayoutX(400);
		this.progressLabel.setLayoutY(40);

		this.progressBar.setLayoutX(40);
		this.progressBar.setLayoutY(40);
		this.progressBar.setPrefWidth(340);
		this.progressBar.setPrefHeight(20);

		this.installationPane.getStyleClass().add("msw-white-pane");
		this.installationPane.setPrefWidth(600);
		this.installationPane.setPrefHeight(100);
		this.installationPane.setLayoutY(480);
		this.installationPane.setVisible(false);
		this.installationPane.getChildren().addAll(progressBar, progressLabel);

		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		this.toolBar.getItems().add(closeButton);

		this.serverNameLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverNameLabel.setLayoutX(90);
		this.serverNameLabel.setLayoutY(170);

		this.serverNameTextField.setPrefWidth(215);
		this.serverNameTextField.setLayoutX(260);
		this.serverNameTextField.setLayoutY(170);

		this.serverVersionLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverVersionLabel.setLayoutX(90);
		this.serverVersionLabel.setLayoutY(230);

		this.serverVersionChoiceBox.setItems(ServerList.getServerList().getServerVersions());
		this.serverVersionChoiceBox.getSelectionModel().selectFirst();
		this.serverVersionChoiceBox.setPrefWidth(80);
		this.serverVersionChoiceBox.setLayoutX(260);
		this.serverVersionChoiceBox.setLayoutY(230);

		this.serverEulaLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.serverEulaLabel.setLayoutX(90);
		this.serverEulaLabel.setLayoutY(290);

		this.serverEulaCheckBox.setLayoutX(260);
		this.serverEulaCheckBox.setLayoutY(295);

		this.serverEulaHyperlink.setLayoutX(388);
		this.serverEulaHyperlink.setLayoutY(293);
		
		this.createServerImageView.setLayoutX(90);
		this.createServerImageView.setLayoutY(50);

		this.createServerLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
		this.createServerLabel.setLayoutX(185);
		this.createServerLabel.setLayoutY(62);

		this.createServerButton.setPrefWidth(120);
		this.createServerButton.setPrefHeight(30);
		this.createServerButton.setLayoutX(355);
		this.createServerButton.setLayoutY(350);
		
		this.createServerPane.getStyleClass().add("msw-white-pane");
		this.createServerPane.setPrefWidth(600);
		this.createServerPane.setPrefHeight(450);
		this.createServerPane.getChildren().addAll(
			createServerLabel, 
			createServerButton,
			createServerImageView,
			serverNameLabel, 
			serverNameTextField, 
			serverVersionLabel, 
			serverVersionChoiceBox, 
			serverEulaLabel,
			serverEulaCheckBox, 
			serverEulaHyperlink
		);
		
		this.vboxPane.setPrefHeight(580);
		this.vboxPane.getChildren().addAll(createServerPane, installationPane);
		
		this.vBox.setAlignment(Pos.CENTER);
		this.vBox.setPrefWidth(600);
		this.vBox.getChildren().add(vboxPane);
		
		this.hBox.setAlignment(Pos.CENTER);
		this.hBox.getChildren().add(vBox);
		
		mainPane.getChildren().addAll(hBox, toolBar);
		AnchorPane.setLeftAnchor(toolBar, 0.0);
		AnchorPane.setTopAnchor(toolBar, 0.0);
		AnchorPane.setRightAnchor(toolBar, 0.0);
		AnchorPane.setTopAnchor(hBox, 0.0);
		AnchorPane.setLeftAnchor(hBox, 0.0);
		AnchorPane.setRightAnchor(hBox, 0.0);
		AnchorPane.setBottomAnchor(hBox, 0.0);
	}

	@Override
	protected void registerNodeEvents() {
		this.closeButton.setOnAction(this::onCloseEvent);
		this.serverEulaHyperlink.setOnAction(this::onHyperlinkEvent);
		this.createServerButton.setOnAction(this::onCreateServerEvent);
	}

	@Override
	public void refresh() {
		this.installationPane.setVisible(false);
		this.closeButton.setDisable(false);
		this.createServerButton.setDisable(false);
		this.serverNameTextField.setText("");
		this.serverNameTextField.getStyleClass().remove("msw-error-border");
		this.serverNameTextField.setDisable(false);
		this.serverVersionChoiceBox.setDisable(false);
		this.serverEulaCheckBox.getStyleClass().remove("msw-error-border");
		this.serverEulaCheckBox.setSelected(false);
		this.serverEulaCheckBox.setDisable(false);
		this.serverVersionChoiceBox.getSelectionModel().selectFirst();
		this.createServerButton.setDisable(false);
		this.progressBar.setProgress(0.00);
		this.progressLabel.setText("");
	}
	
	private void onCloseEvent(ActionEvent event) {
		refresh();
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getMainScene());
	}
	
	private void onHyperlinkEvent(ActionEvent event) {
		try {
			Desktop.getDesktop().browse(new URI(ResourcePath.MOJANG_EULA));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private void onCreateServerEvent(ActionEvent event) {
		if (serverNameTextField.getText().equals("")) {
			serverNameTextField.getStyleClass().add("msw-error-border");
		} else {
			serverNameTextField.getStyleClass().remove("msw-error-border");
		}
		if (!serverEulaCheckBox.isSelected()) {
			serverEulaCheckBox.getStyleClass().add("msw-error-border");
		} else {
			serverEulaCheckBox.getStyleClass().remove("msw-error-border");
		}
		if ((!serverNameTextField.getText().equals("")) && serverEulaCheckBox.isSelected()) {
			if (!ServerList.getServerList().existServer(serverNameTextField.getText())) {
				installationPane.setVisible(true);
				closeButton.setDisable(true);
				createServerButton.setDisable(true);
				serverNameTextField.setDisable(true);
				serverVersionChoiceBox.setDisable(true);
				serverEulaCheckBox.setDisable(true);
				progressLabel.setText("Server wird erstellt ...");
				ServerCreatorService scs = new ServerCreatorService(
						serverNameTextField.getText(), 
						serverVersionChoiceBox.getSelectionModel().getSelectedItem(), 
						progressBar
				);
				scs.start();
				scs.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						DataStatus status = ServerList.getServerList().addServer(
								new Server(serverNameTextField.getText(),
										new ServerVersion(
												serverVersionChoiceBox.getSelectionModel().getSelectedItem().getName(),
												ResourcePath.SERVER_DIRECTORY + serverNameTextField.getText() + "/" +
												new File(serverVersionChoiceBox.getSelectionModel().getSelectedItem().getPath()).getName()
										)
								)
						);
						switch (status) {
						case SUCCESS:
							Alert successAlert = new Alert(AlertType.INFORMATION);
							successAlert.setTitle("Server erstellen erfolgreich!");
							successAlert.setHeaderText(serverNameTextField.getText());
							successAlert.setContentText("Minecraft Server " + serverNameTextField.getText()
									+ " wurde erfolgreich erstellt!");
							successAlert.showAndWait();
							break;
						case ERROR:
							Alert errorAlert = new Alert(AlertType.ERROR);
							errorAlert.setTitle("Installation fehlgeschlagen!");
							errorAlert.setHeaderText(serverNameTextField.getText());
							errorAlert.setContentText(
									"Fehler beim erstellen von " + serverNameTextField.getText() + " !");
							errorAlert.showAndWait();
							break;
						default:
							break;
						}
						refresh();
						MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getMainScene());
					};
				});
				scs.setOnFailed(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {									
						Alert errorAlert = new Alert(AlertType.ERROR);
						errorAlert.setTitle("Installation fehlgeschlagen!");
						errorAlert.setHeaderText(serverNameTextField.getText());
						errorAlert.setContentText("Fehler beim erstellen von " + serverNameTextField.getText() + " !");
						errorAlert.showAndWait();
						refresh();
						MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getMainScene());
					}
				});
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Fehler!");
				alert.setContentText("Servername \"" + serverNameTextField.getText() + " \" existiert schon!");
				alert.showAndWait();
			}
		}
	}

}