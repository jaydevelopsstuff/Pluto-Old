package net.jay.pluto.managers;

import net.jay.pluto.net.Client;
import net.jay.pluto.net.TCPServerManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NetManager implements Manager {
    private TCPServerManager tcpServer;
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
