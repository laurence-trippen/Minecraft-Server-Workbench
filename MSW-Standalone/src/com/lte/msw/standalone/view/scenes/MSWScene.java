package com.lte.msw.standalone.view.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

public abstract class MSWScene extends Scene {

	public MSWScene(Parent root) {
		super(root);
	}
	
	public MSWScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
	}

	public MSWScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
	}

	public MSWScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
	}

	public MSWScene(Parent root, double width, double height) {
		super(root, width, height);
	}

	public MSWScene(Parent root, Paint fill) {
		super(root, fill);
	}
	
	protected abstract void initNodes();
	protected abstract void defineNodes();
	protected abstract void registerNodeEvents();
	
}
