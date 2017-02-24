package com.lte.msw.standalone.view.components;

import javafx.scene.layout.Pane;

public abstract class MSWComponent extends Pane {
	
	protected abstract void initNodes();
	protected abstract void defineNodes();
	protected abstract void registerNodeEvents();
	
}