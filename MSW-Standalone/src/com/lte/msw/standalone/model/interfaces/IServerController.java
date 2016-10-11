package com.lte.msw.standalone.model.interfaces;

import javafx.scene.control.TextArea;

public interface IServerController {
	
	public void init();
	public void start(TextArea console);
	public void stop();

}
