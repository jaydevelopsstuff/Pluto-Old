package net.jay.pluto.entity.tileentity;

import net.jay.pluto.item.Item;

public class ItemFrame extends TileEntity {
    private Item containedItem;

    public ItemFrame(int ID, short x, short y, Item containedItem) {
        super(ID, x, y);
        this.containedItem = containedItem;
    }

    public Item getContainedItem() {
        return containedItem;
    }

    public void setContainedItem(Item containedItem) {
        this.containedItem = containedItem;
    }
}
