package net.passerines.finch.enchants;

import net.kyori.adventure.text.Component;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.reforge.ItemPrefix;
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

    public ItemEnchants(String id, Component displayName, EnchantmentType type, int health, int attack, int defense, int mana){
        this.id = id;
        this.displayName = displayName;
        this.type = type;
        this.health = health;
        this.attack = attack;
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

    public int getHealth(int level) {
        return (int) (health * (1+(level-1)*0.2));
    }

    public ItemEnchants setHealth(int health){
        this.health = health;
        return this;
    }
    public int getDefense(int level) {
        return (int) (defense * (1+(level-1)*0.2));
    }
    public ItemEnchants setDefense(int defense){
        this.defense = defense;
        return this;
    }
    public int getAttack(int level){
        return (int) (attack * (1+(level-1)*0.2));
    }
    public ItemEnchants setAttack(int attack){
        this.attack = attack;
        return this;
    }
    public int getStrength(int level) {
        return (int) (strength * (1+(level-1)*0.2));
    }
    public ItemEnchants setStrength(int strength){
        this.strength = strength;
        return this;
    }
    public int getCritChance(int level) {
        return (int) (critChance * (1+(level-1)*0.2));
    }
    public ItemEnchants setCritChance(int critChance){
        this.critChance = critChance;
        return this;
    }
    public int getHealthRegen(int level) {
        return (int) (healthRegen * (1+(level-1)*0.2));
    }
    public ItemEnchants setHealthRegen(int healthRegen){
        this.healthRegen = healthRegen;
        return this;
    }
    public int getManaRegen(int level) {
        return (int) (manaRegen * (1+(level-1)*0.2));
    }
    public ItemEnchants setManaRegen(int manaRegen){
        this.manaRegen = manaRegen;
        return this;
    }
    public int getDexterity(int level) {
        return (int) (dexterity * (1+(level-1)*0.2));
    }
    public ItemEnchants setDexterity(int dexterity){
        this.dexterity = dexterity;
        return this;
    }
    public int getBowDamage(int level) {
        return (int) (bowDamage * (1+(level-1)*0.2));
    }
    public ItemEnchants setBowDamage(int bowDamage){
        this.bowDamage = bowDamage;
        return this;
    }
    public int getMana(int level){
        return (int) (mana * (1+(level-1)*0.2));
    }
    public ItemEnchants setMana(int mana){
        this.mana = mana;
        return this;
    }
    public double getFireProf(int level) {
        return (int) (fireProf * (1+(level-1)*0.2));
    }
    public ItemEnchants setFireProf(double fireProf){
        this.fireProf = fireProf;
        return this;
    }
    public double getWaterProf(int level) {
        return (int) (waterProf * (1+(level-1)*0.2));
    }
    public ItemEnchants setWaterProf(double waterProf){
        this.waterProf = waterProf;
        return this;
    }
    public double getEarthProf(int level) {
        return (int) (earthProf * (1+(level-1)*0.2));
    }
    public ItemEnchants setEarthProf(double earthProf){
        this.earthProf = earthProf;
        return this;
    }
    public double getWindProf(int level) {
        return (int) (windProf * (1+(level-1)*0.2));
    }
    public ItemEnchants setWindProf(double windProf){
        this.windProf = windProf;
        return this;
    }
    public double getElectroProf(int level) {
        return (int) (electroProf * (1+(level-1)*0.2));
    }
    public ItemEnchants setElectroProf(double electroProf){
        this.electroProf = electroProf;
        return this;
    }
    public double getLightProf(int level) {
        return (int) (lightProf * (1+(level-1)*0.2));
    }
    public ItemEnchants setLightProf(double lightProf){
        this.lightProf = lightProf;
        return this;
    }
    public double getDarknessProf(int level) {
        return (int) (darknessProf * (1+(level-1)*0.2));
    }
    public ItemEnchants setDarknessProf(double darknessProf){
        this.darknessProf = darknessProf;
        return this;
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
