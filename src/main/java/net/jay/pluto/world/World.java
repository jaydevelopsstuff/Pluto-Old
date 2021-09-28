package net.jay.pluto.world;

import net.jay.pluto.container.Chest;
import net.jay.pluto.entity.mob.Mob;
import net.jay.pluto.entity.npc.NPC;
import net.jay.pluto.entity.tileentity.TileEntity;
import net.jay.pluto.world.sign.Sign;
import net.jay.pluto.world.tile.Tile;
import net.jay.pluto.world.tracking.DownedTracker;
import net.jay.pluto.world.tracking.SavedTracker;

import java.util.UUID;

public class World {
    private WorldMetadata metadata;
    private UUID uuid;
    private String name;
    private int seed;
    private String rawSeed;
    private long worldGenVersion;
    private int maxTilesX;
    private int maxTilesY;
    private WorldDifficulty worldDifficulty;
    private WorldEvil evil;
    private int spawnX;
    private int spawnY;

    private boolean hardmode;

    private boolean daytime;
    private double time;

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

    public World(WorldMetadata metadata, UUID uuid, String name, String rawSeed, long worldGenVersion, int maxTilesX, int maxTilesY, WorldDifficulty worldDifficulty, WorldEvil evil, int spawnX, int spawnY, boolean daytime, double time, boolean raining, DownedTracker downedTracker, SavedTracker savedTracker, BoringWorldInfo boringInfo, Tile[][] tiles, Chest[] chests, Sign[] signs, NPC[] npcs, Mob[] mobs, TileEntity[] tileEntities, PressurePlate[] pressurePlates) {
        this.metadata = metadata;
        this.uuid = uuid;
        this.name = name;
        this.rawSeed = rawSeed;
        this.worldGenVersion = worldGenVersion;
        this.maxTilesX = maxTilesX;
        this.maxTilesY = maxTilesY;
        this.worldDifficulty = worldDifficulty;
        this.evil = evil;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.daytime = daytime;
        this.time = time;
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

    public Tile[][] getTiles() {
        return tiles;
    }
}
