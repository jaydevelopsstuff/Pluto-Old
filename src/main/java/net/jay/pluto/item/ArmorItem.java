package net.jay.pluto.item;

public class ArmorItem extends AccessoryItem {
    public ArmorItem(int ID, int prefix, boolean vanity) {
        super(ID, prefix, vanity);
        if(enumType.type != Type.ARMOR) throw new IllegalArgumentException("Item must be type of ARMOR");
    }

    public ArmorItem(Items item, int prefix, boolean vanity) {
        super(item, prefix, vanity);
        if(item.type != Type.ARMOR) throw new IllegalArgumentException("Item must be type of ARMOR");
    }
}
