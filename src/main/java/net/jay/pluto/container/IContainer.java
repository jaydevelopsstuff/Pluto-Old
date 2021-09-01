package net.jay.pluto.container;

import net.jay.pluto.item.Item;

/** An interface for containers that contains necessary methods */
public interface IContainer {
    /** The size of this container (amount of slots) */
    int getSize();

    /** Retrieves the <code>Item</code> held in the specified slot */
    Item getItem(int slot);

    /** Sets the <code>Item</code> for the specified slot */
    void setItem(int slot, Item item);

    /** Retrieves an array of all the items in this container */
    Item[] getItems();

    /** Resets the container to it's default */
    void clear();
}
