package net.jay.pluto.world;

public enum WorldEvil {
    CORRUPTION,
    CRIMSON;

    public static WorldEvil fromBoolean(boolean b) {
        return b ? CRIMSON : CORRUPTION;
    }
}
