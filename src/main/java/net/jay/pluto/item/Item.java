package net.jay.pluto.item;

import lombok.Getter;
import lombok.Setter;

/**
 * A class that represents a basic Terraria item
 * @see Items
 * @author Jay
 */
@Getter
public class Item {
    /** Air, nothing */
    public static final Item Air = new Item();
    public static final Item Empty = Air;

    /** The internal ID of this item */
    protected final int ID;
    /**
     * The enum equivalent of this item
     * @see Items
     */
    protected final Items enumType;
    // TODO: Convert to enum
    protected final int prefix;
    /** The name of this item */
    protected String name;
    /** The maximum stack size of this item (e.g. 999) */
    protected int maxStackSize;
    protected final Availability availability = Availability.ANY;

    @Setter
    protected int stackSize;

    public Item() {
        this.ID = Items.Air.ID;
        this.enumType = Items.Air;
        this.prefix = 0;
        this.name = Items.Air.name;
        this.maxStackSize = Items.Air.maxStackSize;
        this.stackSize = this.maxStackSize;
    }

    public Item(int ID, int prefix) {
        Items item = Items.fromID(ID);
        if(item == null) throw new IllegalArgumentException("Invalid ID");
        this.ID = ID;
        this.enumType = item;
        this.prefix = prefix;
        this.name = item.name;
        this.maxStackSize = item.maxStackSize;
        this.stackSize = this.maxStackSize;
    }

    public Item(int ID, int prefix, int stackSize) {
        // TODO Remove temp fix once Items enum is done
        Items item = Items.fromID(ID);
        //if(item == null) throw new IllegalArgumentException("Invalid ID");
        this.ID = ID;
        this.enumType = item;
        this.prefix = prefix;
        //this.name = item.name;
        //this.maxStackSize = item.maxStackSize;
        //if(stackSize > this.maxStackSize) throw new IllegalArgumentException("Stack size cannot be more than the max stack size for this item (" + this.maxStackSize + ")");
        this.stackSize = stackSize;
    }

    public Item(Items item, int prefix) {
        if(item == null) throw new IllegalArgumentException("Item cannot be null");
        this.ID = item.ID;
        this.enumType = item;
        this.prefix = prefix;
        this.name = item.name;
        this.maxStackSize = item.maxStackSize;
        this.stackSize = this.maxStackSize;
    }

    public Item(Items item, int prefix, int stackSize) {
        if(item == null) throw new IllegalArgumentException();
        this.ID = item.ID;
        this.enumType = item;
        this.prefix = prefix;
        this.name = item.name;
        this.maxStackSize = item.maxStackSize;
        if(stackSize > this.maxStackSize) throw new IllegalArgumentException("Stack size cannot be more than the max stack size for this item (" + this.maxStackSize + ")");
        this.stackSize = stackSize;
    }

    public static Item initializeFlexibly(int ID, int prefix, int stackSize) {
        Items item = Items.fromID(ID);
        if(item == null) throw new IllegalArgumentException("Invalid ID");
        return switch(item.type) {
            case WEAPON -> new WeaponItem(item, prefix);
            case ARMOR -> new ArmorItem(item, prefix, item.vanity);
            case ACCESSORY -> new AccessoryItem(item, prefix, item.vanity);
            default -> new Item(item, prefix, stackSize);
        };
    }

    public enum Type {
        WEAPON,
        TOOL,
        ARMOR,
        ACCESSORY,
        EQUIPMENT,
        CONSUMABLE,
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
