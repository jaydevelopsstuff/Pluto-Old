package net.jay.pluto.net.packets;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;

public class ConnectRequest implements Packet {
    private static final Packets enumRepresentation = Packets.ConnectRequest;

    private String version;

    @Override
    public void readPacketData(PacketBuffer buffer) {
        buffer.readByte();
        version = buffer.readString();
        System.out.println(version);
    }

    @Override
    public void writePacketData(PacketBuffer buffer) {

    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
