package net.jay.pluto.util;

import net.jay.pluto.container.PlayerAccessories;
import net.jay.pluto.container.PlayerArmor;
import net.jay.pluto.container.PlayerInventory;
import net.jay.pluto.data.holders.CharacterInfo;
import net.jay.pluto.entity.player.BasicPlayer;
import net.jay.pluto.entity.player.ManageablePlayer;
import net.jay.pluto.net.Client;

public class PlayerBuilder {
    private final Client client;

    private final int ID;
    private String name;
    private CharacterInfo characterInfo;
    private PlayerInventory inventory;
    private PlayerArmor armor;
    private PlayerAccessories accessories;

    public PlayerBuilder(int ID, Client client) {
        if(ID < 0) throw new IllegalArgumentException("ID must be equal to or more than 0");
        this.ID = ID;
        this.client = client;
        this.inventory = new PlayerInventory();
        this.armor = new PlayerArmor();
        this.accessories = new PlayerAccessories();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCharacterInfo(CharacterInfo characterInfo) {
        this.characterInfo = characterInfo;
    }

    public void setInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    public void setArmor(PlayerArmor armor) {
        this.armor = armor;
    }

    public void setAccessories(PlayerAccessories accessories) {
        this.accessories = accessories;
    }

    public BasicPlayer buildBasic() {
        return new BasicPlayer(ID, name, characterInfo, inventory, armor, accessories);
    }

    public ManageablePlayer build() {
        return new ManageablePlayer(client, buildBasic());
    }
}
