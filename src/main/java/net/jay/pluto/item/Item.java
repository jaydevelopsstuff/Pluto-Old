package net.jay.pluto.item;

// TODO Finish this
public class Item {
    public static final Item Air = new Item();

    private final int ID;
    private final String name;
    private final int maxStackSize;
    private final Availability availability = Availability.ANY;

    private int stackSize;

    public Item() {
        this.ID = Items.Air.ID;
        this.name = Items.Air.name;
        this.maxStackSize = Items.Air.maxStackSize;
        this.stackSize = this.maxStackSize;
    }

    public Item(int ID) {
        Items item = Items.fromID(ID);
        if(item == null) throw new IllegalArgumentException("Invalid ID");
        this.ID = ID;
        this.name = item.name;
        this.maxStackSize = item.maxStackSize;
        this.stackSize = this.maxStackSize;
    }

    public Item(int ID, int stackSize) {
        Items item = Items.fromID(ID);
        if(item == null) throw new IllegalArgumentException("Invalid ID");
        this.ID = ID;
        this.name = item.name;
        this.maxStackSize = item.maxStackSize;
        if(stackSize > this.maxStackSize) throw new IllegalArgumentException("Stack size cannot be more than the max stack size for this item (" + this.maxStackSize + ")");
        this.stackSize = stackSize;
    }

    public Item(Item item) {
        if(item == null) throw new IllegalArgumentException();
        this.ID = item.ID;
        this.name = item.name;
        this.maxStackSize = item.maxStackSize;
        this.stackSize = this.maxStackSize;
    }

    public Item(Item item, int stackSize) {
        if(item == null) throw new IllegalArgumentException();
        this.ID = item.ID;
        this.name = item.name;
        this.maxStackSize = item.maxStackSize;
        if(stackSize > this.maxStackSize) throw new IllegalArgumentException("Stack size cannot be more than the max stack size for this item (" + this.maxStackSize + ")");
        this.stackSize = stackSize;
    }

    public enum Availability {
        ANY,
        EXPERTONLY,
        MASTERONLY
    }
}
