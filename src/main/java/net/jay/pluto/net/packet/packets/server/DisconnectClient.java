package net.jay.pluto.net.packet.packets.server;

import lombok.AllArgsConstructor;
import net.jay.pluto.data.NetworkText;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;


@AllArgsConstructor
public class DisconnectClient implements SPacket {
    private static final Packets enumRepresentation = Packets.Disconnect;

    public NetworkText reason;

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
