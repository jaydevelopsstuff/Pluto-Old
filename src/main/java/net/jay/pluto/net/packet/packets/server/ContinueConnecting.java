package net.jay.pluto.net.packet.packets.server;

import lombok.AllArgsConstructor;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.packet.SPacket;

@AllArgsConstructor
public class ContinueConnecting implements SPacket {
    private static final Packets enumRepresentation = Packets.ContinueConnecting;

    public short userSlot;

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeUnsignedByte(userSlot);
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeUnsignedByte(userSlot);
        return buffer;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
