package net.jay.pluto.entity;

import lombok.Getter;

@Getter
public abstract class LivingEntity extends Entity {
    protected boolean alive;
    protected int hp;
    protected int maxHp;

    public abstract void kill();

    public abstract void damage(int amount);

    public abstract void heal(int amount);

    public abstract void setHp(int hp);

    public void healToMax() {
        setHp(maxHp);
    }
}
