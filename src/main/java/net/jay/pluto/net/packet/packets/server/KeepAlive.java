package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;

public class KeepAlive implements SPacket {
    private static final Packets enumRepresentation = Packets.KeepAlive;
    private static final int maxPacketDataSize = 0;

    @Override
    public PacketBuffer writePacketData() {
        return new PacketBuffer(0);
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
