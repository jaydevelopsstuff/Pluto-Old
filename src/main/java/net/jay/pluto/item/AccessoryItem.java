package net.jay.pluto.item;

public class AccessoryItem extends Item {
    private final boolean vanity;

    public AccessoryItem(int ID, boolean vanity) {
        super(ID);
        if(enumType.type != Type.ARMOR && enumType.type != Type.ACCESSORY) throw new IllegalArgumentException("Item must be type of ARMOR");
        this.vanity = vanity;
    }

    public AccessoryItem(Items item, boolean vanity) {
        super(item);
        if(item.type != Type.ARMOR && item.type != Type.ACCESSORY) throw new IllegalArgumentException("Item must be type of ARMOR");
        this.vanity = vanity;
    }
}
