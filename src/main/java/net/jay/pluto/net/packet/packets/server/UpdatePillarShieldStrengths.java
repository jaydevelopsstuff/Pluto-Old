package net.jay.pluto.net.packet.packets.server;

import lombok.AllArgsConstructor;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.packet.SPacket;

@AllArgsConstructor
public class UpdatePillarShieldStrengths implements SPacket {
    private static final Packets enumRepresentation = Packets.UpdateShieldStrengths;

    public int solarStrength;
    public int vortexStrength;
    public int nebulaStrength;
    public int stardustStrength;

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeShort((short)solarStrength);
        buffer.writeShort((short)vortexStrength);
        buffer.writeShort((short)nebulaStrength);
        buffer.writeShort((short)stardustStrength);
        return buffer.toNormal();
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
    public Packets getEnum() {
        return enumRepresentation;
    }
}
