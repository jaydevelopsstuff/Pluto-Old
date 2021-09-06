package net.jay.pluto.entity.player;

import net.jay.pluto.container.PlayerAccessories;
import net.jay.pluto.container.PlayerArmor;
import net.jay.pluto.container.PlayerInventory;
import net.jay.pluto.data.holders.CharacterInfo;
import net.jay.pluto.entity.LivingEntity;
import net.jay.pluto.item.Item;

// TODO Finish heal, damage and kill logic
public class BasicPlayer extends LivingEntity {
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

    @Override
    public void kill() {
        alive = false;
        HP = 0;
    }

    @Override
    public void damage(int amount) {
        if(amount < 0) throw new IllegalArgumentException("Damage amount must be 0 or more");
        HP -= amount;
        if(HP <= 0) {
            alive = false;
            HP = 0;
        } else {

        }
    }

    @Override
    public void heal(int amount) {
        if(amount < 0) throw new IllegalArgumentException("Heal amount must be 0 or more");
        HP += amount;
        if(HP > maxHP) HP = maxHP;
    }

    @Override
    public void setHP(int HP) {
        if(HP < 0) throw new IllegalArgumentException("HP cannot be below zero");
        this.HP = Math.min(HP, maxHP);
    }

    public void healToMax() {
        setHP(maxHP);
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
