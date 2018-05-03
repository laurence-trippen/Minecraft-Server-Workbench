package com.laurencetrippen.msw.standalone.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.laurencetrippen.msw.standalone.model.abstracts.AbstractJsonIO;

public class BannedPlayers extends AbstractJsonIO {
	
	private String bannedPlayersPath;
	private List<BannedPlayer> serverBannedPlayerEntries;
	
	public BannedPlayers(String bannedPlayersPath) {
		this.bannedPlayersPath = bannedPlayersPath;
		this.serverBannedPlayerEntries = new ArrayList<BannedPlayer>();
	}
	
	@Override
	public void readEntries() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(bannedPlayersPath));
			JSONArray array = (JSONArray)obj;
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonEntry = (JSONObject)array.get(i);
				this.addBannedPlayerEntry(new BannedPlayer(
						jsonEntry.get("uuid").toString(), 
						jsonEntry.get("name").toString(), 
						jsonEntry.get("created").toString(), 
						jsonEntry.get("source").toString(), 
						jsonEntry.get("expires").toString(), 
						jsonEntry.get("reason").toString()
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
	
	private void addBannedPlayerEntry(BannedPlayer newBannedPlayerEntry) {
		if (serverBannedPlayerEntries != null && newBannedPlayerEntry != null) {
			for (BannedPlayer existingEntry : serverBannedPlayerEntries) {
				if (newBannedPlayerEntry.getUuid().equals(existingEntry.getUuid())) {
					System.out.println("Spieler " + newBannedPlayerEntry.getName() + " hat schon BannedPlayer-Eintrag!");
					return;
				}
			}
			this.serverBannedPlayerEntries.add(newBannedPlayerEntry);
		} else {
			System.out.println("BannedPlayer-Eintrag darf nicht null sein!");
		}
	}

	@Override
	public void printEntries() {
		if (!serverBannedPlayerEntries.isEmpty()) {
			for (BannedPlayer entry : serverBannedPlayerEntries) {
				System.out.println(
						"uuid: " + entry.getUuid() + "\n" +
						"name: " + entry.getName() + "\n" +
						"created: " + entry.getCreated() + "\n" +
						"source: " + entry.getSource() + "\n" +
						"expires: " + entry.getExpires() + "\n" +
						"reason: " + entry.getReason() + "\n"
				);
			}
		}
	}

	public String getBannedPlayersPath() {
		return bannedPlayersPath;
	}

	public void setBannedPlayersPath(String bannedPlayersPath) {
		this.bannedPlayersPath = bannedPlayersPath;
	}

	public List<BannedPlayer> getServerBannedPlayerEntries() {
		return serverBannedPlayerEntries;
	}

	public void setServerBannedPlayerEntries(List<BannedPlayer> serverBannedPlayerEntries) {
		this.serverBannedPlayerEntries = serverBannedPlayerEntries;
	}
	
}