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
    private int defense;
    private int mana;
    private int attack;
    private int strength;
    private int critChance;
    private int healthRegen;
    private int manaRegen;
    private int dexterity;
    private int bowDamage;
    private double fireProf;
    private double waterProf;
    private double earthProf;
    private double windProf;
    private double electroProf;
    private double lightProf;
    private double darknessProf;


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
    public ItemPrefix(String id, Component displayName, Type type, int tier){
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
    public int getHealth() {
        return health;
    }
    public ItemPrefix setHealth(int health){
        this.health = health;
        return this;
    }
    public int getDefense() {
        return defense;
    }
    public ItemPrefix setDefense(int defense){
        this.defense = defense;
        return this;
    }
    public int getAttack(){
        return attack;
    }
    public ItemPrefix setAttack(int attack){
        this.attack = attack;
        return this;
    }
    public int getStrength() {
        return strength;
    }
    public ItemPrefix setStrength(int strength){
        this.strength = strength;
        return this;
    }
    public int getCritChance() {
        return critChance;
    }
    public ItemPrefix setCritChance(int critChance){
        this.critChance = critChance;
        return this;
    }
    public int getHealthRegen() {
        return healthRegen;
    }
    public ItemPrefix setHealthRegen(int healthRegen){
        this.healthRegen = healthRegen;
        return this;
    }
    public int getManaRegen() {
        return manaRegen;
    }
    public ItemPrefix setManaRegen(int manaRegen){
        this.manaRegen = manaRegen;
        return this;
    }
    public int getDexterity() {
        return dexterity;
    }
    public ItemPrefix setDexterity(int dexterity){
        this.dexterity = dexterity;
        return this;
    }
    public int getBowDamage() {
        return bowDamage;
    }
    public ItemPrefix setBowDamage(int bowDamage){
        this.bowDamage = bowDamage;
        return this;
    }
    public int getMana(){
        return mana;
    }
    public ItemPrefix setMana(int mana){
        this.mana = mana;
        return this;
    }
    public double getFireProf() {
        return fireProf;
    }
    public ItemPrefix setFireProf(double fireProf){
        this.fireProf = fireProf;
        return this;
    }
    public double getWaterProf() {
        return waterProf;
    }
    public ItemPrefix setWaterProf(double waterProf){
        this.waterProf = waterProf;
        return this;
    }
    public double getEarthProf() {
        return earthProf;
    }
    public ItemPrefix setEarthProf(double earthProf){
        this.earthProf = earthProf;
        return this;
    }
    public double getWindProf() {
        return windProf;
    }
    public ItemPrefix setWindProf(double windProf){
        this.windProf = windProf;
        return this;
    }
    public double getElectroProf() {
        return electroProf;
    }
    public ItemPrefix setElectroProf(double electroProf){
        this.electroProf = electroProf;
        return this;
    }
    public double getLightProf() {
        return lightProf;
    }
    public ItemPrefix setLightProf(double lightProf){
        this.lightProf = lightProf;
        return this;
    }
    public double getDarknessProf() {
        return darknessProf;
    }
    public ItemPrefix setDarknessProf(double darknessProf){
        this.darknessProf = darknessProf;
        return this;
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
