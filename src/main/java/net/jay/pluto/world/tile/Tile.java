package net.jay.pluto.world.tile;

public class Tile {
    private Block block;
    private Wall wall;

    private short x = -1;

    private short y = -1;

    /**
     * The bit by bit flags for this tile, this reduces the memory needed per tile
     * <br> <br>
     * First 2 bits are allocated for whether there is an actuator and whether the block is actuated or not
     * <br>
     * The next 5 bits are for the type of wire there is (this is an inefficient use of bits, but I don't really care
     * <br><br>
     * If this byte is 0 (all bits 0/false) then this block is "plain"
     */
    private byte flags = 0;

    public Tile(Block block) {
        this.block = block;
    }

    public Tile(Wall wall) {
        this.wall = wall;
    }

    public Tile(Block block, Wall wall) {
        this.block = block;
        this.wall = wall;
    }

    public Tile(Block block, int x, int y) {
        this(block);
        this.x = (short)x;
        this.y = (short)y;
    }

    public Tile(Wall wall, int x, int y) {
        this(wall);
        this.x = (short)x;
        this.y = (short)y;
    }

    public Tile(Block block, Wall wall, int x, int y) {
        this(block, wall);
        this.x = (short)x;
        this.y = (short)y;
    }

    public boolean hasActuator() {
        return getBitFlag(0);
    }

    public void placeActuator() {
        setBitFlag(0, true);
    }

    public void removeActuator() {
        setBitFlag(0, false);
    }

    public boolean isActuated() {
        return getBitFlag(1);
    }

    public WireType getWire() {
        if(getBitFlag(2)) return null;
        if(getBitFlag(3)) return WireType.Red;
        if(getBitFlag(4)) return WireType.Blue;
        if(getBitFlag(5)) return WireType.Green;
        if(getBitFlag(6)) return WireType.Yellow;
        throw new IllegalStateException();
    }

    public void setWire(WireType wire) {
        switch(wire) {
            case Red -> {
                setBitFlag(2, false);
                setBitFlag(3, true);
                setBitFlag(4, false);
                setBitFlag(5, false);
                setBitFlag(6, false);
            }
            case Blue -> {
                setBitFlag(2, false);
                setBitFlag(3, false);
                setBitFlag(4, true);
                setBitFlag(5, false);
                setBitFlag(6, false);
            }
            case Green -> {
                setBitFlag(2, false);
                setBitFlag(3, false);
                setBitFlag(4, false);
                setBitFlag(5, true);
                setBitFlag(6, false);
            }
            case Yellow -> {
                setBitFlag(2, false);
                setBitFlag(3, false);
                setBitFlag(4, false);
                setBitFlag(5, false);
                setBitFlag(6, true);
            }
            // Null (No wire)
            default -> {
                setBitFlag(2, true);
                setBitFlag(3, false);
                setBitFlag(4, false);
                setBitFlag(5, false);
                setBitFlag(6, false);
            }
        }
    }

    public void setActuated(boolean actuated) {
        setBitFlag(1, actuated);
    }

    public Tile copy() {
        return new Tile(block, wall, x, y);
    }

    public Tile copy(int customY) {
        return new Tile(block, wall, x, customY);
    }

    public short getX() {
        return x;
    }

    public void setX(short x) {
        this.x = x;
    }

    public short getY() {
        return y;
    }

    public void setY(short y) {
        this.y = y;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    private boolean getBitFlag(int index) {
        return ((flags >> index) & 1) == 1;
    }

    private void setBitFlag(int index, boolean flag) {
        flags |= (flag ? 1 : 0) << index;
    }
}
