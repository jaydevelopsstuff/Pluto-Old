package net.jay.pluto;

import net.jay.pluto.managers.*;
import net.jay.pluto.net.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class PlutoServer {
    /** The instance for the running PlutoServer */
    private static PlutoServer instance;

    private final boolean[] playerIDs = new boolean[256];

    private final Logger logger = LogManager.getLogger("Pluto");

    /** Manages the server's file interactions */
    private FileManager fileManager;
    /** Manages server configurations */
    private ConfigManager configManager;
    /** Manages all connected clients, lower level TCP communication */
    private NetManager netManager;
    /** Manages/tracks all connected players */
    private PlayerManager playerManager;
    /** Manages listeners that wait for conditions to be reached */
    private ListeningManager listeningManager;

    /** Creates a new <code>PlutoServer</code>, this class should never be initialized more than once */
    public PlutoServer() {
        // Set instance
        instance = this;
        Arrays.fill(playerIDs, true);
    }

    /** Starts the server */
    public void start() {
        logger.info("Starting server");
        fileManager = new FileManager();
        configManager = new ConfigManager();
        netManager = new NetManager();
        playerManager = new PlayerManager(255);
        listeningManager = new ListeningManager();

        netManager.startListening();
        listeningManager.startClientListening();
    }

    /**
     * Adds a client to the <code>NetManager</code>'s list of clients to keep track of (listen for packets, etc.)
     * @param client The client to start tracking
     */
    public void addClient(Client client) {
        netManager.trackClient(client);
    }

    /** Gets the next player ID without marking it as used */
    public int peekNextPlayerID() {
        for(int i = 1; i < playerIDs.length; i++)
            if(playerIDs[i]) return i;
        // No ID is available (bruh)
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
        // No ID is available (bruh)
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

    public Logger getLogger() {
        return logger;
    }

    public NetManager getNetManager() {
        return netManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static PlutoServer getInstance() {
        return instance;
    }
}
