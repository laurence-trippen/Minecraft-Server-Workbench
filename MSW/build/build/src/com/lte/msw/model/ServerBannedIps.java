package com.lte.msw.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.lte.msw.model.abstracts.JsonIO;

public class ServerBannedIps extends JsonIO {
	
	private String bannedIpsPath;
	private List<ServerBannedIpEntry> serverBannedIpEntries;
	
	public ServerBannedIps(String bannedIpsPath) {
		this.bannedIpsPath = bannedIpsPath;
		this.serverBannedIpEntries = new ArrayList<ServerBannedIpEntry>();
	}
	
	@Override
	public void readEntries() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(bannedIpsPath));
			JSONArray array = (JSONArray)obj;
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonEntry = (JSONObject)array.get(i);
				this.addBannedIpEntry(new ServerBannedIpEntry(
						jsonEntry.get("ip").toString(), 
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
	
	private void addBannedIpEntry(ServerBannedIpEntry newBannedIpsEntry) {
		if (serverBannedIpEntries != null && newBannedIpsEntry != null) {
			for (ServerBannedIpEntry existingEntry : serverBannedIpEntries) {
				if (newBannedIpsEntry.getIp().equals(existingEntry.getIp())) {
					System.out.println(
							"IP-Adresse " + 
							newBannedIpsEntry.getIp() + 
							" besitzt bereits BannedIp-Eintrag!"
					);
					return;
				}
			}
			this.serverBannedIpEntries.add(newBannedIpsEntry);
		} else {
			System.out.println("BannedIp-Eintrag darf nicht null sein!");
		}
	}

	@Override
	public void printEntries() {
		if (!serverBannedIpEntries.isEmpty()) {
			for (ServerBannedIpEntry entry : serverBannedIpEntries) {
				System.out.println(
						"ip: " + entry.getIp() + "\n" +
						"created: " + entry.getCreated() + "\n" +
						"source: " + entry.getSource() + "\n" +
						"expires: " + entry.getExpires() + "\n" +
						"reason: " + entry.getReason() + "\n"
				);
			}
		}
	}

	public String getBannedIpsPath() {
		return bannedIpsPath;
	}

	public void setBannedIpsPath(String bannedIpsPath) {
		this.bannedIpsPath = bannedIpsPath;
	}

	public List<ServerBannedIpEntry> getServerBannedIpEntries() {
		return serverBannedIpEntries;
	}

	public void setServerBannedIpEntries(List<ServerBannedIpEntry> serverBannedIpEntries) {
		this.serverBannedIpEntries = serverBannedIpEntries;
	}

}