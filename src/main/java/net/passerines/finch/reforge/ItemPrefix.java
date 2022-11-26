package net.passerines.finch.reforge;

import net.kyori.adventure.text.Component;
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
    private Component displayName;
    private Type type;
    private int tier;
    private int health;
    private int attack;
    private int strength;
    private int critChance;
    private int healthRegen;
    private int manaRegen;
    private int dexterity;
    private int bowDamage;
    private int defense;



    private int mana;

    public enum Type{
        WEAPON,
        ARMOR,
        TRINKET,
        NONE
    }

    public ItemPrefix(String id, String displayName, Type type, int tier, int health, int attack, int strength, int defense, int mana){
        this(id, Chat.formatC(displayName), type, tier, health, attack, strength, defense, mana);
    }

    public ItemPrefix(String id, Component displayName, Type type, int tier, int health, int attack, int strength, int defense, int mana){
        this.id = id;
        this.displayName = displayName;
        this.type = type;
        this.tier = tier;
        this.health = health;
        this.attack = attack;
        this.strength = strength;
        this.defense = defense;
        this.mana = mana;
        PrefixManager.PREFIX_HASH_MAP.put(id, this);
    }
    public ItemPrefix(String id, Component displayName, Type type){
        this.id = id;
        this.displayName = displayName;
        this.type = type;
        this.tier = 0;
        this.health = 0;
        this.attack = 0;
        this.strength = 0;
        this.defense = 0;
        this.mana = 0;
        PrefixManager.PREFIX_HASH_MAP.put(id, this);
    }


    public void applyPrefix(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(Util.getNamespacedKey("prefix"), PersistentDataType.STRING, id);
        item.setItemMeta(meta);
        Util.getFinchItem(item).format(item);
    }

    public void removePrefix(ItemStack item){
        String id = Util.getId(item);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().remove(Util.getNamespacedKey("prefix"));
        item.setItemMeta(meta);
        Util.getFinchItem(item).format(item);
    }

    public Component getDisplayName() {
        return displayName;
    }

    public Type getType(){
        return type;
    }
    public double getHealth() {
        return health;
    }
    public int getDefense() {
        return defense;
    }
    public int getAttack(){
        return attack;
    }
    public int getStrength() {
        return strength;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getHealthRegen() {
        return healthRegen;
    }

    public int getManaRegen() {
        return manaRegen;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getBowDamage() {
        return bowDamage;
    }
    public int getMana(){
        return mana;
    }
    public int getTier(){
        return tier;
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
