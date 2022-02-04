package net.jay.pluto.net.packet.packets.both;

import lombok.AllArgsConstructor;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.packet.MultipleHandlersBothPacket;
import net.jay.pluto.util.TColor;

@AllArgsConstructor
public class PlayerInfo implements MultipleHandlersBothPacket<IServerLoginNetHandler, IServerPlayNetHandler> {
    private static final Packets enumRepresentation = Packets.PlayerInfo;

    public short playerID;
    public short skinVariant;
    public short hairType;
    public String name;
    public short hairDye;
    public short hideVisuals;
    public short hideVisuals2;
    public short hideMisc;
    public TColor hairColor;
    public TColor skinColor;
    public TColor eyeColor;
    public TColor shirtColor;
    public TColor underShirtColor;
    public TColor pantsColor;
    public TColor shoeColor;
    public byte difficultyFlag;
    public byte torchFlags;

    public PlayerInfo(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        playerID = buffer.readUnsignedByte();
        skinVariant = buffer.readUnsignedByte();
        hairType = buffer.readUnsignedByte();
        name = buffer.readString();
        hairDye = buffer.readUnsignedByte();
        hideVisuals = buffer.readByte();
        hideVisuals2 = buffer.readByte();
        hideMisc = buffer.readUnsignedByte();
        hairColor = buffer.readColor();
        skinColor = buffer.readColor();
        eyeColor = buffer.readColor();
        shirtColor = buffer.readColor();
        underShirtColor = buffer.readColor();
        pantsColor = buffer.readColor();
        shoeColor = buffer.readColor();
        difficultyFlag = buffer.readByte();
        torchFlags = buffer.readByte();
    }

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeUnsignedByte(playerID);
        buffer.writeUnsignedByte(skinVariant);
        buffer.writeUnsignedByte(hairType);
        buffer.writeString(name);
        buffer.writeUnsignedByte(hairDye);
        buffer.writeUnsignedByte(hideVisuals);
        buffer.writeUnsignedByte(hideVisuals2);
        buffer.writeUnsignedByte(hideMisc);
        buffer.writeColor(hairColor);
        buffer.writeColor(skinColor);
        buffer.writeColor(eyeColor);
        buffer.writeColor(shirtColor);
        buffer.writeColor(underShirtColor);
        buffer.writeColor(pantsColor);
        buffer.writeColor(shoeColor);
        buffer.writeByte(difficultyFlag);
        buffer.writeByte(torchFlags);
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeUnsignedByte(playerID);
        buffer.writeUnsignedByte(skinVariant);
        buffer.writeUnsignedByte(hairType);
        buffer.writeString(name);
        buffer.writeUnsignedByte(hairDye);
        buffer.writeUnsignedByte(hideVisuals);
        buffer.writeUnsignedByte(hideVisuals2);
        buffer.writeUnsignedByte(hideMisc);
        buffer.writeColor(hairColor);
        buffer.writeColor(skinColor);
        buffer.writeColor(eyeColor);
        buffer.writeColor(shirtColor);
        buffer.writeColor(underShirtColor);
        buffer.writeColor(pantsColor);
        buffer.writeColor(shoeColor);
        buffer.writeByte(difficultyFlag);
        buffer.writeByte(torchFlags);
        return buffer;
    }

    @Override
    public void processPacketLogin(IServerLoginNetHandler handler) {
        handler.processPlayerInfo(this);
    }

    @Override
    public void processPacketPlay(IServerPlayNetHandler handler) {

    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
