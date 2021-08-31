package net.jay.pluto.item;

public class ArmorItem extends AccessoryItem {
    public ArmorItem(int ID, boolean vanity) {
        super(ID, vanity);
        if(enumType.type != Type.ARMOR) throw new IllegalArgumentException("Item must be type of ARMOR");
    }

    public ArmorItem(Items item, boolean vanity) {
        super(item, vanity);
        if(item.type != Type.ARMOR) throw new IllegalArgumentException("Item must be type of ARMOR");
    }
}
