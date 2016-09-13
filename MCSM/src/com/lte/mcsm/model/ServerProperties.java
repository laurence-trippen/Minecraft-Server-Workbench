package com.lte.mcsm.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.lte.mcsm.model.enums.Difficulty;
import com.lte.mcsm.model.enums.GameMode;
import com.lte.mcsm.model.enums.OpLevel;
import com.lte.mcsm.model.enums.WorldType;
import com.lte.mcsm.model.interfaces.IPropertiesController;

public class ServerProperties implements IPropertiesController {
	
	private Properties serverProperties;
	private String propertiesPath;
	private String generator;
	private OpLevel opLevel;
	private boolean allowNether;
	private String rpHash;
	private String levelName;
	private boolean enableQuery;
	private boolean allowFlight;
	private boolean showAchievements;
	private int serverPort;
	private int maxWorldSize;
	private WorldType worldType;
	private boolean enableRcon;
	private String levelSeed;
	private boolean forceGameMode;
	private String serverIp;
	private int networkCompression;
	private int maxBuildHeight;
	private boolean spawnNPCs;
	private boolean whitelist;
	private boolean spawnAnimals;
	private boolean enabledSnooper;
	private boolean onlineMode;
	private String resourcePack;
	private boolean enabledPVP;
	private Difficulty difficulty;
	private boolean enabledCommandBlock;
	private GameMode gameMode;
	private int playerIdleTime;
	private int maxPlayers;
	private int maxTickTime;
	private boolean spawnMonsters;
	private boolean generateStructs;
	private int viewDistance;
	private String motd;
	
	public ServerProperties(String propertiesPath) {
		this.serverProperties = new Properties();
		this.propertiesPath = propertiesPath;
		this.generator = "";
		this.opLevel = OpLevel.FOUR;
		this.allowNether = true;
		this.rpHash = "";
		this.levelName = "world";
		this.enableQuery = false;
		this.allowFlight = false;
		this.showAchievements = true;
		this.serverPort = 25565;
		this.maxWorldSize = 29999984;
		this.worldType = WorldType.DEFAULT;
		this.enableRcon = false;
		this.levelSeed = "";
		this.forceGameMode = false;
		this.serverIp = "";
		this.networkCompression = 256;
		this.maxBuildHeight = 256;
		this.spawnNPCs = true;
		this.whitelist = false;
		this.spawnAnimals = true;
		this.enabledSnooper = true;
		this.onlineMode = true;
		this.resourcePack = "";
		this.enabledPVP = true;
		this.difficulty = Difficulty.EASY;
		this.enabledCommandBlock = false;
		this.gameMode = GameMode.SURVIVAL;
		this.playerIdleTime = 0;
		this.maxPlayers = 20;
		this.maxTickTime = 60000;
		this.spawnMonsters = true;
		this.generateStructs = true;
		this.viewDistance = 10;
		this.motd = "A Minecraft Server";
	}
	
	@Override
	public void saveProperties() {

	}
	
