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

public class ServerWhitelist extends JsonIO {
	
	private String whitelistPath;
	private List<ServerWhitelistEntry> serverWhitelistEntries;
	
	public ServerWhitelist(String whitelistPath) {
		this.whitelistPath = whitelistPath;
		this.serverWhitelistEntries = new ArrayList<ServerWhitelistEntry>();
	}
	
	@Override
	public void readEntries() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(whitelistPath));
			JSONArray array = (JSONArray)obj;
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonEntry = (JSONObject)array.get(i);
				this.addWhitelistEntry(new ServerWhitelistEntry(
						jsonEntry.get("uuid").toString(), 
						jsonEntry.get("name").toString()
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
	
	private void addWhitelistEntry(ServerWhitelistEntry newWhitelistEntry) {
		if (serverWhitelistEntries != null && newWhitelistEntry != null) {
			for (ServerWhitelistEntry existingEntry : serverWhitelistEntries) {
				if (newWhitelistEntry.getUuid().equals(existingEntry.getUuid())) {
					System.out.println("Spieler " + newWhitelistEntry.getName() + " hat schon Whitelist-Eintrag!");
					return;
				}
			}
			this.serverWhitelistEntries.add(newWhitelistEntry);
		} else {
			System.out.println("Whitelist-Eintrag darf nicht null sein!");
		}
	}

	@Override
	public void printEntries() {
		if (!serverWhitelistEntries.isEmpty()) {	
			for (ServerWhitelistEntry entry : serverWhitelistEntries) {
				System.out.println(
						"uuid: " + entry.getUuid() + "\n" +
						"name: " + entry.getName() + "\n"
				);
			}
		}
	}

	public String getWhitelistPath() {
		return whitelistPath;
	}

	public void setWhitelistPath(String whitelistPath) {
		this.whitelistPath = whitelistPath;
	}

	public List<ServerWhitelistEntry> getServerWhitelistEntries() {
		return serverWhitelistEntries;
	}

	public void setServerWhitelistEntries(List<ServerWhitelistEntry> serverWhitelistEntries) {
		this.serverWhitelistEntries = serverWhitelistEntries;
	}
	
}