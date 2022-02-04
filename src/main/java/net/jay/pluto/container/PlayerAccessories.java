package net.jay.pluto.container;

import net.jay.pluto.item.AccessoryItem;
import net.jay.pluto.item.ArmorItem;
import net.jay.pluto.item.Item;

import java.util.Arrays;

public class PlayerAccessories implements IContainer {
    private static final int totalSize = 18;

    private final AccessoryItem[] accessories = new AccessoryItem[6];
    private final AccessoryItem[] vanity = new AccessoryItem[6];
    private final Item[] dyes = new Item[6];

    @Override
    public int getSize() {
        return totalSize;
    }

    @Override
    public Item getItem(int slot) {
        if(slot < 0 || slot >= totalSize) throw new IllegalArgumentException("Slot number cannot be less than 0 or over the size of the container");
        return switch(slot) {
            case 0, 1, 2 -> accessories[slot];
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
                if(!(item instanceof AccessoryItem)) throw new IllegalArgumentException("Item must be an instance of AccessoryItem");
                accessories[slot] = (AccessoryItem)item;
            }
            case 3, 4, 5 -> {
                if(!(item instanceof AccessoryItem)) throw new IllegalArgumentException("Item must be an instance of AccessoryItem");
                vanity[slot] = (AccessoryItem)item;
            }
            case 6, 7, 8 -> dyes[slot] = item;
        }
    }

    @Override
    public Item[] getItems() {
        Item[][] arrays = { accessories, vanity, dyes };
        return (Item[])Arrays.stream(arrays).flatMap(Arrays::stream).toArray();
    }

    @Override
    public void clear() {
        Arrays.fill(accessories, Item.Air);
        Arrays.fill(vanity, Item.Air);
        Arrays.fill(dyes, Item.Air);
    }
}