	@Override
	public void loadProperties() {
		try {
			InputStream input = new FileInputStream(propertiesPath);
			serverProperties.load(input);
			this.setGenerator(serverProperties.getProperty("generator-settings"));
			switch (serverProperties.getProperty("op-permission-level")) {
			case "1":
				this.setOpLevel(OpLevel.ONE);
				break;
			case "2":
				this.setOpLevel(OpLevel.TWO);
				break;
			case "3":
				this.setOpLevel(OpLevel.THREE);
				break;
			case "4":
				this.setOpLevel(OpLevel.FOUR);
				break;
			}
			this.setAllowNether(serverProperties.getProperty("allow-nether") == "true" ? true : false);
			this.setLevelName(serverProperties.getProperty("level-name"));
			this.setEnableQuery(serverProperties.getProperty("enable-query") == "true" ? true : false);
			this.setAllowFlight(serverProperties.getProperty("allow-flight") == "true" ? true : false);
			this.setShowAchievements(serverProperties.getProperty("announce-player-achievements") == "true" ? true : false);
			this.setServerPort(Integer.valueOf(serverProperties.getProperty("server-port")));
			this.setMaxWorldSize(Integer.valueOf(serverProperties.getProperty("max-world-size")));
			switch (serverProperties.getProperty("level-type")) {
			case "DEFAULT":
				this.setWorldType(WorldType.DEFAULT);
				break;
			case "FLAT":
				this.setWorldType(WorldType.SUPERFLAT);
				break;
			case "LARGEBIOMES":
				this.setWorldType(WorldType.LARGEBIOMES);
				break;
			case "AMPLIFIED":
				this.setWorldType(WorldType.AMPLIFIED);
				break;
			case "CUSTOMIZED":
				this.setWorldType(WorldType.CUSTOMIZED);
				break;
			}
			this.setEnableRcon(serverProperties.getProperty("enable-rcon") == "true" ? true : false);
			this.setLevelSeed(serverProperties.getProperty("level-seed"));
			this.setForceGameMode(serverProperties.getProperty("force-gamemode") == "true" ? true : false);
			this.setServerIp(serverProperties.getProperty("server-ip"));
			this.setNetworkCompression(Integer.valueOf(serverProperties.getProperty("network-compression-treshold")));
			this.setMaxBuildHeight(Integer.valueOf(serverProperties.getProperty("max-build-height")));
			this.setSpawnNPCs(serverProperties.getProperty("spawn-npcs") == "true" ? true : false);
			this.setWhitelist(serverProperties.getProperty("white-list") == "true" ? true : false);
			this.setSpawnAnimals(serverProperties.getProperty("spawn-animals") == "true" ? true : false);
			this.setEnabledSnooper(serverProperties.getProperty("snooper-enabled") == "true" ? true : false);
			this.setOnlineMode(serverProperties.getProperty("online-mode") == "true" ? true : false);
			this.setResourcePack(serverProperties.getProperty("resource-pack"));
			this.setEnabledPVP(serverProperties.getProperty("pvp") == "true" ? true : false);
			switch (serverProperties.getProperty("difficulty")) {
			case "0":
				this.setDifficulty(Difficulty.PEACEFUL);
				break;
			case "1":
				this.setDifficulty(Difficulty.EASY);
				break;
			case "2":
				this.setDifficulty(Difficulty.NORMAL);
				break;
			case "3":
				this.setDifficulty(Difficulty.HARD);
				break;
			}
			this.setEnabledCommandBlock(serverProperties.getProperty("enable-command-block") == "true" ? true : false);
			switch (serverProperties.getProperty("gamemode")) {
			case "0":
				this.setGameMode(GameMode.SURVIVAL);
				break;
			case "1":
				this.setGameMode(GameMode.CREATIVE);
				break;
			case "2":
				this.setGameMode(GameMode.HARDCORE);
				break;
			case "3":
				this.setGameMode(GameMode.VISITOR);
				break;
			}
			this.setPlayerIdleTime(Integer.valueOf(serverProperties.getProperty("player-idle-timeout")));
			this.setMaxPlayers(Integer.valueOf(serverProperties.getProperty("max-players")));
			this.setMaxTickTime(Integer.valueOf(serverProperties.getProperty("max-tick-time")));
			this.setSpawnMonsters(serverProperties.getProperty("spawn-monsters") == "true" ? true : false);
			this.setGenerateStructs(serverProperties.getProperty("generate-structures") == "true" ? true : false);
			this.setViewDistance(Integer.valueOf(serverProperties.getProperty("view-distance")));
			this.setMotd(serverProperties.getProperty("motd"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Properties getServerProperties() {
		return serverProperties;
	}

	public void setServerProperties(Properties serverProperties) {
		this.serverProperties = serverProperties;
	}

	public String getPropertiesPath() {
		return propertiesPath;
	}

	public void setPropertiesPath(String propertiesPath) {
		this.propertiesPath = propertiesPath;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	public OpLevel getOpLevel() {
		return opLevel;
	}

	public void setOpLevel(OpLevel opLevel) {
		this.opLevel = opLevel;
	}

	public boolean isAllowNether() {
		return allowNether;
	}

	public void setAllowNether(boolean allowNether) {
		this.allowNether = allowNether;
	}

	public String getRpHash() {
		return rpHash;
	}

	public void setRpHash(String rpHash) {
		this.rpHash = rpHash;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public boolean isEnableQuery() {
		return enableQuery;
	}

	public void setEnableQuery(boolean enableQuery) {
		this.enableQuery = enableQuery;
	}

	public boolean isAllowFlight() {
		return allowFlight;
	}

	public void setAllowFlight(boolean allowFlight) {
		this.allowFlight = allowFlight;
	}

	public boolean isShowAchievements() {
		return showAchievements;
	}

	public void setShowAchievements(boolean showAchievements) {
		this.showAchievements = showAchievements;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public int getMaxWorldSize() {
		return maxWorldSize;
	}

	public void setMaxWorldSize(int maxWorldSize) {
		this.maxWorldSize = maxWorldSize;
	}

	public WorldType getWorldType() {
		return worldType;
	}

	public void setWorldType(WorldType worldType) {
		this.worldType = worldType;
	}

	public boolean isEnableRcon() {
		return enableRcon;
	}

	public void setEnableRcon(boolean enableRcon) {
		this.enableRcon = enableRcon;
	}

	public String getLevelSeed() {
		return levelSeed;
	}

	public void setLevelSeed(String levelSeed) {
		this.levelSeed = levelSeed;
	}

	public boolean isForceGameMode() {
		return forceGameMode;
	}

	public void setForceGameMode(boolean forceGameMode) {
		this.forceGameMode = forceGameMode;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public int getNetworkCompression() {
		return networkCompression;
	}

	public void setNetworkCompression(int networkCompression) {
		this.networkCompression = networkCompression;
	}

	public int getMaxBuildHeight() {
		return maxBuildHeight;
	}

	public void setMaxBuildHeight(int maxBuildHeight) {
		this.maxBuildHeight = maxBuildHeight;
	}

	public boolean isSpawnNPCs() {
		return spawnNPCs;
	}

	public void setSpawnNPCs(boolean spawnNPCs) {
		this.spawnNPCs = spawnNPCs;
	}

	public boolean isWhitelist() {
		return whitelist;
	}

	public void setWhitelist(boolean whitelist) {
		this.whitelist = whitelist;
	}

	public boolean isSpawnAnimals() {
		return spawnAnimals;
	}

	public void setSpawnAnimals(boolean spawnAnimals) {
		this.spawnAnimals = spawnAnimals;
	}

	public boolean isEnabledSnooper() {
		return enabledSnooper;
	}

	public void setEnabledSnooper(boolean enabledSnooper) {
		this.enabledSnooper = enabledSnooper;
	}

	public boolean isOnlineMode() {
		return onlineMode;
	}

	public void setOnlineMode(boolean onlineMode) {
		this.onlineMode = onlineMode;
	}

	public String getResourcePack() {
		return resourcePack;
	}

	public void setResourcePack(String resourcePack) {
		this.resourcePack = resourcePack;
	}

	public boolean isEnabledPVP() {
		return enabledPVP;
	}

	public void setEnabledPVP(boolean enabledPVP) {
		this.enabledPVP = enabledPVP;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public boolean isEnabledCommandBlock() {
		return enabledCommandBlock;
	}

	public void setEnabledCommandBlock(boolean enabledCommandBlock) {
		this.enabledCommandBlock = enabledCommandBlock;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public int getPlayerIdleTime() {
		return playerIdleTime;
	}

	public void setPlayerIdleTime(int playerIdleTime) {
		this.playerIdleTime = playerIdleTime;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getMaxTickTime() {
		return maxTickTime;
	}

	public void setMaxTickTime(int maxTickTime) {
		this.maxTickTime = maxTickTime;
	}

	public boolean isSpawnMonsters() {
		return spawnMonsters;
	}

	public void setSpawnMonsters(boolean spawnMonsters) {
		this.spawnMonsters = spawnMonsters;
	}

	public boolean isGenerateStructs() {
		return generateStructs;
	}

	public void setGenerateStructs(boolean generateStructs) {
		this.generateStructs = generateStructs;
	}

	public int getViewDistance() {
		return viewDistance;
	}

	public void setViewDistance(int viewDistance) {
		this.viewDistance = viewDistance;
	}

	public String getMotd() {
		return motd;
	}

	public void setMotd(String motd) {
		this.motd = motd;
	}
	
}