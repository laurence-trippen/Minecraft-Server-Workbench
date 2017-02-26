package com.lte.msw.standalone.view.scenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.lte.msw.standalone.main.MSWStandalone;
import com.lte.msw.standalone.manager.DesktopManager;
import com.lte.msw.standalone.manager.SceneManager;
import com.lte.msw.standalone.model.ServerList;
import com.lte.msw.standalone.model.ServerVersion;
import com.lte.msw.standalone.model.abstracts.ResourcePath;
import com.lte.msw.standalone.model.enums.DataStatus;
import com.lte.msw.standalone.model.interfaces.IRefreshable;
import com.lte.msw.standalone.model.threads.services.VersionInstallerService;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class VersionInstallerScene extends MSWScene implements IRefreshable {

	private AnchorPane mainPane;
	private HBox hBox;
	private VBox vBox;
	private Pane vboxPane;
	private Pane installSetupPane;
	private Pane installationPane;
	private File selectedJarFile;
	private ToolBar toolBar;
	private Button closeButton;
	private Label versionNameLabel;
	private Label versionJarLabel;
	private Label installVersionLabel;
	private Label jarNameLabel;
	private Label progressLabel;
	private TextField versionNameTextField;
	private Button versionJarButton;
	private Button installVersionButton;
	private ImageView installVersionImageView;
	private ImageView jarVersionImageView;
	private ProgressBar progressBar;

	public VersionInstallerScene() {
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
		this.progressLabel = new Label("Wird vorbereitet ...");
		this.progressBar = new ProgressBar(0);
		this.installationPane = new Pane();
		this.jarNameLabel = new Label();
		this.versionNameLabel = new Label("Server Versions Name:");
		this.versionNameTextField = new TextField();
		this.versionJarLabel = new Label("Server Version: (*.jar)");
		this.versionJarButton = new Button("Minecraft-Server JAR auswählen ...");
		this.installVersionLabel = new Label("Server Version installieren");
		this.installVersionButton = new Button("Installieren");
		this.installSetupPane = new Pane();
		this.closeButton = new Button("Abbrechen");
		this.toolBar = new ToolBar();
	}

	@Override
	protected void defineNodes() {
		this.getStylesheets().clear();
		this.getStylesheets().add(getClass().getResource(ResourcePath.CSS).toExternalForm());

		try {
			mainPane.setBackground(new Background(new BackgroundImage(new Image(
					new FileInputStream(ResourcePath.BACKGROUND)), 
					null, 
					null, 
					null, 
					null
			)));
			this.installVersionImageView = new ImageView(new Image(new FileInputStream(ResourcePath.INSTALL_VERSION_PNG)));
			this.jarVersionImageView = new ImageView(new Image(new FileInputStream(ResourcePath.JAR_PNG)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

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
		this.installationPane.setLayoutY(440);
		this.installationPane.setVisible(false);
		this.installationPane.getChildren().addAll(progressBar, progressLabel);
		
		this.jarVersionImageView.setLayoutX(80);
		this.jarVersionImageView.setLayoutY(318);
		this.jarVersionImageView.setVisible(false);

		this.jarNameLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
		this.jarNameLabel.setLayoutX(120);
		this.jarNameLabel.setLayoutY(325);
		this.jarNameLabel.setVisible(false);

		this.versionNameLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.versionNameLabel.setLayoutX(80);
		this.versionNameLabel.setLayoutY(180);

		this.versionNameTextField.setPrefWidth(210);
		this.versionNameTextField.setLayoutX(290);
		this.versionNameTextField.setLayoutY(180);

		this.versionJarLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		this.versionJarLabel.setLayoutX(80);
		this.versionJarLabel.setLayoutY(240);

		this.versionJarButton.setPrefWidth(210);
		this.versionJarButton.setLayoutX(290);
		this.versionJarButton.setLayoutY(240);

		this.installVersionLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
		this.installVersionLabel.setLayoutX(175);
		this.installVersionLabel.setLayoutY(62);
		this.installVersionImageView.setLayoutX(80);
		this.installVersionImageView.setLayoutY(50);

		this.installVersionButton.setPrefWidth(100);
		this.installVersionButton.setPrefHeight(30);
		this.installVersionButton.setLayoutX(400);
		this.installVersionButton.setLayoutY(320);

		this.installSetupPane.getStyleClass().add("msw-white-pane");
		this.installSetupPane.setPrefWidth(600);
		this.installSetupPane.setPrefHeight(410);
		this.installSetupPane.getChildren().addAll(
			versionNameLabel, 
			versionNameTextField, 
			versionJarLabel,
			versionJarButton, 
			installVersionButton, 
			installVersionImageView, 
			installVersionLabel, 
			jarNameLabel,
			jarVersionImageView
		);

		this.toolBar.setLayoutX(0.00);
		this.toolBar.setLayoutY(0.00);
		this.toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		this.toolBar.getItems().add(closeButton);
		
		this.vboxPane.setPrefHeight(540);
		this.vboxPane.getChildren().addAll(installSetupPane, installationPane);
		
		this.vBox.setAlignment(Pos.CENTER);
		this.vBox.setPrefWidth(600);
		this.vBox.getChildren().add(vboxPane);
		
		this.hBox.setAlignment(Pos.CENTER);
		this.hBox.getChildren().add(vBox);
		
		mainPane.getChildren().addAll(hBox, toolBar);
		AnchorPane.setLeftAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(toolBar, 0.00);
		AnchorPane.setRightAnchor(toolBar, 0.00);
		AnchorPane.setTopAnchor(hBox, 0.0);
		AnchorPane.setLeftAnchor(hBox, 0.0);
		AnchorPane.setRightAnchor(hBox, 0.0);
		AnchorPane.setBottomAnchor(hBox, 0.0);
	}

	@Override
	protected void registerNodeEvents() {
		this.versionJarButton.setOnAction(this::onSelectJarEvent);
		this.installVersionButton.setOnAction(this::onInstallVersionEvent);
		this.closeButton.setOnAction(this::onCloseEvent);
	}

	@Override
	public void refresh() {
		closeButton.setDisable(false);
		installVersionButton.setDisable(false);
		versionNameTextField.setText(null);
		versionNameTextField.setDisable(false);
		versionJarButton.setDisable(false);
		jarVersionImageView.setVisible(false);
		jarNameLabel.setVisible(false);
		jarNameLabel.setText("");
		selectedJarFile = null;
		installationPane.setVisible(false);
		versionNameTextField.getStyleClass().remove("msw-error-border");
		versionJarButton.getStyleClass().remove("msw-error-border");
	}
	
	private void onSelectJarEvent(ActionEvent event) {
		Stage fileChooserStage = new Stage();
		fileChooserStage.centerOnScreen();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Minecraft Server JAR auswählen");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Minecraft Server JAR", "*.jar"));
		selectedJarFile = fileChooser.showOpenDialog(fileChooserStage);
		if (selectedJarFile != null) {
			jarVersionImageView.setVisible(true);
			jarNameLabel.setVisible(true);
			jarNameLabel.setText(selectedJarFile.getName());
		}
	}
	
	private void onInstallVersionEvent(ActionEvent event) {
		if (versionNameTextField.getText().equals("")) {
			versionNameTextField.getStyleClass().add("msw-error-border");
		} else {
			versionNameTextField.getStyleClass().remove("msw-error-border");
		}
		if (selectedJarFile == null) {
			versionJarButton.getStyleClass().add("msw-error-border");
		} else {
			versionJarButton.getStyleClass().remove("msw-error-border");
		}
		if ((!versionNameTextField.getText().equals("")) && selectedJarFile != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			installationPane.setVisible(true);
			progressBar.setProgress(0.2);
			closeButton.setDisable(true);
			versionNameTextField.setDisable(true);
			versionJarButton.setDisable(true);
			installVersionButton.setDisable(true);
			ServerVersion testVersion = new ServerVersion(
					versionNameTextField.getText(),
					ResourcePath.SERVER_VERSIONS + selectedJarFile.getName()
			);
			if (!ServerList.getServerList().existServerVersion(testVersion)) {
				VersionInstallerService vis = new VersionInstallerService(selectedJarFile, progressBar);
				vis.start();
				vis.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						DataStatus status = ServerList.getServerList().addServerVersion(testVersion);
						switch (status) {
						case SUCCESS:
							try {
								Files.copy(Paths.get(selectedJarFile.getAbsolutePath()),
										Paths.get(ResourcePath.SERVER_VERSIONS + selectedJarFile.getName()));
							} catch (IOException e) {
								e.printStackTrace();
							}
							progressLabel.setText("Installation erfolgreich!");
							alert.setTitle("Installation Erfolgreich!");
							alert.setHeaderText(versionNameTextField.getText());
							alert.setContentText("Minecraft Version " + versionNameTextField.getText()
									+ " wurde erfolgreich installiert!\n");
							alert.showAndWait();
							break;
						case ERROR:
							progressLabel.setText("Installation fehlgeschlagen!");
							alert.setAlertType(AlertType.ERROR);
							alert.setTitle("Installation fehlgeschlagen!");
							alert.setHeaderText(versionNameTextField.getText() + " konnte nicht installiert werden!");
							alert.showAndWait();
						default:
							break;
						}
						refresh();
						MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getMainScene());
					};
				});
				vis.setOnFailed(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						System.out.println("onFailed [SERVICE]");
					}
				});
			} else {
				alert.setAlertType(AlertType.ERROR);
				alert.setTitle("Fehler bei Installation!");
				alert.setHeaderText("Minecraft Version existiert schon!");
				alert.setContentText("Minecraft Version " + versionNameTextField.getText() + " existiert schon!");
				alert.showAndWait();
			}
		}
	}
	
	private void onCloseEvent(ActionEvent event) {
		refresh();
		MSWStandalone.getMainStage().setScene(SceneManager.getSceneManager().getServerVersionsScene());
	}

}