package net.jay.pluto.net.packet;

import net.jay.pluto.net.PacketBuffer;

public interface SPacket extends Packet {
    PacketBuffer writePacketData();

    PacketBuffer writePacketData(PacketBuffer buffer);
}
