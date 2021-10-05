package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;

public class SectionsFrame implements SPacket {
    private static final Packets enumRepresentation = Packets.SectionTileFrame;
    private static final int maxPacketDataSize = 8;

    public short startX;
    public short startY;
    public short endX;
    public short endY;

    public SectionsFrame(short startX, short startY, short endX, short endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public PacketBuffer writePacketData() {
        PacketBuffer buffer = new PacketBuffer(maxPacketDataSize);
        buffer.writeShort(startX);
        buffer.writeShort(startY);
        buffer.writeShort(endX);
        buffer.writeShort(endY);
        return buffer;
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeShort(startX);
        buffer.writeShort(startY);
        buffer.writeShort(endX);
        buffer.writeShort(endY);
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
