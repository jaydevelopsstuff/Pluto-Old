package net.jay.pluto;

import net.jay.pluto.managers.ListeningManager;
import net.jay.pluto.managers.PlayerManager;
import net.jay.pluto.managers.NetManager;
import net.jay.pluto.net.Client;

public class PlutoServer {
    /** The instance for the running PlutoServer */
    private static PlutoServer instance;

    private int nextPlayerID = 1;

    // TODO Add password support
    private final boolean hasPassword = false;
    private final String password = null;

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
    }

    /** Starts the server */
    public void start() {
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

    /** Gets the next player ID without advancing it */
    public int peekNextPlayerID() {
        return nextPlayerID;
    }

    /** Takes a player ID for use (e.g. a new client connecting) */
    public int usePlayerID() {
        int usedPlayerID = nextPlayerID;
        nextPlayerID++;
        return usedPlayerID;
    }

    /**
     * Frees up the specified player ID and updates all old Player IDs
     * @param ID The ID to free
     */
    public void freePlayerID(int ID) {
        nextPlayerID--;
        netManager.updateClientIDs(ID);
        playerManager.updatePlayerIDs(ID);
    }

    public boolean isServerFull() {
        return playerManager.getOpenPlayerSlots() <= 0;
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
