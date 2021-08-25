package net.jay.pluto.managers;

import net.jay.pluto.Access;
import net.jay.pluto.net.Client;

import java.io.IOException;

public class ListeningManager implements Manager, Access {
    private final Thread[] clientListeningThreads = new Thread[2];

    public void startClientListening() {
        Thread thread = new Thread(this::clientListener);
        clientListeningThreads[0] = thread;
        thread.start();
    }

    @Override
    public void shutdown() {
        clientListeningThreads[0].interrupt();
        if(clientListeningThreads[1] != null) clientListeningThreads[1].interrupt();
    }

    private void clientListener() {
        while(!Thread.currentThread().isInterrupted()) {
            for(Client client : server.getNetManager().getConnectedClients()) {
                try {
                    // Check for any packets sent from client
                    client.readPacket();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
