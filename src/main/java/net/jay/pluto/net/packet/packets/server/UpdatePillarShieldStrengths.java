package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;

public class UpdatePillarShieldStrengths implements SPacket {
    private static final Packets enumRepresentation = Packets.UpdateShieldStrengths;
    private static final int maxPacketDataSize = 8;

    public int solarStrength;
    public int vortexStrength;
    public int nebulaStrength;
    public int stardustStrength;

    public UpdatePillarShieldStrengths(int solarStrength, int vortexStrength, int nebulaStrength, int stardustStrength) {
        this.solarStrength = solarStrength;
        this.vortexStrength = vortexStrength;
        this.nebulaStrength = nebulaStrength;
        this.stardustStrength = stardustStrength;
    }

    @Override
    public PacketBuffer writePacketData() {
        PacketBuffer buffer = new PacketBuffer(maxPacketDataSize);
        buffer.writeShort((short)solarStrength);
        buffer.writeShort((short)vortexStrength);
        buffer.writeShort((short)nebulaStrength);
        buffer.writeShort((short)stardustStrength);
        return buffer;
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeShort((short)solarStrength);
        buffer.writeShort((short)vortexStrength);
        buffer.writeShort((short)nebulaStrength);
        buffer.writeShort((short)stardustStrength);
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
