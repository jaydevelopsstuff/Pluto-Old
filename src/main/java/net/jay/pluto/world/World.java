package net.jay.pluto.world;

import net.jay.pluto.container.Chest;
import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.entity.mob.Mob;
import net.jay.pluto.entity.npc.NPC;
import net.jay.pluto.entity.tileentity.TileEntity;
import net.jay.pluto.net.Client;
import net.jay.pluto.net.packet.packets.client.RequestEssentialTiles;
import net.jay.pluto.net.packet.packets.server.*;
import net.jay.pluto.world.sign.Sign;
import net.jay.pluto.world.tile.Tile;
import net.jay.pluto.world.tracking.DownedTracker;
import net.jay.pluto.world.tracking.SavedTracker;

import java.io.IOException;
import java.util.UUID;

// TODO Needs cleanup
public class World implements Access {
    private WorldMetadata metadata;
    private UUID uuid;
    private int ID;
    private String name;
    private int seed;
    private String rawSeed;
    private long worldGenVersion;
    private int maxTilesX;
    private int maxTilesY;
    private int maxSectionX;
    private int maxSectionY;
    private WorldDifficulty worldDifficulty;
    private WorldEvil evil;
    private int spawnX;
    private int spawnY;
    private int dungeonX;
    private int dungeonY;

    private boolean hardmode;

    private boolean daytime;
    private double time;
    private boolean bloodMoon;
    private boolean eclipse;

    private boolean raining;

    private DownedTracker downedTracker;
    private SavedTracker savedTracker;

    private BoringWorldInfo boringInfo;

    /** A 2D array of all tiles in the world */
    private Tile[][] tiles;
    private Chest[] chests;
    private Sign[] signs;
    private NPC[] npcs;
    private Mob[] mobs;
    private TileEntity[] tileEntities;
    private PressurePlate[] pressurePlates;

    public World(WorldMetadata metadata, UUID uuid, int ID, String name, String rawSeed, long worldGenVersion, int maxTilesX, int maxTilesY, WorldDifficulty worldDifficulty, WorldEvil evil, int spawnX, int spawnY, int dungeonX, int dungeonY, boolean hardmode, boolean daytime, double time, boolean bloodMoon, boolean eclipse, boolean raining, DownedTracker downedTracker, SavedTracker savedTracker, BoringWorldInfo boringInfo, Tile[][] tiles, Chest[] chests, Sign[] signs, NPC[] npcs, Mob[] mobs, TileEntity[] tileEntities, PressurePlate[] pressurePlates) {
        this.metadata = metadata;
        this.uuid = uuid;
        this.ID = ID;
        this.name = name;
        this.rawSeed = rawSeed;
        this.worldGenVersion = worldGenVersion;
        this.maxTilesX = maxTilesX;
        this.maxTilesY = maxTilesY;
        this.maxSectionX = maxTilesX / 200;
        this.maxSectionY = maxTilesY / 150;
        this.worldDifficulty = worldDifficulty;
        this.evil = evil;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.dungeonX = dungeonX;
        this.dungeonY = dungeonY;
        this.hardmode = hardmode;
        this.daytime = daytime;
        this.time = time;
        this.bloodMoon = bloodMoon;
        this.eclipse = eclipse;
        this.raining = raining;
        this.downedTracker = downedTracker;
        this.savedTracker = savedTracker;
        this.boringInfo = boringInfo;
        this.tiles = tiles;
        this.chests = chests;
        this.signs = signs;
        this.npcs = npcs;
        this.mobs = mobs;
        this.tileEntities = tileEntities;
        this.pressurePlates = pressurePlates;
    }

    public Tile getTile(int x, int y) {
        if((x < 0 || x > getWorldWidth()) || (y < 0 || y > getWorldHeight())) throw new IllegalArgumentException("X/Y cannot be less than 0 or more than world width/height");
        return tiles[x][y];
    }

    public void setTile(Tile tile, int x, int y) {
        if(tile == null) throw new IllegalArgumentException("Tile cannot be null");
        if((x < 0 || x > getWorldWidth()) || (y < 0 || y > getWorldHeight())) throw new IllegalArgumentException("X/Y cannot be less than 0 or more than world width/height");
        tiles[x][y] = tile;
        // TODO Synchronise change with all clients etc
    }

    public Tile[][] getTiles() {
        return getTiles(0, 0, getMaxTilesX(), getMaxTilesY());
    }

    public Tile[][] getTiles(int startX, int startY, int width, int height) {
        Tile[][] tempTiles = new Tile[width][height];

        for(int x = startX; x < startX + width; x++)
            for(int y = startY; y < startY + height; y++)
                tempTiles[x - startX][y - startY] = tiles[x][y];

        return tempTiles;
    }

    public Tile[][] getTilesSpecific(int startX, int startY, int endX, int endY) {
        int width = endX - startX;
        int height = endY - startY;

        if((startX < 0 || startX > getWorldWidth()) || (startY < 0 || startY > getWorldHeight())) throw new IllegalArgumentException("Starting X/Y cannot be less than 0 or more than world width/height");
        if(width < 0 || height < 0) throw new IllegalArgumentException("Width and height must be positive numbers");

        return getTiles(startX, startY, width, height);
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getID() {
        return ID;
    }

    public long getWorldGenVersion() {
        return worldGenVersion;
    }

    public int getMaxTilesX() {
        return maxTilesX;
    }

    public int getMaxTilesY() {
        return maxTilesY;
    }

    public int getMaxSectionX() {
        return maxSectionX;
    }

    public int getMaxSectionY() {
        return maxSectionY;
    }

    public WorldDifficulty getWorldDifficulty() {
        return worldDifficulty;
    }

    public WorldEvil getEvil() {
        return evil;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public int getDungeonX() {
        return dungeonX;
    }

    public int getDungeonY() {
        return dungeonY;
    }

    public boolean isHardmode() {
        return hardmode;
    }

    public boolean isDaytime() {
        return daytime;
    }

    public double getTime() {
        return time;
    }

    public boolean isBloodMoon() {
        return bloodMoon;
    }

    public boolean isEclipse() {
        return eclipse;
    }

    public boolean isRaining() {
        return raining;
    }

    public DownedTracker getDownedTracker() {
        return downedTracker;
    }

    public SavedTracker getSavedTracker() {
        return savedTracker;
    }

    public BoringWorldInfo getBoringInfo() {
        return boringInfo;
    }

    public Chest[] getChests() {
        return chests;
    }

    public Sign[] getSigns() {
        return signs;
    }

    public NPC[] getNpcs() {
        return npcs;
    }

    public Mob[] getMobs() {
        return mobs;
    }

    public TileEntity[] getTileEntities() {
        return tileEntities;
    }

    public PressurePlate[] getPressurePlates() {
        return pressurePlates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeed() {
        return seed;
    }

    public String getRawSeed() {
        return rawSeed;
    }

    public WorldMetadata getMetadata() {
        return metadata;
    }

    public int getWorldWidth() {
        return maxTilesX;
    }

    public int getWorldHeight() {
        return maxTilesY;
    }
}
