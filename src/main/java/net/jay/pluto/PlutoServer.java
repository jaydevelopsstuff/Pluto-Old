package net.jay.pluto;

import lombok.Getter;
import net.jay.pluto.managers.*;
import net.jay.pluto.net.Client;
import net.jay.pluto.world.World;
import net.jay.pluto.world.loading.ReLogicWorldLoader;
import net.jay.pluto.world.loading.WorldLoadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

public class PlutoServer {
    /** The instance for the running PlutoServer */
    @Getter
    private static PlutoServer instance;

    private final boolean[] playerIDs = new boolean[256];

    private final Logger logger = LogManager.getLogger("Pluto");

    /** The current loaded world */
    @Getter
    private World world;

    /** Manages the server's file interactions */
    @Getter
    private FileManager fileManager;
    /** Manages server configurations */
    @Getter
    private ConfigManager configManager;
    /** Manages all connected clients, lower level TCP communication */
    @Getter
    private NetManager netManager;
    /** Manages/tracks all connected players */
    @Getter
    private PlayerManager playerManager;
    /** Manages listeners that wait for conditions to be reached */
    @Getter
    private ListeningManager listeningManager;
    /** Manages command receiving, parsing, and handling */
    @Getter
    private CommandManager commandManager;
    @Getter
    private ConsoleManager consoleManager;

    /** Creates a new {@link PlutoServer}, this class should never be initialized more than once during runtime */
    public PlutoServer() {
        // Set instance
        instance = this;
        Arrays.fill(playerIDs, true);
    }

    /** Starts the server */
    public void start() {
        // Has to be initialized before others
        fileManager = new FileManager();

        clearOldLogs();
        logger.debug("Cleared old logs");


        logger.info("Starting server...");


        logger.info("Setting up managers");

        logger.debug("Setting up Config Manager");
        configManager = new ConfigManager();
        logger.debug("Setting up Net Manager");
        netManager = new NetManager(7777);
        logger.debug("Setting up Player Manager");
        playerManager = new PlayerManager(255);
        logger.debug("Setting up Listening Manager");
        listeningManager = new ListeningManager();
        logger.debug("Setting up Command Manager");
        commandManager = new CommandManager();
        logger.debug("Setting up Console Manager");
        consoleManager = new ConsoleManager();

        logger.info("Finished setting up managers");


        logger.info("Initializing managers");

        logger.debug("Initializing Config Manager");
        configManager.initialize();
        logger.debug("Initializing Net Manager");
        netManager.initialize();
        logger.debug("Initializing Player Manager");
        playerManager.initialize();
        logger.debug("Initializing Listening Manager");
        listeningManager.initialize();
        logger.debug("Initializing Command Manager");
        commandManager.initialize();
        logger.debug("Initializing Console Manager");
        consoleManager.initialize();

        logger.info("Finished initializing managers");

        try {
            ReLogicWorldLoader reLogicWorldLoader = new ReLogicWorldLoader("world.wld");
            reLogicWorldLoader.loadWorld();
            world = reLogicWorldLoader.build();
        } catch (WorldLoadingException | IOException e) {
            e.printStackTrace();
        }

        logger.debug("Starting TCP listening");
        netManager.startListening();
        logger.debug("Starting client packet listening");
        listeningManager.startClientListening();
        logger.debug("Starting keep alive loop");
        listeningManager.startKeepAliveLoop();

        logger.info("Server started");
    }

    /** Shuts down the server */
    public void shutdown() {
        logger.info("Shutting down server...");

        logger.info("Shutting down managers");
        listeningManager.shutdown();
        logger.debug("Listening Manager shut down");
        playerManager.shutdown();
        logger.debug("Player Manager shut down");
        netManager.shutdown();
        logger.debug("Net Manager shut down");
        configManager.shutdown();
        logger.debug("File Manager shut down");
        fileManager.shutdown();

        logger.info("Server shut down");
    }

    public void clearOldLogs() {
        fileManager.clear("logs/latest.log", (success) -> {
            if(!success) logger.warn("Was unable to clear latest.log");
        });
        fileManager.clear("logs/debug.log", (success) -> {
            if(!success) logger.warn("Was unable to clear debug.log");
        });
    }

    /** Gets the next available player ID without marking it as used */
    public int peekNextAvailablePlayerID() {
        for(int i = 1; i < playerIDs.length; i++)
            if(playerIDs[i]) return i;
        // No ID is available
        return -1;
    }

    /** Takes a player ID for use (e.g. a new client connecting) */
    public int usePlayerID() {
        for(int i = 0; i < playerIDs.length; i++) {
            if(playerIDs[i]) {
                playerIDs[i] = false;
                return i;
            }
        }
        // No ID is available
        return -1;
    }

    /**
     * Frees up the specified player ID for later use
     * @param ID The ID to free
     */
    public void freePlayerID(int ID) {
        for(int i = 0; i < playerIDs.length; i++) {
            if(i == ID) playerIDs[i] = true;
        }
    }

    public boolean isServerFull() {
        return playerManager.getOpenPlayerSlots() <= 0;
    }
}
