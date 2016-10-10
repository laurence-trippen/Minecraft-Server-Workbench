package com.lte.msw.serverapi.server.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.lte.msw.serverapi.server.model.enums.Difficulty;
import com.lte.msw.serverapi.server.model.enums.GameMode;
import com.lte.msw.serverapi.server.model.enums.OpLevel;
import com.lte.msw.serverapi.server.model.enums.WorldType;
import com.lte.msw.serverapi.server.model.interfaces.IPropertiesController;

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
	public void save() {
		OutputStream output = null;
		try {
			output = new FileOutputStream(propertiesPath);
			this.serverProperties.setProperty("generator-settings", this.getGenerator());
			switch (this.getOpLevel()) {
			case ONE:
				this.serverProperties.setProperty("op-permission-level", "1");
				break;
			case TWO:
				this.serverProperties.setProperty("op-permission-level", "2");
				break;
			case THREE:
				this.serverProperties.setProperty("op-permission-level", "3");
				break;
			case FOUR:
				this.serverProperties.setProperty("op-permission-level", "4");
				break;
			}
			this.serverProperties.setProperty("allow-nether", this.isAllowNether() ? "true" : "false");
			this.serverProperties.setProperty("level-name", this.getLevelName());
			this.serverProperties.setProperty("enable-query", this.isEnableQuery() ? "true" : "false");
			this.serverProperties.setProperty("allow-flight", this.isAllowFlight() ? "true" : "false");
			this.serverProperties.setProperty("announce-player-achievements",
					this.isShowAchievements() ? "true" : "false");
			this.serverProperties.setProperty("server-port", String.valueOf(this.getServerPort()));
			this.serverProperties.setProperty("max-world-size", String.valueOf(this.getMaxWorldSize()));
			this.serverProperties.setProperty("level-type", this.getWorldType().toString());
			this.serverProperties.setProperty("enable-rcon", this.isEnableRcon() ? "true" : "false");
			this.serverProperties.setProperty("level-seed", this.getLevelSeed());
			this.serverProperties.setProperty("force-gamemode", this.isForceGameMode() ? "true" : "false");
			this.serverProperties.setProperty("server-ip", this.getServerIp());
			this.serverProperties.setProperty("network-compression-threshold",
					String.valueOf(this.getNetworkCompression()));
			this.serverProperties.setProperty("max-build-height", String.valueOf(this.getMaxBuildHeight()));
			this.serverProperties.setProperty("spawn-npcs", this.isSpawnNPCs() ? "true" : "false");
			this.serverProperties.setProperty("white-list", this.isWhitelist() ? "true" : "false");
			this.serverProperties.setProperty("spawn-animals", this.isSpawnAnimals() ? "true" : "false");
			this.serverProperties.setProperty("snooper-enabled", this.isEnabledSnooper() ? "true" : "false");
			this.serverProperties.setProperty("online-mode", this.isOnlineMode() ? "true" : "false");
			this.serverProperties.setProperty("resource-pack", this.getResourcePack());
			this.serverProperties.setProperty("pvp", this.isEnabledPVP() ? "true" : "false");
			switch (this.getDifficulty()) {
			case PEACEFUL:
				this.serverProperties.setProperty("difficulty", "0");
				break;
			case EASY:
				this.serverProperties.setProperty("difficulty", "1");
				break;
			case NORMAL:
				this.serverProperties.setProperty("difficulty", "2");
				break;
			case HARD:
				this.serverProperties.setProperty("difficulty", "3");
				break;
			}
			this.serverProperties.setProperty("enable-command-block", this.isEnabledCommandBlock() ? "true" : "false");
			switch (this.getGameMode()) {
			case SURVIVAL:
				this.serverProperties.setProperty("gamemode", "0");
				break;
			case CREATIVE:
				this.serverProperties.setProperty("gamemode", "1");
				break;
			case HARDCORE:
				this.serverProperties.setProperty("gamemode", "2");
				break;
			case VISITOR:
				this.serverProperties.setProperty("gamemode", "3");
				break;
			}
			this.serverProperties.setProperty("player-idle-timeout", String.valueOf(this.getPlayerIdleTime()));
			this.serverProperties.setProperty("max-players", String.valueOf(this.getMaxPlayers()));
			this.serverProperties.setProperty("max-tick-time", String.valueOf(this.getMaxTickTime()));
			this.serverProperties.setProperty("spawn-monsters", this.isSpawnMonsters() ? "true" : "false");
			this.serverProperties.setProperty("generate-structures", this.isGenerateStructs() ? "true" : "false");
			this.serverProperties.setProperty("view-distance", String.valueOf(this.getViewDistance()));
			this.serverProperties.setProperty("motd", this.getMotd());
			this.serverProperties.store(output, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void load() {
		InputStream input = null;
		try {
			input = new FileInputStream(propertiesPath);
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
			this.setShowAchievements(
					serverProperties.getProperty("announce-player-achievements") == "true" ? true : false);
			this.setServerPort(Integer.parseInt(serverProperties.getProperty("server-port")));
			this.setMaxWorldSize(Integer.parseInt(serverProperties.getProperty("max-world-size")));
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
			this.setNetworkCompression(Integer.parseInt(serverProperties.getProperty("network-compression-threshold")));
			this.setMaxBuildHeight(Integer.parseInt(serverProperties.getProperty("max-build-height")));
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
			this.setPlayerIdleTime(Integer.parseInt(serverProperties.getProperty("player-idle-timeout")));
			this.setMaxPlayers(Integer.parseInt(serverProperties.getProperty("max-players")));
			this.setMaxTickTime(Integer.parseInt(serverProperties.getProperty("max-tick-time")));
			this.setSpawnMonsters(serverProperties.getProperty("spawn-monsters") == "true" ? true : false);
			this.setGenerateStructs(serverProperties.getProperty("generate-structures") == "true" ? true : false);
			this.setViewDistance(Integer.parseInt(serverProperties.getProperty("view-distance")));
			this.setMotd(serverProperties.getProperty("motd"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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