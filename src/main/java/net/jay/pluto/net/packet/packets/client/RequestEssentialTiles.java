package net.jay.pluto.net.packet.packets.client;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.packet.CPacket;

public class RequestEssentialTiles implements CPacket<IServerLoginNetHandler> {
    private static final Packets enumRepresentation = Packets.RequestEssentialTiles;

    public int spawnX;
    public int spawnY;

    public RequestEssentialTiles(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        spawnX = buffer.readInt();
        spawnY = buffer.readInt();
    }

    @Override
    public void processPacket(IServerLoginNetHandler handler) {
        handler.processRequestEssentialTiles(this);
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
