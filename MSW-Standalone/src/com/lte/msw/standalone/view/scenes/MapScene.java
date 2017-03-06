package com.lte.msw.standalone.view.scenes;

import com.lte.msw.standalone.manager.DesktopManager;
import com.lte.msw.standalone.model.Map;

import javafx.scene.layout.AnchorPane;

public class MapScene extends MSWScene {
	
	private Map map;
	
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
	
	public void setMap(Map map) {
		this.map = map;
	}

}