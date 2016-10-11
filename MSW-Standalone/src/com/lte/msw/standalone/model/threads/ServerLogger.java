package com.lte.msw.model.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.control.TextArea;

public class ServerLogger implements Runnable {
	
	private BufferedReader reader;
	private TextArea console;
	
	public ServerLogger(InputStream is, TextArea console) {
		this.reader = new BufferedReader(new InputStreamReader(is));
		this.console = console;
	}
	
	@Override
	public void run() {
		try {
			String line = reader.readLine();
			while (line != null) {
				console.appendText(line + "\n");
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}