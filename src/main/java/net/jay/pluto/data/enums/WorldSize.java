package net.jay.pluto.data.enums;

public enum WorldSize {
    SMALL(4200, 1200),
    MEDIUM(6400, 1800),
    LARGE(8400, 2400);

    public final int width;
    public final int height;

    WorldSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
