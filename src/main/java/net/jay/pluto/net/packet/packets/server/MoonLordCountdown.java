package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;

public class MoonLordCountdown implements SPacket {
    private static final Packets enumRepresentation = Packets.MoonLordCountdown;
    private static final int maxPacketDataSize = 4;

    public int moonLordCountdown;

    public MoonLordCountdown(int moonLordCountdown) {
        this.moonLordCountdown = moonLordCountdown;
    }

    @Override
    public PacketBuffer writePacketData() {
        PacketBuffer buffer = new PacketBuffer(maxPacketDataSize);
        buffer.writeInt(moonLordCountdown);
        return buffer;
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeInt(moonLordCountdown);
        return buffer;
    }

    @Override
    public int getMaxPacketDataSize() {
        return maxPacketDataSize;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
