package com.laurencetrippen.msw.standalone.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.laurencetrippen.msw.standalone.manager.SceneManager;
import com.laurencetrippen.msw.standalone.model.abstracts.ResourcePath;
import com.laurencetrippen.msw.standalone.model.enums.DataStatus;
import com.laurencetrippen.msw.standalone.model.enums.Difficulty;
import com.laurencetrippen.msw.standalone.model.enums.GameMode;
import com.laurencetrippen.msw.standalone.model.enums.OpLevel;
import com.laurencetrippen.msw.standalone.model.enums.WorldType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MSWStorage {
	
	private static MSWStorage instance = null;
	private ObservableList<Server> serverList;
	private ObservableList<ServerVersion> serverVersions;
	private int serverCounter;
	private int serverVersionCounter;
	
	public static MSWStorage getInstance() {
		if (instance == null) {
			instance = new MSWStorage();
		}
		return instance;
	}
	
	private MSWStorage() {
		this.serverList = FXCollections.observableArrayList();
		this.serverVersions = FXCollections.observableArrayList();
		this.serverCounter = 0;
		this.serverVersionCounter = 0;
		loadXML();
	}
	
	public DataStatus addServer(Server newServer) {
		if (newServer != null) {	
			serverCounter++;
			newServer.setId(serverCounter);
			serverList.add(newServer);
			saveXML();
			SceneManager.getSceneManager().getMainScene().fetch();
			return DataStatus.SUCCESS;
		}
		return DataStatus.ERROR;
	}
	
	public DataStatus deleteServer(Server deleteServer) {
		if (deleteServer != null) {
			int counter = 0;
			if (serverList.remove(deleteServer)) {
				for (Server server : serverList) {
					counter++;
					server.setId(counter);
				}
				saveXML();
				SceneManager.getSceneManager().getMainScene().fetch();
				return DataStatus.SUCCESS;
			} else {
				return DataStatus.ERROR;
			}
		}
		return DataStatus.ERROR;
	}
	
	public boolean existServer(String newServerName) {
		for (Server server : serverList) {
			if (newServerName.equals(server.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public DataStatus addServerVersion(ServerVersion serverVersion) {
		if (serverVersion != null) {
			serverVersionCounter++;
			serverVersion.setId(serverVersionCounter);
			serverVersions.add(serverVersion);
			saveXML();
			SceneManager.getSceneManager().getServerVersionsScene().fetch();
			return DataStatus.SUCCESS;
		}
		return DataStatus.ERROR;
	}
	
	public DataStatus deleteServerVersion(ServerVersion serverVersion) {
		if (serverVersion != null) {
			int counter = 0;
			File versionFile = new File(serverVersion.getPath());
			if (serverVersions.remove(serverVersion)) {
				if (versionFile.exists()) {
					versionFile.delete();
				}
				for (ServerVersion version : serverVersions) {
					counter++;
					version.setId(counter);
				}
				saveXML();
				SceneManager.getSceneManager().getServerVersionsScene().fetch();
				return DataStatus.SUCCESS;
			} else {
				return DataStatus.ERROR;
			}
		}
		return DataStatus.ERROR;
	}
	
	public boolean existServerVersion(ServerVersion serverVersion) {
		for (ServerVersion existingVersion : serverVersions) {
			if (
					serverVersion.getName().equals(existingVersion.getName()) || 
					serverVersion.getPath().equals(existingVersion.getPath())
			) {
				return true;
			}
		}
		return false;
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
			properties.addContent(new Element("spawnMonsters").setText(saveServer.getServerProperties().isSpawnMonsters() == true ? "true" : "false"));
			properties.addContent(new Element("generateStructs").setText(saveServer.getServerProperties().isGenerateStructs() == true ? "true" : "false"));
			properties.addContent(new Element("viewDistance").setText(Integer.toString(saveServer.getServerProperties().getViewDistance())));
			properties.addContent(new Element("motd").setText(saveServer.getServerProperties().getMotd()));
			Element versionElement = new Element("version");
			versionElement.addContent(new Element("name").setText(saveServer.getServerVersion().getName()));
			versionElement.addContent(new Element("path").setText(saveServer.getServerVersion().getPath()));
			Element server = new Element("Server");
			server.setAttribute("id", ""+saveServer.getId());
			server.addContent(new Element("name").setText(saveServer.getName()));
			server.addContent(new Element("description").setText(saveServer.getDescription()));
			server.addContent(new Element("creationDate").setText(saveServer.getCreationDate()));
			server.addContent(versionElement);
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
			xmlOutputter.output(xml, new FileOutputStream(new File(ResourcePath.XML)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadXML() {
		try {
			Document xml = new SAXBuilder().build(ResourcePath.XML);
			if (xml.hasRootElement()) {
				Element root = xml.getRootElement();
				List<Element> xmlServers = root.getChild("MCServer").getChildren();
				List<Element> xmlVersions = root.getChild("MCVersions").getChildren();
				for (Element xmlServer : xmlServers) {
					serverCounter++;
					Element version = xmlServer.getChild("version");
					Element properties = xmlServer.getChild("properties");
					ServerVersion serverVersion = new ServerVersion(version.getChildText("name"), version.getChildText("path"));
					Server loadServer = new Server(xmlServer.getChildText("name"), serverVersion, serverCounter);
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
					serverProperties.setSpawnMonsters(Boolean.parseBoolean(properties.getChildText("spawnMonsters")));
					serverProperties.setGenerateStructs(Boolean.parseBoolean(properties.getChildText("generateStructs")));
					serverProperties.setViewDistance(Integer.parseInt(properties.getChildText("viewDistance")));
					serverProperties.setMotd(properties.getChildText("motd"));
					serverList.add(loadServer);		
				}
				for (Element xmlVersion : xmlVersions) {
					serverVersionCounter++;
					ServerVersion serverVersion = new ServerVersion(
							serverVersionCounter, 
							xmlVersion.getChildText("name"),
							xmlVersion.getChildText("path")
					);
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
	
	public ObservableList<Server> getServer() {
		return serverList;
	}

	public ObservableList<ServerVersion> getServerVersions() {
		return serverVersions;
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
	
}