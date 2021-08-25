package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;

public class PasswordRequired implements SPacket {
    private final Packets enumRepresentation = Packets.PasswordRequired;

    @Override
    public PacketBuffer writePacketData() {
        // Nothing to write
        return null;
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        // Nothing to write
        return buffer;
    }

    @Override
    public int getMaxPacketDataSize() {
        // Nothing is ever written
        return 0;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
