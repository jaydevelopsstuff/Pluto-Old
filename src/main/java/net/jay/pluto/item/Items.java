package net.jay.pluto.item;

public enum Items {
    Air(0, "Air", 0),
    DarkSideHallow(5087, "Dark Side of the Hallow", 99, Item.Availability.ANY); // Might have wrong stack size rn

    public final int ID;
    public final String name;
    public final int maxStackSize;
    public final Item.Availability availability;

    Items(int ID, String name, int maxStackSize) {
        this.ID = ID;
        this.name = name;
        this.maxStackSize = maxStackSize;
        this.availability = Item.Availability.ANY;
    }

    Items(int ID, String name, int maxStackSize, Item.Availability availability) {
        this.ID = ID;
        this.name = name;
        this.maxStackSize = maxStackSize;
        this.availability = availability;
    }

    public static Items fromID(int ID) {
        for(Items item : values()) {
            if(item.ID == ID) return item;
        }
        return null;
    }
}
