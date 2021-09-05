package net.jay.pluto.item;

// TODO Add items (this is a great thing for pull requests right now
/**
 * An enum containing all the items in Terraria
 * @see Item
 */
public enum Items {
    /** This represents an empty slot/item */
    Air(0, "Air", Item.Type.NONE, 0),
    Mushroom(5, "Mushroom", Item.Type.CONSUMABLE, StandardStackSizes.Thirty),
    CopperBow(3504, "Copper Bow", Item.Type.WEAPON, StandardStackSizes.Single),
    CopperHammer(3505, "Copper Hammer", Item.Type.TOOL, StandardStackSizes.Single),
    CopperAxe(3506, "Copper Axe", Item.Type.TOOL, StandardStackSizes.Single),
    CopperShortsword(3507, "Copper Shortsword", Item.Type.WEAPON, StandardStackSizes.Single),
    CopperBroadsword(3508, "Copper Broadsword", Item.Type.WEAPON, StandardStackSizes.Single),
    CopperPickaxe(3509, "Copper Pickaxe", Item.Type.TOOL, StandardStackSizes.Single),
    Zenith(4956, "Zenith", Item.Type.WEAPON, StandardStackSizes.Single),
    DarkSideHallow(5087, "Dark Side of the Hallow", Item.Type.FURNITURE, StandardStackSizes.NinetyNine);

    /** The ID of this item */
    public final int ID;
    /** The name of this item */
    public final String name;
    /**
     * The type of this item (Weapon, Tool, Accessory, etc.)
     * @see Item.Type
     */
    public final Item.Type type;
    /** The max stack size for this item (e.g. 999) */
    public final int maxStackSize;
    /** Whether this item is a vanity item or not, this is needed for accessories/armor implementation */
    public final boolean vanity;
    /**
     * The availability of this item
     * @see Item.Availability
     */
    public final Item.Availability availability;

    Items(int ID, String name, Item.Type type, int maxStackSize) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.maxStackSize = maxStackSize;
        this.vanity = false;
        this.availability = Item.Availability.ANY;
    }

    Items(int ID, String name, Item.Type type, int maxStackSize, boolean vanity) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.maxStackSize = maxStackSize;
        this.vanity = vanity;
        this.availability = Item.Availability.ANY;
    }

    Items(int ID, String name, Item.Type type, int maxStackSize, Item.Availability availability) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.maxStackSize = maxStackSize;
        this.vanity = false;
        this.availability = availability;
    }

    Items(int ID, String name, Item.Type type, int maxStackSize, Item.Availability availability, boolean vanity) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.maxStackSize = maxStackSize;
        this.vanity = vanity;
        this.availability = availability;
    }

    public static Items fromID(int ID) {
        for(Items item : values()) {
            if(item.ID == ID) return item;
        }
        return null;
    }
}
