package net.jay.pluto.net;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetManager {
    private TCPServerManager tcpServer;
    private final List<Socket> connectedSockets = new ArrayList<>();

    public void startServer() throws IOException {
        tcpServer = new TCPServerManager(7777);
    }


}
