package net.passerines.finch.items;

import org.bukkit.inventory.ItemStack;

public abstract class FinchArmor extends FinchItem {

    protected double health;
    protected int defense;
    protected int damage;

    public FinchArmor(String id) {
        super(id);
        this.health = 0;
        this.defense = 0;
        this.damage = 0;
    }

    public double getHealth() {
        return health;
    }
    public int getDefense() {
        return defense;
    }
    public int getDamage(){return damage;}
}
