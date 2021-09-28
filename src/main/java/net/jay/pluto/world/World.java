package net.jay.pluto.world;

import net.jay.pluto.world.tile.Tile;

import java.util.UUID;

public class World {
    private int versionNumber;
    private WorldMetadata metadata;
    private UUID uuid;
    private int seed;
    private String rawSeed;
    private int worldGenVersion;
    private WorldDifficulty worldDifficulty;
    private int maxTilesX;
    private int maxTilesY;



    /** A 2D array of all tiles in the world */
    private final Tile[][] tiles;

    public World(WorldMetadata metadata) {
        this.metadata = metadata;

        this.tiles = new Tile[getWorldWidth()][getWorldHeight()];
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
