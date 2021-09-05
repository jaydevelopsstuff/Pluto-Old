package net.jay.pluto.item;

public class WeaponItem extends Item {
    public WeaponItem(int ID, int prefix) {
        super(ID, prefix);
        if(enumType.type != Type.WEAPON) throw new IllegalArgumentException("Item must be type of ARMOR");
    }

    public WeaponItem(Items item, int prefix) {
        super(item, prefix);
        if(item.type != Type.WEAPON) throw new IllegalArgumentException("Item must be type of ARMOR");
    }
}
