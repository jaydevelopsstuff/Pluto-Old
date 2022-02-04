package net.jay.pluto.world;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PressurePlate {
    private short x;
    private short y;

    public PressurePlate(int x, int y) {
        this.x = (short)x;
        this.y = (short)y;
    }
}
