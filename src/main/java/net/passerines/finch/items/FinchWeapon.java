package net.passerines.finch.items;

import org.bukkit.inventory.ItemStack;

public abstract class FinchWeapon extends FinchItem {

    protected double health;
    protected int defense;
    protected int damage;
    protected int mana;

    public FinchWeapon(String id) {
        super(id);
        this.health = 0;
        this.defense = 0;
        this.damage = 0;
        this.mana = 0;
    }

    public double getHealth() {
        return health;
    }
    public int getDefense() {
        return defense;
    }
    public int getDamage(){
        return damage;
    }
    public int getMana(){
        return mana;
    }
}