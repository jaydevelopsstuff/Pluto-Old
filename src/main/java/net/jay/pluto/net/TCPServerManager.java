package net.jay.pluto.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TCPServerManager {
    private final int port;

    private ServerSocket server;

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
            server = new ServerSocket(port);

            while(!listenerThread.isInterrupted()) {
                Socket connectedSocket = server.accept();

                executor.execute(() -> {
                    try {

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
