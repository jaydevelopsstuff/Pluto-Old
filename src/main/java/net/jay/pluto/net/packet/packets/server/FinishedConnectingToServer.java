package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;

public class FinishedConnectingToServer implements SPacket {
    private static final Packets enumRepresentation = Packets.FinishedConnectingToServer;

    @Override
    public PacketBuffer writePacketData() {
        return new PacketBuffer(0);
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        return buffer;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
