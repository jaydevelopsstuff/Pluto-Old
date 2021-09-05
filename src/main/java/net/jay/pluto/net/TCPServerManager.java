package net.jay.pluto.net;

import net.jay.pluto.data.interfaces.Access;

import java.io.IOException;

public class TCPServerManager implements Access {
    private final int port;

    private TServerSocket tcpServer;

    private final Thread listenerThread;

    public TCPServerManager(int port) {
        this.port = port;

        listenerThread = new Thread(this::startServerAndListen, "TCP Listener Thread");
        listenerThread.start();
    }

    private void startServerAndListen() {
        try {
            tcpServer = new TServerSocket(port);

            while(!listenerThread.isInterrupted()) {
                ClientSocket connectedSocket = (ClientSocket)tcpServer.accept();
                connectedSocket.init();
                System.out.println("Somebody connected");

                // Disconnect if the server is full, otherwise start tracking client
                if(server.isServerFull()) connectedSocket.disconnectGracefully("Server is full");
                else server.addClient(new Client(server.usePlayerID(), connectedSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shuts down the thread listening for new connections as well as closing the server. This method assumes all clients have already been kicked off the server
     * @throws IOException Thrown if <code>server.close()</code> throws an exception
     */
    public void shutdown() throws IOException {
        listenerThread.interrupt();
        tcpServer.close();
    }
}
