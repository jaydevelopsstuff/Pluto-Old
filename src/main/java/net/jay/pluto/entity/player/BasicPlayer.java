package net.jay.pluto.entity.player;

import lombok.Getter;
import net.jay.pluto.container.PlayerAccessories;
import net.jay.pluto.container.PlayerArmor;
import net.jay.pluto.container.PlayerInventory;
import net.jay.pluto.data.holders.CharacterInfo;
import net.jay.pluto.entity.LivingEntity;
import net.jay.pluto.item.Item;

import java.util.UUID;

// TODO Finish heal, damage and kill logic
@Getter
public class BasicPlayer extends LivingEntity {
    protected final int ID;

    protected final UUID uuid;
    protected final String name;
    protected CharacterInfo characterInfo;

    protected int mana;
    protected int maxMana;

    protected final PlayerInventory inventory;
    protected final PlayerArmor armor;
    protected final PlayerAccessories accessories;
    protected Item trash;

    public BasicPlayer(int ID, UUID uuid, String name, CharacterInfo characterInfo, int hp, int maxHp, int mana, int maxMana, PlayerInventory inventory, PlayerArmor armor, PlayerAccessories accessories) {
        this.ID = ID;
        this.uuid = uuid;
        this.name = name;
        this.characterInfo = characterInfo;
        this.hp = hp;
        this.maxHp = maxHp;
        this.mana = mana;
        this.maxMana = maxMana;
        this.inventory = inventory;
        this.armor = armor;
        this.accessories = accessories;
    }

    @Override
    public void kill() {
        hp = 0;
        alive = false;
    }

    @Override
    public void damage(int amount) {
        if(amount < 0) throw new IllegalArgumentException("Damage amount must be 0 or more");
        hp -= amount;
        if(hp <= 0) {
            alive = false;
            hp = 0;
        } else {

        }
    }

    @Override
    public void heal(int amount) {
        if(amount < 0) throw new IllegalArgumentException("Heal amount must be 0 or more");
        hp += amount;
        if(hp > maxHp) hp = maxHp;
    }

    @Override
    public void setHp(int hp) {
        if(hp < 0) throw new IllegalArgumentException("HP cannot be below zero");
        this.hp = Math.min(hp, maxHp);
    }

    public void setMana(int mana) {
        if(mana < 0) throw new IllegalArgumentException("Mana cannot be below zero");
        this.hp = Math.min(mana, maxMana);
    }
}
