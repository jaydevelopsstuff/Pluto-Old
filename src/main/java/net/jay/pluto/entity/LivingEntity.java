package net.jay.pluto.entity;

public abstract class LivingEntity extends Entity {
    protected boolean alive;
    protected int HP;
    protected int maxHP;

    public abstract void kill();

    public abstract void damage(int amount);

    public abstract void heal(int amount);

    public abstract void setHP(int HP);

    public boolean isAlive() {
        return alive;
    }

    public int getHP() {
        return HP;
    }

    public int getMaxHP() {
        return maxHP;
    }
}
