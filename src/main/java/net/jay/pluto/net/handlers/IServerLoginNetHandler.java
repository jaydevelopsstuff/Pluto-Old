package net.jay.pluto.net.handlers;

import net.jay.pluto.net.packet.packets.both.PlayerInfo;
import net.jay.pluto.net.packet.packets.client.ConnectRequest;
import net.jay.pluto.net.packet.packets.client.PasswordSend;
import net.jay.pluto.net.packet.packets.client.RequestWorldData;

public interface IServerLoginNetHandler extends NetHandler {
    void processConnectRequest(ConnectRequest packet);

    void processPasswordSend(PasswordSend packet);

    void processPlayerInfo(PlayerInfo packet);

    void processRequestWorldData(RequestWorldData packet);
}
