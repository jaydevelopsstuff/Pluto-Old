package net.jay.pluto.net.packets;

import net.jay.pluto.localization.NetworkText;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;

public class DisconnectClient implements Packet {
    private static final Packets enumRepresentation = Packets.Disconnect;

    private NetworkText reason;

    public DisconnectClient(NetworkText reason) {
        this.reason = reason;
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {

    }

    @Override
    public PacketBuffer writePacketData() {
        return reason.serialize(new PacketBuffer(256));
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer = reason.serialize(buffer);
        return buffer;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
