package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;

public class ContinueConnecting implements SPacket {
    private static final Packets enumRepresentation = Packets.ContinueConnecting;

    private byte userSlot;

    public ContinueConnecting() {

    }

    public ContinueConnecting(byte userSlot) {
        this.userSlot = userSlot;
    }

    public void setPacketData(byte userSlot) {
        this.userSlot = userSlot;
    }

    @Override
    public PacketBuffer writePacketData() {
        PacketBuffer buffer = new PacketBuffer(1);
        buffer.writeByte(userSlot);
        return buffer;
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeByte(userSlot);
        return buffer;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
