package net.jay.pluto.world;

public class PressurePlate {
    private short x;
    private short y;

    public PressurePlate(int x, int y) {
        this.x = (short)x;
        this.y = (short)y;
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
