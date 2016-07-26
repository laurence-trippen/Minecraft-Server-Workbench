package com.lte.mcsm.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.lte.mcsm.model.enums.Difficulty;
import com.lte.mcsm.model.enums.GameMode;
import com.lte.mcsm.model.enums.OpLevel;
import com.lte.mcsm.model.enums.WorldType;

public class ServerList {
	
	private static ServerList instance = null;
	private List<Server> serverList;
	private List<ServerVersion> serverVersions;
	private int serverCounter;
	private int serverVersionCounter;
	
	public static ServerList getServerList() {
		if (instance == null) {
			instance = new ServerList();
		}
		return instance;
	}
	
	private ServerList() {
		this.serverList = new ArrayList<Server>();
		this.serverVersions = new ArrayList<ServerVersion>();
		this.serverCounter = 0;
		this.serverVersionCounter = 0;
		loadXML();
	}
	
	public void addServer(Server newServer) {
		if (newServer != null && serverList != null) {
			for (Server server : serverList) {
				if (newServer.getName().equals(server.getName())) {
					System.out.println("Server existiert schon!");
					return;
				}
			}	
			serverCounter++;
			newServer.setId(serverCounter);
			serverList.add(newServer);
			saveXML();
		}
	}
	
	public void addServerVersion(ServerVersion serverVersion) {
		if (serverVersion != null && serverVersions != null) {
			for (ServerVersion existingVersion : serverVersions) {
				if (serverVersion.getName().equals(existingVersion.getName())) {
					System.out.println("Serverversion existiert schon!");
					return;
				}
			}
			serverVersionCounter++;
			serverVersion.setId(serverVersionCounter);
			serverVersions.add(serverVersion);
			saveXML();
		}
	}
	
