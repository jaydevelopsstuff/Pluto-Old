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
    private String uuid;
    private String name;
    private int HP;
    private int maxHP;
    private CharacterInfo characterInfo;
    private PlayerInventory inventory;
    private PlayerArmor armor;
    private PlayerAccessories accessories;

    public PlayerBuilder(Client client) {
        if(client.getClientID() < 0) throw new IllegalArgumentException("ID must be equal to or more than 0");
        this.ID = client.getClientID();
        this.client = client;
        this.inventory = new PlayerInventory();
        this.armor = new PlayerArmor();
        this.accessories = new PlayerAccessories();
    }

    public PlayerBuilder(int ID, Client client) {
        if(ID < 0) throw new IllegalArgumentException("ID must be equal to or more than 0");
        this.ID = ID;
        this.client = client;
        this.inventory = new PlayerInventory();
        this.armor = new PlayerArmor();
        this.accessories = new PlayerAccessories();
    }

    public Client getClient() {
        return client;
    }

    public int getID() {
        return ID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
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

    public void setInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    public PlayerArmor getArmor() {
        return armor;
    }

    public void setArmor(PlayerArmor armor) {
        this.armor = armor;
    }

    public PlayerAccessories getAccessories() {
        return accessories;
    }

    public void setAccessories(PlayerAccessories accessories) {
        this.accessories = accessories;
    }

    public BasicPlayer buildBasic() {
        return new BasicPlayer(ID, uuid, name, characterInfo, inventory, armor, accessories);
    }

    public ManageablePlayer build() {
        return new ManageablePlayer(client, buildBasic());
    }
}
