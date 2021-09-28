package net.jay.pluto.world.tile;

/**
 * @author Jay
 */
public class Wall {
    private short ID;

    private byte color;

    public Wall(short ID) {
        this.ID = ID;
    }

    public Wall(Walls wall) {

    }

    public short getID() {
        return ID;
    }

    public void setID(short ID) {
        this.ID = ID;
    }

    public byte getColor() {
        return color;
    }

    public void setColor(byte color) {
        this.color = color;
    }
}
