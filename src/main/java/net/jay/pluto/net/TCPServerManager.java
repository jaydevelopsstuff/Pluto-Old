package net.jay.pluto.net;

import net.jay.pluto.net.packets.ConnectRequest;
import net.jay.pluto.util.TerrariaReader;
import net.jay.pluto.util.TerrariaWriter;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
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
                System.out.println("Somebody connected");

                TerrariaReader reader = new TerrariaReader(connectedSocket.getInputStream());
                TerrariaWriter writer = new TerrariaWriter(connectedSocket.getOutputStream());

                executor.execute(() -> {
                    try {
                        System.out.println(Arrays.toString(reader.readNBytes(reader.available())));
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
