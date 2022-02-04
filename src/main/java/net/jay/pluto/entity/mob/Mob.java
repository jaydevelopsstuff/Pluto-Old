package net.jay.pluto.entity.mob;

import net.jay.pluto.entity.LivingEntity;

public class Mob extends LivingEntity {
    private int spriteID;
    private String name;

    public int getSpriteID() {
        return spriteID;
    }

    public void setSpriteID(int spriteID) {
        this.spriteID = spriteID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void kill() {
        alive = false;
        hp = 0;
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
}
