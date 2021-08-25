package net.jay.pluto.net.packet;

import net.jay.pluto.net.Packets;

public interface Packet {
    int maxStringLength = 256;

    Packets getEnum();
}
