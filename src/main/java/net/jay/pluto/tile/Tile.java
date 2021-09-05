package net.jay.pluto.tile;

/**
 * The class for a Terraria tile, this class is optimized for minimal memory use
 * @see Tiles
 * @author Jay
 */
public class Tile {
    /** The internal ID of this tile */
    private short ID;

    /** The x position of this tile */
    private short x;
    /** The y position of this tile */
    private short y;

    /**
     * The bit by bit flags for this tile, this reduces the memory needed per tile
     * <br> <br>
     * First 3 bits are allocated for combinations to check what <code>Modification</code> this tile has
     * <br>
     * The 5 other bits are currently unused
     */
    private byte flags = 0;

    public Modification getModification() {
        boolean b1 = getBitFlag(0);
        boolean b2 = getBitFlag(1);
        boolean b3 = getBitFlag(2);

        if(!b1 && !b2 && !b3) return Modification.Solid;
        if(b1 && !b2 && !b3) return Modification.HalfBrick;
        if(b1 && b2 && !b3) return Modification.SlopeDownRight;
        if(b1 && b2 && b3) return Modification.SlopeDownLeft;
        if(!b1 && b2 && b3) return Modification.SlopeUpRight;
        if(!b1 && !b2 && b3) return Modification.SlopeUpLeft;
        throw new IllegalStateException();
    }

    public void setModification(Modification modification) {
        switch(modification) {
            case Solid -> {
                setBitFlag(0, false);
                setBitFlag(1, false);
                setBitFlag(2, false);
            }
            case HalfBrick -> {
                setBitFlag(0, true);
                setBitFlag(1, false);
                setBitFlag(2, false);
            }
            case SlopeDownRight -> {
                setBitFlag(0, true);
                setBitFlag(1, true);
                setBitFlag(2, false);
            }
            case SlopeDownLeft -> {
                setBitFlag(0, true);
                setBitFlag(1, true);
                setBitFlag(2, true);
            }
            case SlopeUpRight -> {
                setBitFlag(0, false);
                setBitFlag(1, true);
                setBitFlag(2, true);
            }
            case SlopeUpLeft -> {
                setBitFlag(0, false);
                setBitFlag(1, false);
                setBitFlag(2, true);
            }
        }
    }

    private boolean getBitFlag(int index) {
        return ((flags >> index) & 1) == 1;
    }

    private void setBitFlag(int index, boolean flag) {
        flags |= (flag ? 1 : 0) << index;
    }

    public enum Modification {
        Solid(0),
        HalfBrick(1),
        SlopeDownRight(2),
        SlopeDownLeft(3),
        SlopeUpRight(4),
        SlopeUpLeft(5);

        public final int ID;

        Modification(int ID) {
            this.ID = ID;
        }
    }
}
