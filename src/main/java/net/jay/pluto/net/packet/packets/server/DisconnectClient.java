package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.localization.NetworkText;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;

public class DisconnectClient implements SPacket {
    private static final Packets enumRepresentation = Packets.Disconnect;

    private NetworkText reason;

    public DisconnectClient() {

    }

    public DisconnectClient(NetworkText reason) {
        this.reason = reason;
    }

    public void setPacketData(NetworkText reason) {
        this.reason = reason;
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
