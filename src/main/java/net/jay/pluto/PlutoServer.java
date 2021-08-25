package net.jay.pluto;

import net.jay.pluto.managers.ListeningManager;
import net.jay.pluto.managers.PlayerManager;
import net.jay.pluto.managers.NetManager;
import net.jay.pluto.net.Client;

public class PlutoServer {
    private static PlutoServer instance;

    private int nextPlayerID = 1;

    // TODO Add password support
    private final boolean hasPassword = false;
    private final String password = null;

    private NetManager netManager;
    private PlayerManager playerManager;
    private ListeningManager listeningManager;

    public PlutoServer() {
        instance = this;
    }

    public void start() {
        netManager = new NetManager();
        playerManager = new PlayerManager(255);
        listeningManager = new ListeningManager();

        netManager.startListening();
        listeningManager.startClientListening();
    }

    public void addClient(Client client) {
        netManager.trackClient(client);
    }

    public int peekNextPlayerID() {
        return nextPlayerID;
    }

    public int usePlayerID() {
        int usedPlayerID = nextPlayerID;
        nextPlayerID++;
        return usedPlayerID;
    }

    public void freePlayerID(int ID) {
        nextPlayerID--;
        // TODO Finish the rest of this
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
