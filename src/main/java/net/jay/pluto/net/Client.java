package net.jay.pluto.net;

import lombok.Getter;
import lombok.Setter;
import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.entity.player.BasicPlayer;
import net.jay.pluto.net.handlers.NetHandler;
import net.jay.pluto.net.packet.*;
import net.jay.pluto.net.packet.packets.server.KeepAlive;

import java.io.IOException;

@Getter
public class Client implements Access {
    /** The ID of this client, if the client completes login it will be passed to the player object */
    private final int clientID;

    @Setter
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

    public void sendKeepAlive() throws IOException {
        socket.sendPacket(new KeepAlive());
    }

    public void readPacket() throws IOException {
        Packet packet = socket.readPacket();
        if(packet == null) return;

        if(packet instanceof CPacket<?>) connectionManager.passOnPacket((CPacket<NetHandler>)packet);
        else if(packet instanceof MultipleHandlersCPacket<?, ?>) connectionManager.passOnPacket((MultipleHandlersCPacket<NetHandler, NetHandler>)packet);
    }

    public void sendPacket(SPacket packet) {
        try {
            socket.sendPacket(packet);
        } catch (IOException e) {
            if(e.getMessage().toLowerCase().contains("socket closed")) disconnect("Client disconnected");
            e.printStackTrace();
        }
    }

    public void queuePacket(SPacket packet) {
        socket.queuePacket(packet);
    }

    public void flushPacketQueue() {
        try {
            socket.flushPacketQueue();
        } catch (IOException e) {
            if(e.getMessage().toLowerCase().contains("socket closed")) disconnect("Client disconnected");
            e.printStackTrace();
        }
    }

    public void boot(String reason) {
        disconnect(reason);
    }

    public void disconnect(String reason) {
        try {
            socket.disconnectGracefully(reason);
        } catch (IOException e) {
            e.printStackTrace();
        }
        connectionManager.getNetHandler().handleDisconnect();
        server.getNetManager().removeClient(this);
    }

    public void boot() {
        disconnect();
    }

    public void disconnect() {
        try {
            socket.disconnectGracefully();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connectionManager.getNetHandler().handleDisconnect();
        server.getNetManager().removeClient(this);
    }

    public int getClientID() {
        return clientID;
    }

    public String getIP() {
        String address = socket.getRemoteSocketAddress().toString();
        address = address.replace("/", "").substring(0, address.indexOf(":") - 1);
        return address;
    }
}
