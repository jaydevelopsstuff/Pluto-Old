package net.jay.pluto.net.packet.packets.both;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.packet.MultipleHandlersBothPacket;

public class PlayerHP implements MultipleHandlersBothPacket<IServerLoginNetHandler, IServerPlayNetHandler> {
    private static final Packets enumRepresentation = Packets.PlayerHp;
    private static final int maxPacketDataSize = 5;

    public short playerID;
    public short HP;
    public short maxHP;

    public PlayerHP(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    public PlayerHP(short playerID, short HP, short maxHP) {
        this.playerID = playerID;
        this.HP = HP;
        this.maxHP = maxHP;
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        playerID = buffer.readUnsignedByte();
        HP = buffer.readShort();
        maxHP = buffer.readShort();
    }

    @Override
    public PacketBuffer writePacketData() {
        PacketBuffer buffer = new PacketBuffer(maxPacketDataSize);
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(HP);
        buffer.writeShort(maxHP);
        return buffer;
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        if(buffer.getAllocation() < maxPacketDataSize) throw new IllegalArgumentException("Buffer must have a length of at least " + maxPacketDataSize);
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(HP);
        buffer.writeShort(maxHP);
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
    public int getMaxPacketDataSize() {
        return maxPacketDataSize;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
