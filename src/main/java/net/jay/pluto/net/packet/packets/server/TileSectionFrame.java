package net.jay.pluto.net.packet.packets.server;

import lombok.AllArgsConstructor;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.packet.SPacket;

@AllArgsConstructor
public class TileSectionFrame implements SPacket {
    public short startX;
    public short startY;
    public short endX;
    public short endY;

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeShort(startX);
        buffer.writeShort(startY);
        buffer.writeShort(endX);
        buffer.writeShort(endY);
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        return null;
    }

    @Override
    public Packets getEnum() {
        return Packets.SectionTileFrame;
    }
}
