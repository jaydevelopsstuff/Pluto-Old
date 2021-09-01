package net.jay.pluto.managers;

import net.jay.pluto.net.Client;
import net.jay.pluto.net.TCPServerManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages all things pertaining to lower level network logic
 * @author Jay
 */
public class NetManager implements Manager {
    /** The TCP server that manages lower level connection logic */
    private TCPServerManager tcpServer;
    /** All the clients that are connected to the server */
    private final List<Client> connectedClients = new CopyOnWriteArrayList<>();

    public void startListening() {
        tcpServer = new TCPServerManager(7777);
    }

    public void trackClient(Client client) {
        connectedClients.add(client);
    }

    public void removeClient(Client client) {
        connectedClients.remove(client);
    }

    @Deprecated
    public void updateClientIDs(int removedPlayerID) {
        for(Client client : connectedClients) {
            //if(client.getClientID() > removedPlayerID) client.setClientID(client.getClientID() - 1);
        }
    }

    @Override
    public void shutdown() {
        try {
            tcpServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getConnectedClients() {
        return connectedClients;
    }
}
