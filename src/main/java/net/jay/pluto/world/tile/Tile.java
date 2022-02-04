package net.jay.pluto.world.tile;

import lombok.Getter;
import lombok.Setter;

public class Tile {
    @Getter
    private Block block;
    @Getter
    private Wall wall;

    @Getter
    @Setter
    private short frameX = -1;

    @Getter
    @Setter
    private short frameY = -1;

    /**
     * The bit by bit flags for this tile, this reduces the memory needed per tile
     * <br> <br>
     * First 2 bits are allocated for whether there is an actuator and whether the block is actuated or not
     * <br>
     * The next 5 bits are for the type of wire there is (this is an inefficient use of bits, but I don't really care
     * <br><br>
     */
    private byte flags = 0b00100000;

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

    public Tile(Block block, int frameX, int frameY) {
        this(block);
        this.frameX = (short)frameX;
        this.frameY = (short)frameY;
    }

    public Tile(Wall wall, int frameX, int frameY) {
        this(wall);
        this.frameX = (short)frameX;
        this.frameY = (short)frameY;
    }

    public Tile(Block block, Wall wall, int frameX, int frameY) {
        this(block, wall);
        this.frameX = (short)frameX;
        this.frameY = (short)frameY;
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

    public void setActuated(boolean actuated) {
        setBitFlag(1, actuated);
    }

    public boolean hasBlock() {
        return block != null;
    }

    public boolean hasWall() {
        return wall != null;
    }

    public boolean isEmpty() {
        return !hasBlock() && !hasWall();
    }

    public boolean sameAs(Tile tile) {
        if(isEmpty() && tile.isEmpty() && flags == tile.flags) return true;
        if(block == null) return tile.block == null;
        if(wall == null) return tile.wall == null;
        return block.sameAs(tile.block) && wall.sameAs(tile.wall) && flags == tile.flags;
    }

    public Tile copy() {
        return new Tile(block, wall, frameX, frameY);
    }

    public Tile copy(int customFrameY) {
        return new Tile(block, wall, frameX, customFrameY);
    }

    private boolean getBitFlag(int index) {
        return ((flags >> index) & 1) == 1;
    }

    private void setBitFlag(int index, boolean flag) {
        flags |= (flag ? 1 : 0) << index;
    }
}
