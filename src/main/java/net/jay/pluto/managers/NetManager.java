package net.jay.pluto.managers;

import lombok.Getter;
import net.jay.pluto.net.Client;
import net.jay.pluto.net.TCPServerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages all things pertaining to lower level network logic
 * @author Jay
 */
public class NetManager implements Manager {
    @Getter
    private static final Logger logger = LogManager.getLogger("Network");

    private final int port;

    /** The TCP server that manages lower level connection logic */
    private TCPServerManager tcpServer;
    /** All the clients that are connected to the server */
    @Getter
    private final List<Client> connectedClients = new CopyOnWriteArrayList<>();

    public NetManager(int port) {
        this.port = port;
    }

    @Override
    public void initialize() {

    }

    public void startListening() {
        tcpServer = new TCPServerManager(port);
        logger.info("Started listening on port " + port);
    }

    /**
     * Begins tracking the provided client.
     * @param client The {@link Client} to track
     */
    public void trackClient(Client client) {
        logger.info(client.getIP() + " is connecting");
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
}
