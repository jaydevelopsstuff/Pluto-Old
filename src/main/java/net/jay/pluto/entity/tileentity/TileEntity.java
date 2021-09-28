package net.jay.pluto.entity.tileentity;

public class TileEntity {
    private int ID;
    private short x;
    private short y;

    public TileEntity(int ID, short x, short y) {
        this.ID = ID;
        this.x = x;
        this.y = y;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
}
