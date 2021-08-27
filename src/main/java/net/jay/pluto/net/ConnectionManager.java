package net.jay.pluto.net;

import net.jay.pluto.entity.Player;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.handlers.NetHandler;
import net.jay.pluto.net.handlers.ServerLoginNetHandler;
import net.jay.pluto.net.packet.CPacket;
import net.jay.pluto.net.packet.MultipleHandlersCPacket;

public class ConnectionManager {
    private final Client parent;
    private final ClientSocket connection;

    private boolean isLoggedIn;
    private final Player player;

    private NetHandler netHandler;

    public ConnectionManager(ClientSocket connection, Client parent) {
        this.connection = connection;
        this.parent = parent;
        this.isLoggedIn = false;
        this.player = null;
        netHandler = new ServerLoginNetHandler(parent);
    }

    /**
     * Initializes a new <code>ConnectionManager</code> for a client that has already logged in
     * @param parent The <code>Client</code> that manages this user
     * @param connection The socket connection to this client
     * @param player The <code>Player</code> object for this client
     */
    public ConnectionManager(ClientSocket connection, Player player, Client parent) {
        this.parent = parent;
        this.connection = connection;
        this.isLoggedIn = true;
        this.player = player;
    }

    public void setNetHandler(NetHandler netHandler) {
        if(!(netHandler instanceof IServerLoginNetHandler) || !(netHandler instanceof IServerPlayNetHandler)) throw new IllegalArgumentException("NetHandler must implement IServerLoginNetHandler or IServerPlayNetHandler");
        this.netHandler = netHandler;
    }

    public void passOnPacket(CPacket<NetHandler> packet) {
        packet.processPacket(netHandler);
    }

    public void passOnPacket(MultipleHandlersCPacket<NetHandler, NetHandler> packet) {
        if(netHandler instanceof IServerLoginNetHandler) packet.processPacketLogin(netHandler);
        else if(netHandler instanceof IServerPlayNetHandler) packet.processPacketPlay(netHandler);
    }

    public NetHandler getNetHandler() {
        return netHandler;
    }
}
