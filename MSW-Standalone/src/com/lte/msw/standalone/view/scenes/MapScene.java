package com.lte.msw.standalone.view.scenes;

import com.lte.msw.standalone.manager.DesktopManager;

import javafx.scene.layout.AnchorPane;

public class MapScene extends MSWScene {

	public MapScene() {
		super(new AnchorPane(), DesktopManager.getScreenSize().getWidth(), DesktopManager.getScreenSize().getHeight());
		this.initNodes();
		this.defineNodes();
		this.registerNodeEvents();
	}
	
	@Override
	protected void initNodes() {
		
	}

	@Override
	protected void defineNodes() {
		
	}

	@Override
	protected void registerNodeEvents() {
		
	}

}