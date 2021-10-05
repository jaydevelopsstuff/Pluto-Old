package net.jay.pluto.world.tile;

/**
 * The class for a Terraria "Block", this class is optimized for minimal memory use
 * @see Blocks
 * @author Jay
 */
public class Block {
    /** The internal ID of this tile */
    private short ID;

    private short u;

    private short v;

    private byte color;

    /**
     * The bit by bit flags for this block, this reduces the memory needed per block
     * <br> <br>
     * First 3 bits are allocated for combinations to check what <code>Modification</code> this block has
     * <br>
     * The last 5 bits are currently unused
     * <br><br>
     * If this byte is 0 (all bits 0/false) then this block is "plain"
     */
    private byte flags = 0;

    public Block(short ID) {
        this.ID = ID;
    }

    public Block(int ID) {
        this((short)ID);
        if(ID < Short.MIN_VALUE || ID > Short.MAX_VALUE) throw new IllegalArgumentException("ID must be in range of signed short");
    }

    public Block(Blocks block) {
        this(block.ID);
    }

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

    public boolean sameAs(Block block) {
        if(block == null) return false;
        return ID == block.ID && u == block.u && v == block.v && block.color == color && block.flags == flags;
    }

    public Blocks getType() {
        return Blocks.fromID(ID);
    }

    public void setType(Blocks block) {
        ID = (short)block.ID;
        u = -1;
        v = -1;
    }

    public String getName() {
        Blocks tileType = Blocks.fromID(ID);
        if(tileType == null) return null;
        return tileType.name;
    }

    public short getID() {
        return ID;
    }

    public void setID(short ID) {
        this.ID = ID;
        this.u = -1;
        this.v = -1;
    }

    public void setID(int ID) {
        if(ID < Short.MIN_VALUE || ID > Short.MAX_VALUE) throw new IllegalArgumentException("ID must be in the range of signed short");
        setID((short)ID);
    }

    public short getU() {
        return u;
    }

    public void setU(short u) {
        this.u = u;
    }

    public short getV() {
        return v;
    }

    public void setV(short v) {
        this.v = v;
    }

    public byte getColor() {
        return color;
    }

    public void setColor(byte color) {
        this.color = color;
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

        public static Modification fromID(int ID) {
            for(Modification modification : values()) {
                if(modification.ID == ID) return modification;
            }
            return null;
        }
    }
}
