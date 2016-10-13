package com.lte.msw.standalone.model.threads;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Scene;

public class ServerCreateService extends Service<Void> {
	
	private Scene ui;
	
	public ServerCreateService(Scene ui) {
		this.ui = ui;
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				
				return null;
			}
		};
	}
	
	private void updateProgress(double progress) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				
			}
		});
	}

}