package net.jay.pluto.net.packet.packets.both;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.packet.MultipleHandlersBothPacket;

public class PlayerMana implements MultipleHandlersBothPacket<IServerLoginNetHandler, IServerPlayNetHandler> {
    private static final Packets enumRepresentation = Packets.PlayerMana;
    private static final int maxPacketDataSize = 5;

    public short playerID;
    public short mana;
    public short maxMana;

    public PlayerMana(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    public PlayerMana(short playerID, short mana, short maxMana) {
        this.playerID = playerID;
        this.mana = mana;
        this.maxMana = maxMana;
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        this.playerID = buffer.readUnsignedByte();
        this.mana = buffer.readShort();
        this.maxMana = buffer.readShort();
    }

    @Override
    public PacketBuffer writePacketData() {
        PacketBuffer buffer = new PacketBuffer(maxPacketDataSize);
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(mana);
        buffer.writeShort(maxMana);
        return buffer;
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        if(buffer.getAllocation() < maxPacketDataSize) throw new IllegalArgumentException("Buffer must have a length of at least " + maxPacketDataSize);
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
    public int getMaxPacketDataSize() {
        return maxPacketDataSize;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
