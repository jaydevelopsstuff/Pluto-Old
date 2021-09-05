package net.jay.pluto.item;

public class AccessoryItem extends Item {
    private final boolean vanity;

    public AccessoryItem(int ID, int prefix, boolean vanity) {
        super(ID, prefix);
        if(enumType.type != Type.ARMOR && enumType.type != Type.ACCESSORY) throw new IllegalArgumentException("Item must be type of ARMOR/ACCESSORY");
        this.vanity = vanity;
    }

    public AccessoryItem(Items item, int prefix, boolean vanity) {
        super(item, prefix);
        if(item.type != Type.ARMOR && item.type != Type.ACCESSORY) throw new IllegalArgumentException("Item must be type of ARMOR/ACCESSORY");
        this.vanity = vanity;
    }
}
