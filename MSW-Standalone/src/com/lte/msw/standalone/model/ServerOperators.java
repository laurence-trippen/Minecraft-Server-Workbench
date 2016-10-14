package com.lte.msw.standalone.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.lte.msw.standalone.model.abstracts.JsonIO;

public class ServerOperators extends JsonIO {
	
	private String opsPath;
	private List<ServerOperatorEntry> serverOPEntries;
	
	public ServerOperators(String opsPath) {
		this.opsPath = opsPath;
		this.serverOPEntries = new ArrayList<ServerOperatorEntry>();
	}
	
	@Override
	public void readEntries() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(opsPath));
			JSONArray array = (JSONArray)obj;
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonEntry = (JSONObject)array.get(i);
				this.addOPSEntry(new ServerOperatorEntry(
						jsonEntry.get("uuid").toString(), 
						jsonEntry.get("name").toString(), 
						jsonEntry.get("level").toString()
				));
			}
		} catch (ParseException pe) {
			pe.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addOPSEntry(ServerOperatorEntry serverOPEntry) {
		if (serverOPEntries != null && serverOPEntry != null) {
			for (ServerOperatorEntry existingEntry : serverOPEntries) {
				if (serverOPEntry.getUuid().equals(existingEntry.getUuid())) {
					System.out.println("Spieler " + serverOPEntry.getName() + " hat schon OP-Eintrag!");
					return;
				}
			}
			this.serverOPEntries.add(serverOPEntry);
		} else {
			System.out.println("OP-Eintrag darf nicht null sein!");
		}
	}
	
	@Override
	public void printEntries() {
		if (!serverOPEntries.isEmpty()) {	
			for (ServerOperatorEntry entry : serverOPEntries) {
				System.out.println(
						"uuid: " + entry.getUuid() + "\n" +
						"name: " + entry.getName() + "\n" +
						"level: " + entry.getLevel() + "\n"
				);
			}
		}
	}
	
	public String getOpsPath() {
		return opsPath;
	}

	public void setOpsPath(String opsPath) {
		this.opsPath = opsPath;
	}

	public List<ServerOperatorEntry> getServerOPEntries() {
		return serverOPEntries;
	}

	public void setServerOPEntries(List<ServerOperatorEntry> serverOPEntries) {
		this.serverOPEntries = serverOPEntries;
	}
	
}