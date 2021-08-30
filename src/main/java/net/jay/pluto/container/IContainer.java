package net.jay.pluto.container;

import net.jay.pluto.item.Item;

public interface IContainer {
    int getSize();

    Item getItem(int slot);

    void setItem(int slot, Item item);

    Item[] getItems();

    void clear();
}
