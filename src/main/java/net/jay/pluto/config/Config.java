package net.jay.pluto.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private String worldName = DefaultConfig.worldName;
    private int port = DefaultConfig.port;
    private int maxPlayerCount = DefaultConfig.maxPlayerCount;

    public Config(String configFile) throws BadConfigException {
        this.fromFile(configFile);
    }

    public Config(File configFile) throws BadConfigException {
        this.fromFile(configFile);
    }

    public Config(String worldName, int port, int maxPlayerCount) {
        this.worldName = worldName;
        this.port = port;
        this.maxPlayerCount = maxPlayerCount;
    }

    public void fromFile(String file) throws BadConfigException {
        fromFile(new File(file));
    }

    public void fromFile(File file) throws BadConfigException {
        if(!file.getName().endsWith(".properties")) throw new IllegalArgumentException("File must be a properties file");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        } catch(IOException e) {
            if(e instanceof FileNotFoundException) throw new BadConfigException("Invalid file");
            throw new BadConfigException("Was unable to read config");
        }
        fromProperties(properties);
    }

    public void fromProperties(Properties properties) throws BadConfigException {
        worldName = properties.getProperty("worldname", DefaultConfig.worldName);
        try {
            port = Integer.parseInt(properties.getProperty("port", String.valueOf(DefaultConfig.port)));

            if(port < 0 || port > 65535) throw new BadConfigException("port is not in the valid range (0 - 65535)");
        } catch(NumberFormatException ignored) {
            throw new BadConfigException("invalid port");
        }
        try {
            maxPlayerCount = Integer.parseInt(properties.getProperty("maxplayercount", String.valueOf(DefaultConfig.maxPlayerCount)));

            if(maxPlayerCount < 1 || maxPlayerCount > 255) throw new BadConfigException("player count must be from 1 to 255");
        } catch(NumberFormatException ignored) {
            throw new BadConfigException("invalid max player count");
        }
    }
}
