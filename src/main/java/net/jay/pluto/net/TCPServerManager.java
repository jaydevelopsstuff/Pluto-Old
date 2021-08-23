package net.jay.pluto.net;

import net.jay.pluto.localization.NetworkText;
import net.jay.pluto.net.packet.packets.server.DisconnectClient;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TCPServerManager {
    private final int port;

    private TServerSocket server;

    private final Thread listenerThread;
    private final Executor executor;

    public TCPServerManager(int port) throws IOException {
        this.port = port;
        this.executor = Executors.newSingleThreadExecutor();

        listenerThread = new Thread(this::startServerAndListen, "Connect Listener Thread");
        listenerThread.start();

    }

    private void startServerAndListen() {
        try {
            server = new TServerSocket(port);

            while(!listenerThread.isInterrupted()) {
                PlayerSocket connectedSocket = (PlayerSocket)server.accept();
                connectedSocket.init();
                System.out.println("Somebody connected");

                executor.execute(() -> {
                    try {
                        connectedSocket.sendPacket(new DisconnectClient(new NetworkText("Haha fuck you", NetworkText.Mode.LITERAL)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
