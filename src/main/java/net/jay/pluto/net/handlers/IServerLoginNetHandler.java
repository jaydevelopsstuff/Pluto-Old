package net.jay.pluto.net.handlers;

import net.jay.pluto.net.packet.packets.client.ConnectRequest;

public interface IServerLoginNetHandler extends NetHandler {
    void processConnectRequest(ConnectRequest request);
}
