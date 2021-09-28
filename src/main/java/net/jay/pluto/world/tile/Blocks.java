package net.jay.pluto.world.tile;

/**
 * An enum containing all the tiles in Terraria
 * @see Block
 */
public enum Blocks {
    /** Represents an inactive block, not an actual tile */
    Air(-1, "Air"),
    Dirt(0, "Dirt"),
    Stone(1, "Stone"),
    Grass(2, "Grass");

    public final int ID;
    public final String name;

    Blocks(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public static Blocks fromID(int ID) {
        for(Blocks tile : values()) {
            if(tile.ID  == ID) return tile;
        }
        return null;
    }
}
