package net.jay.pluto.net.packet.packets.both;

import lombok.AllArgsConstructor;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.packet.MultipleHandlersBothPacket;

@AllArgsConstructor
public class PlayerHP implements MultipleHandlersBothPacket<IServerLoginNetHandler, IServerPlayNetHandler> {
    private static final Packets enumRepresentation = Packets.PlayerHp;

    public short playerID;
    public short hp;
    public short maxHp;

    public PlayerHP(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        playerID = buffer.readUnsignedByte();
        hp = buffer.readShort();
        maxHp = buffer.readShort();
    }

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(hp);
        buffer.writeShort(maxHp);
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(hp);
        buffer.writeShort(maxHp);
        return buffer;
    }

    @Override
    public void processPacketLogin(IServerLoginNetHandler handler) {
        handler.processPlayerHP(this);
    }

    @Override
    public void processPacketPlay(IServerPlayNetHandler handler) {

    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
