package net.jay.pluto.net.handlers;

import net.jay.pluto.net.packet.packets.both.*;
import net.jay.pluto.net.packet.packets.client.*;

public interface IServerLoginNetHandler extends NetHandler {
    void processConnectRequest(ConnectRequest packet);

    void processPasswordSend(PasswordSend packet);

    void processPlayerInfo(PlayerInfo packet);

    void processClientUUID(ClientUUID packet);

    void processPlayerHP(PlayerHP packet);

    void processManaEffect(ManaEffect packet);

    void processPlayerBuff(PlayerBuff packet);

    void processPlayerSlot(PlayerSlot packet);

    void processRequestWorldData(RequestWorldData packet);

    void processRequestEssentialTiles(RequestEssentialTiles packet);
}
