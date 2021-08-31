package net.jay.pluto.container;

import net.jay.pluto.item.ArmorItem;
import net.jay.pluto.item.Item;

import java.util.Arrays;

public class PlayerArmor implements IContainer {
    private static final int totalSize = 9;

    private final ArmorItem[] armor = new ArmorItem[3];
    private final ArmorItem[] vanity = new ArmorItem[3];
    private final Item[] dyes = new Item[3];

    @Override
    public int getSize() {
        return totalSize;
    }

    @Override
    public Item getItem(int slot) {
        if(slot < 0 || slot >= totalSize) throw new IllegalArgumentException("Slot number cannot be less than 0 or over the size of the container");
        return switch(slot) {
            case 0, 1, 2 -> armor[slot];
            case 3, 4, 5 -> vanity[slot - 3];
            case 6, 7, 8 -> dyes[slot - 6];
            default -> null;
        };
    }

    @Override
    public void setItem(int slot, Item item) {
        if(slot < 0 || slot >= totalSize) throw new IllegalArgumentException("Slot number cannot be less than 0 or over the size of the container");
        switch(slot) {
            case 0, 1, 2 -> {
                if(!(item instanceof ArmorItem)) throw new IllegalArgumentException("Item must be an instance of AccessoryItem");
                armor[slot] = (ArmorItem) item;
            }
            case 3, 4, 5 -> {
                if(!(item instanceof ArmorItem)) throw new IllegalArgumentException("Item must be an instance of AccessoryItem");
                vanity[slot] = (ArmorItem) item;
            }
            case 6, 7, 8 -> dyes[slot] = item;
        }
    }

    @Override
    public Item[] getItems() {
        Item[][] arrays = { armor, vanity, dyes };
        return (Item[])Arrays.stream(arrays).flatMap(Arrays::stream).toArray();
    }

    @Override
    public void clear() {
        Arrays.fill(armor, Item.Air);
        Arrays.fill(vanity, Item.Air);
        Arrays.fill(dyes, Item.Air);
    }
}
