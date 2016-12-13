package com.lte.msw.standalone.model.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ServerLogger implements Runnable {
	
	private BufferedReader reader;
	
	public ServerLogger(InputStream is) {
		this.reader = new BufferedReader(new InputStreamReader(is));
	}
	
	@Override
	public void run() {
		try {
			String line = reader.readLine();
			while (line != null) {
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}