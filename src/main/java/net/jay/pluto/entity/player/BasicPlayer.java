package net.jay.pluto.entity.player;

import net.jay.pluto.container.PlayerAccessories;
import net.jay.pluto.container.PlayerArmor;
import net.jay.pluto.container.PlayerInventory;
import net.jay.pluto.data.holders.CharacterInfo;
import net.jay.pluto.entity.Entity;
import net.jay.pluto.item.Item;

public class BasicPlayer extends Entity {
    private final int ID;

    private final String uuid;
    private final String name;
    private CharacterInfo characterInfo;

    private final PlayerInventory inventory;
    private final PlayerArmor armor;
    private final PlayerAccessories accessories;
    private Item trash;

    public BasicPlayer(int ID, String uuid, String name, CharacterInfo characterInfo, PlayerInventory inventory, PlayerArmor armor, PlayerAccessories accessories) {
        this.ID = ID;
        this.uuid = uuid;
        this.name = name;
        this.characterInfo = characterInfo;
        this.inventory = inventory;
        this.armor = armor;
        this.accessories = accessories;
    }

    public int getID() {
        return ID;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public CharacterInfo getCharacterInfo() {
        return characterInfo;
    }

    public void setCharacterInfo(CharacterInfo characterInfo) {
        this.characterInfo = characterInfo;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public PlayerArmor getArmor() {
        return armor;
    }

    public PlayerAccessories getAccessories() {
        return accessories;
    }

    public Item getTrash() {
        return trash;
    }
}
