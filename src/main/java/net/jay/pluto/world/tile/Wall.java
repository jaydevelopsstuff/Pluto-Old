package net.jay.pluto.world.tile;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jay
 */
@Getter
@Setter
public class Wall {
    private short ID;

    private byte color;

    public Wall(short ID) {
        this.ID = ID;
    }

    public Wall(Walls wall) {
        // this(wall.ID);
    }

    public boolean sameAs(Wall wall) {
        if(wall == null) return false;
        return ID == wall.ID && color == wall.color;
    }
}
