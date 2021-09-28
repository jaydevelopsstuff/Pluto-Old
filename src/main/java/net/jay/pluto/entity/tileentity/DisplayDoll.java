package net.jay.pluto.entity.tileentity;

import net.jay.pluto.item.Item;

public class DisplayDoll extends TileEntity {
    private Item[] items;
    private Item[] dyes;

    public DisplayDoll(int ID, short x, short y, Item[] items, Item[] dyes) {
        super(ID, x, y);
        this.items = items;
        this.dyes = dyes;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Item[] getDyes() {
        return dyes;
    }

    public void setDyes(Item[] dyes) {
        this.dyes = dyes;
    }
}
