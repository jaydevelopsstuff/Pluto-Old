package net.jay.pluto.container;

import net.jay.pluto.item.Item;

import java.util.Arrays;

public class Chest implements IContainer {
    public static final int DefaultSize = 40;

    private Item[] items;

    private String name;
    private short x;
    private short y;

    public Chest() {
        this.name = "";
        this.x = -1;
        this.y = -1;
        this.items = new Item[DefaultSize];
        Arrays.fill(items, Item.Air);
    }

    public Chest(String name) {
        this();
        this.name = name;
    }

    public Chest(String name, int x, int y) {
        this(name);
        this.x = (short)x;
        this.y = (short)y;
    }

    public Chest(String name, int x, int y, Item[] items) {
        this(name, x, y);
        this.items = items;

        // Check if length is not valid
        if(this.items.length != DefaultSize) throw new IllegalArgumentException("Items array must have length of default chest size (" + DefaultSize + ")");

        // Check for incorrect entries (null)
        for(Item item : this.items) {
            if(item == null) throw new IllegalArgumentException("Items array cannot have null item (if you want to have an empty slot use Item.Air)");
        }
    }

    @Override
    public int getSize() {
        return items.length;
    }

    @Override
    public Item getItem(int slot) {
        return items[slot];
    }

    @Override
    public void setItem(int slot, Item item) {
        if(item == null) throw new IllegalArgumentException("Item cannot be null (if you want to have an empty slot use Item.Air)");
        items[slot] = item;
    }

    @Override
    public Item[] getItems() {
        return items;
    }

    @Override
    public void clear() {
        Arrays.fill(items, Item.Air);
    }
}
