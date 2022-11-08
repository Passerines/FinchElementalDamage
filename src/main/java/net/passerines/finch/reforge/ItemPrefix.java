package net.passerines.finch.reforge;

import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemPrefix {
    private String id;
    private String displayName;
    private Type type;
    private int tier;
    private int health;
    private int damage;
    private int defense;
    private int mana;

    enum Type{
        WEAPON,
        ARMOR,
        TRINKET
    }
    public ItemPrefix(String id, String displayName, Type type, int tier, int health, int damage, int defense, int mana){
        this.id = id;
        this.displayName = displayName;
        this.type = type;
        this.tier = tier;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.mana = mana;
        PrefixManager.PREFIX_HASH_MAP.put(id, this);
    }

    public void applyPrefix(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(Util.getNamespacedKey("prefix"), PersistentDataType.STRING, id);
        meta.displayName(Chat.formatC(displayName + " ").append(meta.displayName()));
        item.setItemMeta(meta);
    }

    public void removePrefix(ItemStack item){
        String id = Util.getId(item);
        ItemStack origItem = ItemManager.ITEM_HASH_MAP.get(id).getItem();
        ItemMeta meta = origItem.getItemMeta();
        meta.getPersistentDataContainer().remove(Util.getNamespacedKey("prefix"));
        meta.displayName(meta.displayName());
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
