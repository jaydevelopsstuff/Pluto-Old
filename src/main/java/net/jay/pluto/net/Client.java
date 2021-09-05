package net.jay.pluto.net;

import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.entity.player.BasicPlayer;
import net.jay.pluto.net.handlers.NetHandler;
import net.jay.pluto.net.packet.*;

import java.io.IOException;

public class Client implements Access {
    /** The ID of this client, if the client completes login it will be passed to the <code>Player</code> object */
    private final int clientID;

    private boolean loggedIn;

    /** The socket of this client, used for TCP communication */
    private final ClientSocket socket;
    private ConnectionManager connectionManager;

    public Client(int clientID, ClientSocket socket) {
        this.clientID = clientID;
        this.loggedIn = false;
        this.socket = socket;
        this.connectionManager = new ConnectionManager(socket, this);
    }

    public Client(int clientID, ClientSocket socket, BasicPlayer player) {
        this.clientID = clientID;
        this.loggedIn = true;
        this.socket = socket;
        this.connectionManager = new ConnectionManager(socket, player, this);
    }

    public void login(BasicPlayer player) {
        loggedIn = true;
        connectionManager = new ConnectionManager(socket, player, this);
    }

    public void readPacket() throws IOException {
        Packet packet = socket.readPacket();
        if(packet == null) return;

        if(packet instanceof CPacket<?>) connectionManager.passOnPacket((CPacket<NetHandler>)packet);
        else if(packet instanceof MultipleHandlersCPacket<?, ?>) connectionManager.passOnPacket((MultipleHandlersCPacket<NetHandler, NetHandler>)packet);
    }

    public void sendPacket(SPacket packet) throws IOException {
        socket.sendPacket(packet);
    }

    public void boot(String reason) throws IOException {
        disconnect(reason);
    }

    public void disconnect(String reason) throws IOException {
        socket.disconnectGracefully(reason);
        connectionManager.getNetHandler().handleDisconnect();
        server.getNetManager().removeClient(this);
    }

    public void boot() throws IOException {
        disconnect();
    }

    public void disconnect() throws IOException {
        socket.disconnectGracefully();
        connectionManager.getNetHandler().handleDisconnect();
        server.getNetManager().removeClient(this);
    }

    public int getClientID() {
        return clientID;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public ClientSocket getSocket() {
        return socket;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }
}
