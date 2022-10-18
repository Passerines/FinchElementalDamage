package net.passerines.finch.reforge;

import net.passerines.finch.util.Chat;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemPrefix {
    private String id;
    private int tier;
    private int health;
    private int damage;
    private int defense;
    private int mana;

    public ItemPrefix(String id, int tier, int health, int damage, int defense, int mana){
        this.id = id;
        this.tier = tier;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.mana = mana;
    }

    public void applyPrefix(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Chat.formatC("stinky ").append(meta.displayName()));
        item.setItemMeta(meta);
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
    /*
    6 tiers  5 ints
    common = 0
    uncommon = 1
    rare = 2
    epic = 3
    legendary = 4
    mythic = 5
     */

}
