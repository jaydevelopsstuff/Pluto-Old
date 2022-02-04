package net.jay.pluto.world.sign;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Sign {
    @Setter
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
}
