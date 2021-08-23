package net.jay.pluto.net.packet;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.handlers.NetHandler;

public interface CPacket<T extends NetHandler> extends Packet {
    void readPacketData(PacketBuffer buffer);

    void processPacket(T handler);
}
