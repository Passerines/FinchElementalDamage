package net.passerines.finch.enchants;

import net.kyori.adventure.text.Component;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class ItemEnchants {
    private String id;
    private Component displayName;
    private EnchantmentType type;
    private int health;
    private int damage;
    private int defense;
    private int mana;

    public enum EnchantmentType{
        WEAPON,
        ARMOR,
        NONE
    }

    public ItemEnchants(String id, Component displayName, EnchantmentType type){
        this(id, displayName, type, 0,0,0,0);
    }

    public ItemEnchants(String id, String displayName, EnchantmentType type){
        this(id, Chat.formatC(displayName), type, 0, 0, 0, 0);
    }

    public ItemEnchants(String id, String displayName, EnchantmentType type, int health, int damage, int defense, int mana){
        this(id, Chat.formatC(displayName), type, health, damage, defense, mana);
    }

    public ItemEnchants(String id, Component displayName, EnchantmentType type, int health, int damage, int defense, int mana){
        this.id = id;
        this.displayName = displayName;
        this.type = type;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.mana = mana;
        EnchantManager.ENCHANTS_HASH_MAP.put(id, this);
    }

//get string of enchantment
// split it into arrays of enchantment
// check to make sure the enchant isin't already on there
// put it back into the string, adding the new enchantment/level

    public void applyEnchant(ItemStack item, int level){
        HashMap<String, Integer> enchantList = Util.getEnchants(item);
        enchantList.put(id, level);
        String itemString = Util.getEnchantString(enchantList);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(Util.getNamespacedKey("enchant"), PersistentDataType.STRING, itemString);
        item.setItemMeta(meta);
        Util.getFinchItem(item).format(item);
    }

    public void removeEnchant(ItemStack item){
        HashMap<String, Integer> enchantList = Util.getEnchants(item);
        enchantList.remove(id);
        String itemString = Util.getEnchantString(enchantList);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(Util.getNamespacedKey("enchant"), PersistentDataType.STRING, itemString);
        item.setItemMeta(meta);
        Util.getFinchItem(item).format(item);
    }
    //This is just where we apply the effect
    public void onElementalDamage(ElementalDamageEvent event, int level){

    }

    public Component getDisplayName() {
        return displayName;
    }

    public EnchantmentType getType(){
        return type;
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
