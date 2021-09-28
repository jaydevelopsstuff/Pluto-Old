package net.jay.pluto.world.sign;

public class Sign {
    private String text;
    private short x;
    private short y;

    public Sign() {
        this.text = "";
        this.x = -1;
        this.y = -1;
    }

    public Sign(String text) {
        this();
        this.text = text;
    }

    public Sign(String text, int x, int y) {
        this.text = text;
        this.x = (short)x;
        this.y = (short)y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }
}
