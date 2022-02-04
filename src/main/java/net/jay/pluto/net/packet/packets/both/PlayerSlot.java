package net.jay.pluto.net.packet.packets.both;

import lombok.AllArgsConstructor;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.packet.MultipleHandlersBothPacket;

@AllArgsConstructor
public class PlayerSlot implements MultipleHandlersBothPacket<IServerLoginNetHandler, IServerPlayNetHandler> {
    private static final Packets enumRepresentation = Packets.PlayerSlot;

    public short playerID;
    public short slot;
    public short stack;
    public short prefix;
    public short itemNetID;

    public PlayerSlot(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        playerID = buffer.readUnsignedByte();
        slot = buffer.readShort();
        stack = buffer.readShort();
        prefix = buffer.readUnsignedByte();
        itemNetID = buffer.readShort();
    }

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(slot);
        buffer.writeShort(stack);
        buffer.writeUnsignedByte(prefix);
        buffer.writeShort(itemNetID);
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeUnsignedByte(playerID);
        buffer.writeShort(slot);
        buffer.writeShort(stack);
        buffer.writeUnsignedByte(prefix);
        buffer.writeShort(itemNetID);
        return buffer;
    }

    @Override
    public void processPacketLogin(IServerLoginNetHandler handler) {
        handler.processPlayerSlot(this);
    }

    @Override
    public void processPacketPlay(IServerPlayNetHandler handler) {

    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
