package net.jay.pluto.net.packet.packets.both;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.packet.MultipleHandlersBothPacket;

public class PlayerSlot implements MultipleHandlersBothPacket<IServerLoginNetHandler, IServerPlayNetHandler> {
    private static final Packets enumRepresentation = Packets.PlayerSlot;
    private static final int maxPacketDataSize = 8;

    public short playerID;
    public short slotID;
    public short stack;
    public short prefix;
    public short itemNetID;

    public PlayerSlot(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    public PlayerSlot(short playerID, short slotID, short stack, short prefix, short itemNetID) {
        this.playerID = playerID;
        this.slotID = slotID;
        this.stack = stack;
        this.prefix = prefix;
        this.itemNetID = itemNetID;
    }


    @Override
    public void readPacketData(PacketBuffer buffer) {
        playerID = buffer.readUnsignedByte();
        slotID = buffer.readShort();
        stack = buffer.readShort();
        prefix = buffer.readUnsignedByte();
        itemNetID = buffer.readShort();
    }

    @Override
    public PacketBuffer writePacketData() {
        PacketBuffer buffer = new PacketBuffer(maxPacketDataSize);
        buffer.writeByte(playerID);
        buffer.writeShort(slotID);
        buffer.writeShort(stack);
        buffer.writeByte(prefix);
        buffer.writeShort(itemNetID);
        return buffer;
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeByte(playerID);
        buffer.writeShort(slotID);
        buffer.writeShort(stack);
        buffer.writeByte(prefix);
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
    public int getMaxPacketDataSize() {
        return maxPacketDataSize;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
