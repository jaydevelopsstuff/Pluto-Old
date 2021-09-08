package net.jay.pluto.world;


import net.jay.pluto.tile.Tile;

public class World {
    private int seed;
    private String seedText;

    private WorldMetadata metadata;

    /** A 2D array of all tiles in the world */
    private final Tile[][] tiles;

    public World(String seedText, int seed, WorldMetadata metadata) {
        this.seedText = seedText;
        this.seed = seed;
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

    public String getSeedText() {
        return seedText;
    }

    public WorldMetadata getMetadata() {
        return metadata;
    }

    public int getWorldWidth() {
        return metadata.getWorldWidth();
    }

    public int getWorldHeight() {
        return metadata.getWorldHeight();
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
