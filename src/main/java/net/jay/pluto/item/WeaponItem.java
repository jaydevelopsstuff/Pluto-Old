package net.jay.pluto.item;

public class WeaponItem extends Item {
    public WeaponItem(int ID) {
        super(ID);
    }

    public WeaponItem(Items item) {
        super(item);
        if(item.type != Type.WEAPON) throw new IllegalArgumentException("Item must be type of ARMOR");
    }
}
