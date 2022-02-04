package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.data.NetworkText;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.packet.SPacket;

public class Status implements SPacket {
    private static final Packets enumRepresentation = Packets.Status;

    public int statusMax;
    public NetworkText text;

    public Status(int statusMax, NetworkText text) {
        this.statusMax = statusMax;
        this.text = text;
    }

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeInt(statusMax);
        buffer = text.serialize(buffer);
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeInt(statusMax);
        buffer = text.serialize(buffer);
        return buffer;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
