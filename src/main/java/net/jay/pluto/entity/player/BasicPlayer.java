package net.jay.pluto.entity.player;

import net.jay.pluto.container.PlayerAccessories;
import net.jay.pluto.container.PlayerArmor;
import net.jay.pluto.container.PlayerInventory;
import net.jay.pluto.entity.Entity;
import net.jay.pluto.item.Item;

public class BasicPlayer extends Entity {
    private int ID;

    private final PlayerInventory inventory;
    private final PlayerArmor armor;
    private final PlayerAccessories accessories;
    private Item trash;

    public BasicPlayer(int ID, PlayerInventory inventory, PlayerArmor armor, PlayerAccessories accessories) {
        this.ID = ID;
        this.inventory = inventory;
        this.armor = armor;
        this.accessories = accessories;
    }

    public int getID() {
        return ID;
    }

    /**
     * Sets the ID for this player, the ID of the player and the <code>Client</code> attached to it should always be synchronized
     * @param ID The ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
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
