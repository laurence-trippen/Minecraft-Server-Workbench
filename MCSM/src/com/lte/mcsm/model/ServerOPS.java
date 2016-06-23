package com.lte.mcsm.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerOPS {
	
	private static final String OPS_JSON_PATH = "src/test-files/ops.json";
	private List<ServerOPEntry> serverOPEntries;
	
	public ServerOPS() {
		this.serverOPEntries = new ArrayList<ServerOPEntry>();
	}
	
	public void readOPSEntries() {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(OPS_JSON_PATH));
			JSONArray jsonArray = (JSONArray)obj;
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonEntry = (JSONObject)jsonArray.get(i);
				this.addOPSEntry(new ServerOPEntry(
						jsonEntry.get("uuid").toString(), 
						jsonEntry.get("name").toString(), 
						jsonEntry.get("level").toString()
				));
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void addOPSEntry(ServerOPEntry serverOPEntry) {
		if (serverOPEntries != null && serverOPEntry != null) {
			if (serverOPEntries.isEmpty()) {
				this.serverOPEntries.add(serverOPEntry);
				return;
			} else {				
				for (ServerOPEntry existingEntry : serverOPEntries) {
					if (serverOPEntry.getUuid().equals(existingEntry.getUuid())) {
						System.out.println("Spieler " + serverOPEntry.getName() + " hat schon OP-Eintrag!");
						return;
					} else {
						this.serverOPEntries.add(serverOPEntry);
					}
				}
			}
		} else {
			System.out.println("Op Eintrag darf nicht null sein!");
		}
	}
	
	public void printOPEntries() {
		if (!serverOPEntries.isEmpty()) {	
			for (ServerOPEntry entry : serverOPEntries) {
				System.out.println(
						"uuid: " + entry.getUuid() + "\n" +
						"name: " + entry.getName() + "\n" +
						"level: " + entry.getLevel()
				);
			}
		}
	}

	public List<ServerOPEntry> getServerOPEntries() {
		return serverOPEntries;
	}

	public void setServerOPEntries(List<ServerOPEntry> serverOPEntries) {
		this.serverOPEntries = serverOPEntries;
	}
	
}
