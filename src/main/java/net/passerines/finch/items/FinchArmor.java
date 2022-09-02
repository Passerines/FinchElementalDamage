package net.passerines.finch.items;

import org.bukkit.inventory.ItemStack;

public abstract class FinchArmor extends FinchItem {

    protected int health;
    protected int defense;

    public FinchArmor(String id) {
        super(id);
        this.health = 0;
        this.defense = 0;
    }

    public int getHealth() {
        return health;
    }
    public int getDefense() {
        return defense;
    }
}
