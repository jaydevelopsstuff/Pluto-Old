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
     * The next 3 bits are for the <code>WireType</code> this tile has
     * <br>
     * The 2 bits after that are for whether the block has an actuator on it and whether its actuated
     * <br><br>
     * If this byte is 0 (all bits 0/false) then this block is "plain"
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

    public WireType getWireType() {
        boolean b4 = getBitFlag(3);
        boolean b5 = getBitFlag(4);

        if(!b4 && !b5) return null;
        if(b4 && !b5) return WireType.Blue;
        if(!b4 && b5) return WireType.Green;
        if(b4 && b5) return WireType.Yellow;
        throw new IllegalStateException();
    }

    public void setWireType(WireType type) {
        if(type == null) {
            setBitFlag(3, false);
            setBitFlag(4, false);
            setBitFlag(5, false);
            return;
        }
        switch(type) {
            case Red -> {
                setBitFlag(3, true);
                setBitFlag(4, false);
                setBitFlag(5, false);
            }
            case Blue -> {
                setBitFlag(3, true);
                setBitFlag(4, true);
                setBitFlag(5, false);
            }
            case Green -> {
                setBitFlag(3, true);
                setBitFlag(4, true);
                setBitFlag(5, true);
            }
            case Yellow -> {
                setBitFlag(3, false);
                setBitFlag(4, true);
                setBitFlag(5, true);
            }
        }
    }

    public boolean hasActuator() {
        return getBitFlag(6);
    }

    public void placeActuator() {
        setBitFlag(6, true);
    }

    public void removeActuator() {
        setBitFlag(6, false);
    }

    public boolean isActuated() {
        return getBitFlag(7);
    }

    public void setActuated(boolean actuated) {
        setBitFlag(7, actuated);
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
