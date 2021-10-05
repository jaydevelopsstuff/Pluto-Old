package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;

public class UpdateGoodEvil implements SPacket {
    private static final Packets enumRepresentation = Packets.UpdateGoodEvil;
    private static final int maxPacketDataSize = 3;

    public byte totalGood;
    public byte totalEvil;
    public byte totalCrimson;

    public UpdateGoodEvil(byte totalGood, byte totalEvil, byte totalCrimson) {
        this.totalGood = totalGood;
        this.totalEvil = totalEvil;
        this.totalCrimson = totalCrimson;
    }

    @Override
    public PacketBuffer writePacketData() {
        PacketBuffer buffer = new PacketBuffer(maxPacketDataSize);
        buffer.writeByte(totalGood);
        buffer.writeByte(totalEvil);
        buffer.writeByte(totalCrimson);
        return buffer;
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeByte(totalGood);
        buffer.writeByte(totalEvil);
        buffer.writeByte(totalCrimson);
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
