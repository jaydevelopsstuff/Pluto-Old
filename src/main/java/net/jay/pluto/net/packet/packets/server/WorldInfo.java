package net.jay.pluto.net.packet.packets.server;

import lombok.AllArgsConstructor;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.packet.SPacket;
import net.jay.pluto.util.BitsByte;
import net.jay.pluto.world.*;
import net.jay.pluto.world.tracking.DownedTracker;
import net.jay.pluto.world.tracking.SavedTracker;

import java.util.UUID;

@AllArgsConstructor
public class WorldInfo implements SPacket {
    private static final Packets enumRepresentation = Packets.WorldInfo;

    public UUID uuid;
    public int ID;
    public String name;
    public long worldGenVersion;
    public short maxTilesX;
    public short maxTilesY;
    public WorldDifficulty difficulty;
    public short spawnX;
    public short spawnY;
    public boolean hardmode;
    public boolean daytime;
    public double time;
    public boolean bloodMoon;
    public boolean eclipse;
    public DownedTracker downedTracker;
    public BoringWorldInfo boringInfo;

    public WorldInfo(World world) {
        this(world.getUuid(), world.getID(), world.getName(), world.getWorldGenVersion(), (short)world.getMaxTilesX(), (short)world.getMaxTilesY(), world.getWorldDifficulty(), (short)world.getSpawnX(), (short)world.getSpawnY(), world.isHardmode(), world.isDaytime(), world.getTime(), world.isBloodMoon(), world.isEclipse(), world.getDownedTracker(), world.getBoringInfo());
    }

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeInt((int)time);
        BitsByte dayMoonEclipse = new BitsByte();
        dayMoonEclipse.setBits(daytime, bloodMoon, eclipse);
        buffer.writeByte(dayMoonEclipse.build());
        buffer.writeByte((byte)boringInfo.getMoonPhase());
        buffer.writeShort(maxTilesX);
        buffer.writeShort(maxTilesY);
        buffer.writeShort(spawnX);
        buffer.writeShort(spawnY);
        buffer.writeShort((short)Math.floor(boringInfo.getWorldSurface()));
        buffer.writeShort((short)Math.floor(boringInfo.getRockLayer()));
        buffer.writeInt(ID);
        buffer.writeString(name);
        buffer.writeByte(difficulty.ID);
        buffer.writeLong(uuid.getLeastSignificantBits());
        buffer.writeLong(uuid.getMostSignificantBits());
        buffer.writeLong(worldGenVersion);
        buffer.writeByte(boringInfo.getMoonType());
        buffer.writeByte((byte)boringInfo.getBgTree());
        buffer.writeByte((byte)boringInfo.getBgCorruption());
        buffer.writeByte((byte)boringInfo.getBgJungle());
        buffer.writeByte((byte)boringInfo.getBgSnow());
        buffer.writeByte((byte)boringInfo.getBgHallow());
        buffer.writeByte((byte)boringInfo.getBgCrimson());
        buffer.writeByte((byte)boringInfo.getBgDesert());
        buffer.writeByte((byte)boringInfo.getBgOcean());
        buffer.writeByte((byte)boringInfo.getBgMushroom());
        buffer.writeByte((byte)boringInfo.getBgUnderworld());
        buffer.writeByte((byte)boringInfo.getBgTree2());
        buffer.writeByte((byte)boringInfo.getBgTree3());
        buffer.writeByte((byte)boringInfo.getBgTree4());
        buffer.writeByte((byte)boringInfo.getIceBackStyle());
        buffer.writeByte((byte)boringInfo.getJungleBackStyle());
        buffer.writeByte((byte)boringInfo.getHellBackStyle());
        buffer.writeFloat(boringInfo.getWindSpeedTarget());
        buffer.writeByte((byte)boringInfo.getNumClouds());
        buffer.writeInt(boringInfo.getTreeX0());
        buffer.writeInt(boringInfo.getTreeX1());
        buffer.writeInt(boringInfo.getTreeX2());
        buffer.writeByte((byte)boringInfo.getTreeStyle0());
        buffer.writeByte((byte)boringInfo.getTreeStyle1());
        buffer.writeByte((byte)boringInfo.getTreeStyle2());
        buffer.writeByte((byte)boringInfo.getTreeStyle3());
        buffer.writeByte((byte)boringInfo.getCaveBackX0());
        buffer.writeByte((byte)boringInfo.getCaveBackX1());
        buffer.writeByte((byte)boringInfo.getCaveBackX2());
        buffer.writeByte((byte)boringInfo.getCaveBackStyle0());
        buffer.writeByte((byte)boringInfo.getCaveBackStyle1());
        buffer.writeByte((byte)boringInfo.getCaveBackStyle2());
        buffer.writeByte((byte)boringInfo.getCaveBackStyle3());
        int[] treeTops = boringInfo.getTreeTops();
        buffer.writeByte((byte)treeTops[0]);
        buffer.writeByte((byte)treeTops[1]);
        buffer.writeByte((byte)treeTops[2]);
        buffer.writeByte((byte)treeTops[3]);
        buffer.writeByte((byte)treeTops[4]);
        buffer.writeByte((byte)treeTops[5]);
        buffer.writeByte((byte)treeTops[6]);
        buffer.writeByte((byte)treeTops[7]);
        buffer.writeByte((byte)treeTops[8]);
        buffer.writeByte((byte)treeTops[9]);
        buffer.writeByte((byte)treeTops[10]);
        buffer.writeByte((byte)treeTops[11]);
        buffer.writeByte((byte)treeTops[12]);
        buffer.writeFloat(boringInfo.getMaxRain());
        BitsByte eventInfo1 = new BitsByte();
        // TODO Fix bosses here also server side characters
        eventInfo1.setBits(boringInfo.isShadowOrbSmashed(), downedTracker.isEyeDowned(), false, false, hardmode, downedTracker.isClownDowned(), false, false);
        buffer.writeByte(eventInfo1.build());
        BitsByte eventInfo2 = new BitsByte();
        eventInfo2.setBits(false, false, false, false, false, false, false, false);
        buffer.writeByte(eventInfo2.build());
        BitsByte eventInfo3 = new BitsByte();
        eventInfo3.setBits(difficulty == WorldDifficulty.Expert, boringInfo.isFastForwardTime(), false, downedTracker.isKingSlimeDowned(), downedTracker.isQueenBeeDowned(), false, false, false);
        buffer.writeByte(eventInfo3.build());
        BitsByte eventInfo4 = new BitsByte();
        eventInfo4.setBits(false, false, false, false, false, false, false, false);
        buffer.writeByte(eventInfo4.build());
        BitsByte eventInfo5 = new BitsByte();
        eventInfo5.setBits(false, false, false, boringInfo.isSandstormActive(), false, false, false, false);
        buffer.writeByte(eventInfo5.build());
        BitsByte eventInfo6 = new BitsByte();
        eventInfo6.setBits(boringInfo.isCombatBookUsed(), boringInfo.isLanternNightManual(), false, false, false, false, boringInfo.isForceHalloweenForToday(), boringInfo.isForceXMasForToday());
        buffer.writeByte(eventInfo6.build());
        BitsByte eventInfo7 = new BitsByte();
        eventInfo7.setBits(boringInfo.isBoughtCat(), boringInfo.isBoughtDog(), boringInfo.isBoughtBunny(), false, false /* drunk world */, false, false, false /* gg world */);
        buffer.writeByte(eventInfo7.build());
        buffer.writeShort((short)boringInfo.getOre1TileID());
        buffer.writeShort((short)boringInfo.getOre2TileID());
        buffer.writeShort((short)boringInfo.getOre3TileID());
        buffer.writeShort((short)boringInfo.getOre4TileID());
        buffer.writeShort((short)boringInfo.getHardmodeOre1TileID());
        buffer.writeShort((short)boringInfo.getHardmodeOre2TileID());
        buffer.writeShort((short)boringInfo.getHardmodeore3TileID());
        buffer.writeByte((byte)boringInfo.getInvasionType());
        buffer.writeLong(0);
        buffer.writeFloat(boringInfo.getSandstormSeverity());
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeInt((int)time);
        BitsByte dayMoonEclipse = new BitsByte();
        dayMoonEclipse.setBits(daytime, bloodMoon, eclipse);
        buffer.writeByte(dayMoonEclipse.build());
        buffer.writeByte((byte)boringInfo.getMoonPhase());
        buffer.writeShort(maxTilesX);
        buffer.writeShort(maxTilesY);
        buffer.writeShort(spawnX);
        buffer.writeShort(spawnY);
        buffer.writeShort((short)Math.floor(boringInfo.getWorldSurface()));
        buffer.writeShort((short)Math.floor(boringInfo.getRockLayer()));
        buffer.writeInt(ID);
        buffer.writeString(name);
        buffer.writeByte(difficulty.ID);
        buffer.writeLong(uuid.getLeastSignificantBits());
        buffer.writeLong(uuid.getMostSignificantBits());
        buffer.writeLong(worldGenVersion);
        buffer.writeByte(boringInfo.getMoonType());
        buffer.writeByte((byte)boringInfo.getBgTree());
        buffer.writeByte((byte)boringInfo.getBgCorruption());
        buffer.writeByte((byte)boringInfo.getBgJungle());
        buffer.writeByte((byte)boringInfo.getBgSnow());
        buffer.writeByte((byte)boringInfo.getBgHallow());
        buffer.writeByte((byte)boringInfo.getBgCrimson());
        buffer.writeByte((byte)boringInfo.getBgDesert());
        buffer.writeByte((byte)boringInfo.getBgOcean());
        buffer.writeByte((byte)boringInfo.getBgMushroom());
        buffer.writeByte((byte)boringInfo.getBgUnderworld());
        buffer.writeByte((byte)boringInfo.getBgTree2());
        buffer.writeByte((byte)boringInfo.getBgTree3());
        buffer.writeByte((byte)boringInfo.getBgTree4());
        buffer.writeByte((byte)boringInfo.getIceBackStyle());
        buffer.writeByte((byte)boringInfo.getJungleBackStyle());
        buffer.writeByte((byte)boringInfo.getHellBackStyle());
        buffer.writeFloat(boringInfo.getWindSpeedTarget());
        buffer.writeByte((byte)boringInfo.getNumClouds());
        buffer.writeInt(boringInfo.getTreeX0());
        buffer.writeInt(boringInfo.getTreeX1());
        buffer.writeInt(boringInfo.getTreeX2());
        buffer.writeByte((byte)boringInfo.getTreeStyle0());
        buffer.writeByte((byte)boringInfo.getTreeStyle1());
        buffer.writeByte((byte)boringInfo.getTreeStyle2());
        buffer.writeByte((byte)boringInfo.getTreeStyle3());
        buffer.writeByte((byte)boringInfo.getCaveBackX0());
        buffer.writeByte((byte)boringInfo.getCaveBackX1());
        buffer.writeByte((byte)boringInfo.getCaveBackX2());
        buffer.writeByte((byte)boringInfo.getCaveBackStyle0());
        buffer.writeByte((byte)boringInfo.getCaveBackStyle1());
        buffer.writeByte((byte)boringInfo.getCaveBackStyle2());
        buffer.writeByte((byte)boringInfo.getCaveBackStyle3());
        // TODO Do actual tree top styles
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeByte((byte)1);
        buffer.writeFloat(boringInfo.getMaxRain());
        BitsByte eventInfo1 = new BitsByte();
        // TODO Fix bosses here also server side characters
        eventInfo1.setBits(boringInfo.isShadowOrbSmashed(), downedTracker.isEyeDowned(), false, false, hardmode, downedTracker.isClownDowned(), false, false);
        buffer.writeByte(eventInfo1.build());
        BitsByte eventInfo2 = new BitsByte();
        eventInfo2.setBits(false, false, false, false, false, false, false, false);
        buffer.writeByte(eventInfo2.build());
        BitsByte eventInfo3 = new BitsByte();
        eventInfo3.setBits(difficulty == WorldDifficulty.Expert, boringInfo.isFastForwardTime(), false, downedTracker.isKingSlimeDowned(), downedTracker.isQueenBeeDowned(), false, false, false);
        buffer.writeByte(eventInfo3.build());
        BitsByte eventInfo4 = new BitsByte();
        eventInfo4.setBits(false, false, false, false, false, false, false, false);
        buffer.writeByte(eventInfo4.build());
        BitsByte eventInfo5 = new BitsByte();
        eventInfo5.setBits(false, false, false, boringInfo.isSandstormActive(), false, false, false, false, false);
        buffer.writeByte(eventInfo5.build());
        BitsByte eventInfo6 = new BitsByte();
        eventInfo6.setBits(boringInfo.isCombatBookUsed(), boringInfo.isLanternNightManual(), false, false, false, false, boringInfo.isForceHalloweenForToday(), boringInfo.isForceXMasForToday());
        buffer.writeByte(eventInfo6.build());
        BitsByte eventInfo7 = new BitsByte();
        eventInfo7.setBits(boringInfo.isBoughtCat(), boringInfo.isBoughtDog(), boringInfo.isBoughtBunny(), false, false /* drunk world */, false, false, false /* gg world */);
        buffer.writeByte(eventInfo7.build());
        buffer.writeShort((short)boringInfo.getOre1TileID());
        buffer.writeShort((short)boringInfo.getOre2TileID());
        buffer.writeShort((short)boringInfo.getOre3TileID());
        buffer.writeShort((short)boringInfo.getOre4TileID());
        buffer.writeShort((short)boringInfo.getHardmodeOre1TileID());
        buffer.writeShort((short)boringInfo.getHardmodeOre2TileID());
        buffer.writeShort((short)boringInfo.getHardmodeore3TileID());
        buffer.writeByte((byte)boringInfo.getInvasionType());
        buffer.writeLong(0);
        buffer.writeFloat(boringInfo.getSandstormSeverity());
        return buffer;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
