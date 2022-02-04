package net.jay.pluto.net.packet.packets.both;

import lombok.AllArgsConstructor;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.packet.MultipleHandlersBothPacket;

@AllArgsConstructor
public class PlayerMana implements MultipleHandlersBothPacket<IServerLoginNetHandler, IServerPlayNetHandler> {
    private static final Packets enumRepresentation = Packets.PlayerMana;

    public short playerID;
    public short mana;
    public short maxMana;

    public PlayerMana(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        this.playerID = buffer.readUnsignedByte();
        this.mana = buffer.readShort();
        this.maxMana = buffer.readShort();
    }

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(mana);
        buffer.writeShort(maxMana);
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(mana);
        buffer.writeShort(maxMana);
        return buffer;
    }

    @Override
    public void processPacketLogin(IServerLoginNetHandler handler) {

    }

    @Override
    public void processPacketPlay(IServerPlayNetHandler handler) {

    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
