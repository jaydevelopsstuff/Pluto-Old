package net.jay.pluto.net.packet.packets.client;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.packet.CPacket;

import java.util.UUID;

public class ClientUUID implements CPacket<IServerLoginNetHandler> {
    private static final Packets enumRepresentation = Packets.ClientUUID;

    public UUID uuid;

    public ClientUUID(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        uuid = UUID.fromString(buffer.readString());
    }

    @Override
    public void processPacket(IServerLoginNetHandler handler) {
        handler.processClientUUID(this);
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
