package net.jay.pluto.entity.npc;

import net.jay.pluto.entity.LivingEntity;

public class NPC extends LivingEntity {
    private int spriteID;
    private String name;

    private String displayName;

    private boolean homeless;

    // Make a home object perhaps?
    private short homeX;
    private short homeY;

    // Happiness I think
    private int townNpcVariationIndex;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isHomeless() {
        return homeless;
    }

    public void setHomeless(boolean homeless) {
        this.homeless = homeless;
    }

    public short getHomeX() {
        return homeX;
    }

    public void setHomeX(int homeX) {
        this.homeX = (short)homeX;
    }

    public short getHomeY() {
        return homeY;
    }

    public void setHomeY(int homeY) {
        this.homeY = (short)homeY;
    }

    public int getTownNpcVariationIndex() {
        return townNpcVariationIndex;
    }

    public void setTownNpcVariationIndex(int townNpcVariationIndex) {
        this.townNpcVariationIndex = townNpcVariationIndex;
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
