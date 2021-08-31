package net.jay.pluto.net.packet.packets.both;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.packet.MultipleHandlersBothPacket;
import net.jay.pluto.util.TColor;

public class PlayerInfo implements MultipleHandlersBothPacket<IServerLoginNetHandler, IServerPlayNetHandler> {
    private static final Packets enumRepresentation = Packets.PlayerInfo;
    private static final int maxPacketDataSize = 30 + maxStringLength;

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

    public PlayerInfo(short playerID, short skinVariant, short hairType, String name, short hairDye, short hideVisuals, short hideVisuals2, short hideMisc, TColor hairColor, TColor skinColor, TColor eyeColor, TColor shirtColor, TColor underShirtColor, TColor pantsColor, TColor shoeColor, byte difficultyFlag, byte torchFlags) {
        this.playerID = playerID;
        this.skinVariant = skinVariant;
        this.hairType = hairType;
        this.name = name;
        this.hairDye = hairDye;
        this.hideVisuals = hideVisuals;
        this.hideVisuals2 = hideVisuals2;
        this.hideMisc = hideMisc;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.shirtColor = shirtColor;
        this.underShirtColor = underShirtColor;
        this.pantsColor = pantsColor;
        this.shoeColor = shoeColor;
        this.difficultyFlag = difficultyFlag;
        this.torchFlags = torchFlags;
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
    public void processPacketLogin(IServerLoginNetHandler handler) {
        handler.processPlayerInfo(this);
    }

    @Override
    public void processPacketPlay(IServerPlayNetHandler handler) {

    }

    @Override
    public PacketBuffer writePacketData() {
        PacketBuffer buffer = new PacketBuffer(maxPacketDataSize);
        buffer.writeByte(playerID);
        buffer.writeByte(skinVariant);
        buffer.writeByte(hairType);
        buffer.writeString(name);
        buffer.writeByte(hairDye);
        buffer.writeByte(hideVisuals);
        buffer.writeByte(hideVisuals2);
        buffer.writeByte(hideMisc);
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
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        if(buffer.getAllocation() < maxPacketDataSize) throw new IllegalArgumentException("Buffer must have a length of at least " + maxPacketDataSize);
        buffer.writeByte(playerID);
        buffer.writeByte(skinVariant);
        buffer.writeByte(hairType);
        buffer.writeString(name);
        buffer.writeByte(hairDye);
        buffer.writeByte(hideVisuals);
        buffer.writeByte(hideVisuals2);
        buffer.writeByte(hideMisc);
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
    public int getMaxPacketDataSize() {
        return maxPacketDataSize;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
