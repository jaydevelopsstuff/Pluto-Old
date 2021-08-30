package net.jay.pluto.container;

import net.jay.pluto.item.ArmorItem;
import net.jay.pluto.item.Item;

public class PlayerAccessories implements IContainer {
    private static final int totalSize = 18;

    private final ArmorItem[] accessories = new ArmorItem[6];
    private final ArmorItem[] vanity = new ArmorItem[6];
    private final Item[] dyes = new Item[6];

    @Override
    public int getSize() {
        return totalSize;
    }

    @Override
    public Item getItem(int slot) {
        return null;
    }

    @Override
    public void setItem(int slot, Item item) {

    }

    @Override
    public Item[] getItems() {
        return new Item[0];
    }

    @Override
    public void clear() {

    }
}