	private void saveXML() {
		Document xml = new Document();
		xml.setRootElement(new Element("MCSM"));
		xml.getRootElement().addContent(new Element("MCServer"));
		xml.getRootElement().addContent(new Element("MCVersions"));
		for (Server saveServer : serverList) {
			Element properties = new Element("properties");
			properties.addContent(new Element("generator").setText(saveServer.getServerProperties().getGenerator()));
			properties.addContent(new Element("opLevel").setText(saveServer.getServerProperties().getOpLevel().toString()));
			properties.addContent(new Element("allowNether").setText(saveServer.getServerProperties().isAllowNether() == true ? "true" : "false"));
			properties.addContent(new Element("rpHash").setText(saveServer.getServerProperties().getRpHash()));
			properties.addContent(new Element("levelName").setText(saveServer.getServerProperties().getLevelName()));
			properties.addContent(new Element("enableQuery").setText(saveServer.getServerProperties().isEnableQuery() == true ? "true" : "false"));
			properties.addContent(new Element("allowFlight").setText(saveServer.getServerProperties().isAllowNether() == true ? "true" : "false"));
			properties.addContent(new Element("showAchievements").setText(saveServer.getServerProperties().isShowAchievements() == true ? "true" : "false"));
			properties.addContent(new Element("serverPort").setText(Integer.toString(saveServer.getServerProperties().getServerPort())));
			properties.addContent(new Element("maxWorldSize").setText(Integer.toString(saveServer.getServerProperties().getMaxWorldSize())));
			properties.addContent(new Element("worldType").setText(saveServer.getServerProperties().getWorldType().toString()));
			properties.addContent(new Element("enableRcon").setText(saveServer.getServerProperties().isEnableRcon() == true ? "true" : "false"));
			properties.addContent(new Element("levelSeed").setText(saveServer.getServerProperties().getLevelSeed()));
			properties.addContent(new Element("forceGameMode").setText(saveServer.getServerProperties().isForceGameMode() == true ? "true" : "false"));
			properties.addContent(new Element("serverIp").setText(saveServer.getServerProperties().getServerIp()));
			properties.addContent(new Element("networkCompression").setText(Integer.toString(saveServer.getServerProperties().getNetworkCompression())));
			properties.addContent(new Element("maxBuildHeight").setText(Integer.toString(saveServer.getServerProperties().getMaxBuildHeight())));
			properties.addContent(new Element("spawnNPCs").setText(saveServer.getServerProperties().isSpawnNPCs() == true ? "true" : "false"));
			properties.addContent(new Element("whitelist").setText(saveServer.getServerProperties().isWhitelist() == true ? "true" : "false"));
			properties.addContent(new Element("enabledSnooper").setText(saveServer.getServerProperties().isEnabledSnooper() == true ? "true" : "false"));
			properties.addContent(new Element("onlineMode").setText(saveServer.getServerProperties().isOnlineMode() == true ? "true" : "false"));
			properties.addContent(new Element("resourcePack").setText(saveServer.getServerProperties().getResourcePack()));
			properties.addContent(new Element("enabledPVP").setText(saveServer.getServerProperties().isEnabledPVP() == true ? "true" : "false"));
			properties.addContent(new Element("difficulty").setText(saveServer.getServerProperties().getDifficulty().toString()));
			properties.addContent(new Element("enabledCommandBlock").setText(saveServer.getServerProperties().isEnabledCommandBlock() == true ? "true" : "false"));
			properties.addContent(new Element("gameMode").setText(saveServer.getServerProperties().getGameMode().toString()));
			properties.addContent(new Element("playerIdleTime").setText(Integer.toString(saveServer.getServerProperties().getPlayerIdleTime())));
			properties.addContent(new Element("maxPlayers").setText(Integer.toString(saveServer.getServerProperties().getMaxPlayers())));
			properties.addContent(new Element("maxTickTime").setText(Integer.toString(saveServer.getServerProperties().getMaxTickTime())));
			properties.addContent(new Element("spawnMonsters").setText(saveServer.getServerProperties().isSpawnMonsters() == true ? "true" : "false"));
			properties.addContent(new Element("generateStructs").setText(saveServer.getServerProperties().isGenerateStructs() == true ? "true" : "false"));
			properties.addContent(new Element("viewDistance").setText(Integer.toString(saveServer.getServerProperties().getViewDistance())));
			properties.addContent(new Element("motd").setText(saveServer.getServerProperties().getMotd()));
			Element server = new Element("Server");
			server.setAttribute("id", ""+saveServer.getId());
			server.addContent(new Element("name").setText(saveServer.getName()));
			server.addContent(new Element("description").setText(saveServer.getDescription()));
			server.addContent(new Element("creationDate").setText(saveServer.getCreationDate()));
			server.addContent(properties);
			xml.getRootElement().getChild("MCServer").addContent(server);
		}
		for (ServerVersion saveVersion : serverVersions) {
			Element serverVersion = new Element("ServerVersion");
			serverVersion.setAttribute("id", ""+saveVersion.getId());
			serverVersion.addContent(new Element("name").setText(saveVersion.getName()));
			serverVersion.addContent(new Element("path").setText(saveVersion.getPath()));
			xml.getRootElement().getChild("MCVersions").addContent(serverVersion);
		}
		XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		try {
			xmlOutputter.output(xml, new FileOutputStream(new File(Path.XML)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadXML() {
		try {
			Document xml = new SAXBuilder().build(Path.XML);
			if (xml.hasRootElement()) {
				Element root = xml.getRootElement();
				List<Element> xmlServers = root.getChild("MCServer").getChildren();
				List<Element> xmlVersions = root.getChild("MCVersions").getChildren();
				for (Element xmlServer : xmlServers) {
					serverCounter++;
					Element properties = xmlServer.getChild("properties");
					Server loadServer = new Server(xmlServer.getChildText("name"), serverCounter);
					loadServer.setDescription(xmlServer.getChildText("description"));
					loadServer.setCreationDate(xmlServer.getAttributeValue("creationDate"));
					ServerProperties serverProperties = loadServer.getServerProperties();
					serverProperties.setGenerator(properties.getChildText("generator"));
					serverProperties.setOpLevel(OpLevel.valueOf(properties.getChildText("opLevel")));
					serverProperties.setAllowNether(Boolean.parseBoolean(properties.getChildText("allowNether")));
					serverProperties.setRpHash(properties.getChildText("rpHash"));
					serverProperties.setLevelName(properties.getChildText("levelName"));
					serverProperties.setEnableQuery(Boolean.parseBoolean(properties.getChildText("enableQuery")));
					serverProperties.setAllowFlight(Boolean.parseBoolean(properties.getChildText("allowFlight")));
					serverProperties.setShowAchievements(Boolean.parseBoolean(properties.getChildText("showAchievements")));
					serverProperties.setServerPort(Integer.parseInt(properties.getChildText("serverPort")));
					serverProperties.setMaxWorldSize(Integer.parseInt(properties.getChildText("maxWorldSize")));
					serverProperties.setWorldType(WorldType.valueOf(properties.getChildText("worldType")));
					serverProperties.setEnableRcon(Boolean.parseBoolean(properties.getChildText("enableRcon")));
					serverProperties.setLevelSeed(properties.getChildText("levelSeed"));
					serverProperties.setForceGameMode(Boolean.parseBoolean(properties.getChildText("forceGameMode")));
					serverProperties.setServerIp(properties.getChildText("serverIp"));
					serverProperties.setNetworkCompression(Integer.parseInt(properties.getChildText("networkCompression")));
					serverProperties.setMaxBuildHeight(Integer.parseInt(properties.getChildText("maxBuildHeight")));
					serverProperties.setSpawnNPCs(Boolean.parseBoolean(properties.getChildText("spawnNPCs")));
					serverProperties.setWhitelist(Boolean.parseBoolean(properties.getChildText("whitelist")));
					serverProperties.setSpawnAnimals(Boolean.parseBoolean(properties.getChildText("spawnAnimals")));
					serverProperties.setEnabledSnooper(Boolean.parseBoolean(properties.getChildText("enabledSnooper")));
					serverProperties.setOnlineMode(Boolean.parseBoolean(properties.getChildText("onlineMode")));
					serverProperties.setResourcePack(properties.getChildText("resourcePack"));
					serverProperties.setEnabledPVP(Boolean.parseBoolean(properties.getChildText("enabledPVP")));
					serverProperties.setDifficulty(Difficulty.valueOf(properties.getChildText("difficulty")));
					serverProperties.setEnabledCommandBlock(Boolean.parseBoolean(properties.getChildText("enabledCommandBlock")));
					serverProperties.setGameMode(GameMode.valueOf(properties.getChildText("gameMode")));
					serverProperties.setPlayerIdleTime(Integer.parseInt(properties.getChildText("playerIdleTime")));
					serverProperties.setMaxPlayers(Integer.parseInt(properties.getChildText("maxPlayers")));
					serverProperties.setMaxTickTime(Integer.parseInt(properties.getChildText("maxTickTime")));
					serverProperties.setSpawnMonsters(Boolean.parseBoolean(properties.getChildText("spawnMonsters")));
					serverProperties.setGenerateStructs(Boolean.parseBoolean(properties.getChildText("generateStructs")));
					serverProperties.setViewDistance(Integer.parseInt(properties.getChildText("viewDistance")));
					serverProperties.setMotd(properties.getChildText("motd"));
					serverList.add(loadServer);		
				}
				for (Element xmlVersion : xmlVersions) {
					serverVersionCounter++;
					ServerVersion serverVersion = new ServerVersion(serverVersionCounter, xmlVersion.getChildText("name"));
					serverVersion.setPath(xmlVersion.getChildText("path"));
					serverVersions.add(serverVersion);
				}
			}
		} catch (JDOMParseException jdpe) {
			jdpe.printStackTrace();
		} catch (JDOMException jde) {
			jde.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public List<Server> getServer() {
		return serverList;
	}

	public void setServer(List<Server> server) {
		this.serverList = server;
	}

	public List<ServerVersion> getServerVersions() {
		return serverVersions;
	}

	public void setServerVersions(List<ServerVersion> serverVersions) {
		this.serverVersions = serverVersions;
	}

	public int getServerCount() {
		return serverCounter;
	}

	public void setServerCount(int serverCount) {
		this.serverCounter = serverCount;
	}

	public int getServerVersionCounter() {
		return serverVersionCounter;
	}

	public void setServerVersionCounter(int serverVersionCounter) {
		this.serverVersionCounter = serverVersionCounter;
	}

	public static void setInstance(ServerList instance) {
		ServerList.instance = instance;
	}
	
}
