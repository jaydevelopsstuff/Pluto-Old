package net.jay.pluto.net.packets;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;

public interface Packet {
    void readPacketData(PacketBuffer buffer);

    void writePacketData(PacketBuffer buffer);

    Packets getEnum();
}
