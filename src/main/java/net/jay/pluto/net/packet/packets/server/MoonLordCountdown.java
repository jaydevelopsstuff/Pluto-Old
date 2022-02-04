package net.jay.pluto.net.packet.packets.server;

import lombok.AllArgsConstructor;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.packet.SPacket;

@AllArgsConstructor
public class MoonLordCountdown implements SPacket {
    private static final Packets enumRepresentation = Packets.MoonLordCountdown;

    public int moonLordCountdown;

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeInt(moonLordCountdown);
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeInt(moonLordCountdown);
        return buffer;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
