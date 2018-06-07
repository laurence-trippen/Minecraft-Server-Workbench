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

public class BannedAdresses extends AbstractJsonIO {
	
	private String bannedIpsPath;
	private List<BannedAdress> serverBannedIpEntries;
	
	public BannedAdresses(String bannedIpsPath) {
		this.bannedIpsPath = bannedIpsPath;
		this.serverBannedIpEntries = new ArrayList<BannedAdress>();
	}
	
	@Override
	public void readEntries() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(bannedIpsPath));
			JSONArray array = (JSONArray)obj;
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonEntry = (JSONObject)array.get(i);
				this.addBannedIpEntry(new BannedAdress(
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
	
	private void addBannedIpEntry(BannedAdress newBannedIpsEntry) {
		if (serverBannedIpEntries != null && newBannedIpsEntry != null) {
			for (BannedAdress existingEntry : serverBannedIpEntries) {
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
			for (BannedAdress entry : serverBannedIpEntries) {
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

	public List<BannedAdress> getServerBannedIpEntries() {
		return serverBannedIpEntries;
	}

	public void setServerBannedIpEntries(List<BannedAdress> serverBannedIpEntries) {
		this.serverBannedIpEntries = serverBannedIpEntries;
	}

}