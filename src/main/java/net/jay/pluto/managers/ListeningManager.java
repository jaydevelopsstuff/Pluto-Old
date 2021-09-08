package net.jay.pluto.managers;

import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.entity.player.ManageablePlayer;
import net.jay.pluto.net.Client;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

// Maybe move keep alive & packet reading loops to net manager?
public class ListeningManager implements Manager, Access {
    private final Thread[] clientListeningThreads = new Thread[2];
    private final Timer taskScheduler;

    public ListeningManager() {
        this.taskScheduler = new Timer("Keep Alive");
    }

    @Override
    public void initialize() {

    }

    public void startClientListening() {
        Thread thread = new Thread(this::startClientPacketListenerThreaded, "Client Packet Listener");
        clientListeningThreads[0] = thread;
        thread.start();
    }

    public void startKeepAliveLoop() {
        taskScheduler.schedule(new TimerTask() {
            @Override
            public void run() {
                for(ManageablePlayer player : server.getPlayerManager().getConnectedPlayers()) {
                    try {
                        player.getConnectedClient().sendKeepAlive();
                    } catch(IOException e) {
                        try {
                            player.disconnect();
                        } catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }, 0, 15000);
    }

    private void startClientPacketListenerThreaded() {
        while(!Thread.currentThread().isInterrupted()) {
            for(Client client : server.getNetManager().getConnectedClients()) {
                try {
                    // Check for any packets sent from client
                    client.readPacket();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                // This is for performance
                Thread.sleep(1);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutdown() {
        clientListeningThreads[0].interrupt();
        if(clientListeningThreads[1] != null) clientListeningThreads[1].interrupt();
    }
}
