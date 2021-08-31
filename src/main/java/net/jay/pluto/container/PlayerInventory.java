package net.jay.pluto.container;

import net.jay.pluto.item.Item;

import java.util.Arrays;

public class PlayerInventory implements IContainer {
    private static final int size = 59;
    private final Item[] inventory = new Item[size];

    public PlayerInventory() {
        // Unnecessary for now I think but just in case
        clear();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Item getItem(int slot) {
        if(slot < 0 || slot >= size) throw new IllegalArgumentException("Slot number cannot be less than 0 or over the size of the container");
        return inventory[slot];
    }

    @Override
    public void setItem(int slot, Item item) {
        if(slot < 0 || slot >= size) throw new IllegalArgumentException("Slot number cannot be less than 0 or over the size of the container");
        inventory[slot] = item;
    }

    @Override
    public Item[] getItems() {
        return inventory;
    }

    @Override
    public void clear() {
        Arrays.fill(inventory, Item.Air);
    }
}
