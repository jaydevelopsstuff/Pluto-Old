package net.jay.pluto.net.packet.packets.both;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.packet.MultipleHandlersBothPacket;

// This might only be sent in login, if it is then I will be changing this
public class ManaEffect implements MultipleHandlersBothPacket<IServerLoginNetHandler, IServerPlayNetHandler> {
    private static final Packets enumRepresentation = Packets.ManaEffect;

    public short playerID;
    public short manaAmount;

    public ManaEffect(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        playerID = buffer.readUnsignedByte();
        manaAmount = buffer.readShort();
    }

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(manaAmount);
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(manaAmount);
        return buffer;
    }

    @Override
    public void processPacketLogin(IServerLoginNetHandler handler) {
        handler.processManaEffect(this);
    }

    @Override
    public void processPacketPlay(IServerPlayNetHandler handler) {

    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
