package net.jay.pluto.net.packets;

import net.jay.pluto.net.PacketBuffer;

public interface Packet {
    void readPacketData(PacketBuffer buffer);

    void writePacketData(PacketBuffer buffer);
}
