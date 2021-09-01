package net.jay.pluto.item;

/**
 * A class that represents a basic Terraria item
 * @see Items
 * @author Jay
 */
public class Item {
    /** Air AKA nothing */
    public static final Item Air = new Item();
    public static final Item Empty = Air;

    /** The internal ID of this item */
    protected final int ID;
    protected final Items enumType;
    /** The name of this item */
    protected final String name;
    /** The maximum stack size of this item (e.g. 999) */
    protected final int maxStackSize;
    protected final Availability availability = Availability.ANY;

    protected int stackSize;

    public Item() {
        this.ID = Items.Air.ID;
        this.name = Items.Air.name;
        this.enumType = Items.Air;
        this.maxStackSize = Items.Air.maxStackSize;
        this.stackSize = this.maxStackSize;
    }

    public Item(int ID) {
        Items item = Items.fromID(ID);
        if(item == null) throw new IllegalArgumentException("Invalid ID");
        this.ID = ID;
        this.name = item.name;
        this.enumType = item;
        this.maxStackSize = item.maxStackSize;
        this.stackSize = this.maxStackSize;
    }

    public Item(int ID, int stackSize) {
        Items item = Items.fromID(ID);
        if(item == null) throw new IllegalArgumentException("Invalid ID");
        this.ID = ID;
        this.enumType = item;
        this.name = item.name;
        this.maxStackSize = item.maxStackSize;
        if(stackSize > this.maxStackSize) throw new IllegalArgumentException("Stack size cannot be more than the max stack size for this item (" + this.maxStackSize + ")");
        this.stackSize = stackSize;
    }

    public Item(Items item) {
        if(item == null) throw new IllegalArgumentException("Invalid ID");
        this.ID = item.ID;
        this.enumType = item;
        this.name = item.name;
        this.maxStackSize = item.maxStackSize;
        this.stackSize = this.maxStackSize;
    }

    public Item(Items item, int stackSize) {
        if(item == null) throw new IllegalArgumentException();
        this.ID = item.ID;
        this.enumType = item;
        this.name = item.name;
        this.maxStackSize = item.maxStackSize;
        if(stackSize > this.maxStackSize) throw new IllegalArgumentException("Stack size cannot be more than the max stack size for this item (" + this.maxStackSize + ")");
        this.stackSize = stackSize;
    }

    public static Item initializeFlexibly(int ID, int stackSize) {
        Items item = Items.fromID(ID);
        if(item == null) throw new IllegalArgumentException("Invalid ID");
        return switch(item.type) {
            case WEAPON -> new WeaponItem(item);
            case ARMOR -> new ArmorItem(item, item.vanity);
            case ACCESSORY -> new AccessoryItem(item, item.vanity);
            default -> new Item(item, stackSize);
        };
    }

    public enum Type {
        WEAPON,
        TOOL,
        ARMOR,
        ACCESSORY,
        EQUIPMENT,
        CONSUMABLES,
        VANITY,
        BLOCKS,
        FURNITURE,
        MATERIALS,
        MISCELLANEOUS,
        /** This should only be used for the Air item */
        NONE
    }

    public enum Availability {
        ANY,
        EXPERTONLY,
        MASTERONLY
    }
}
