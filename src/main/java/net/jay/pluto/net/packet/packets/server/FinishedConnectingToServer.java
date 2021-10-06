package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;

public class FinishedConnectingToServer implements SPacket {
    private static final Packets enumRepresentation = Packets.FinishedConnectingToServer;
    private static final int maxPacketDataSize = 0;

    @Override
    public PacketBuffer writePacketData() {
        return new PacketBuffer(maxPacketDataSize);
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        return buffer;
    }

    @Override
    public int getMaxPacketDataSize() {
        return maxPacketDataSize;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
