package net.passerines.finch.players;

import net.passerines.finch.enchants.ItemEnchant;
import net.passerines.finch.events.HealthDisplay;
import net.passerines.finch.items.*;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.reforge.ItemPrefix;
import net.passerines.finch.reforge.PrefixManager;
import net.passerines.finch.reforge.ReforgeMenu;
import net.passerines.finch.trinkets.TrinketMenu;
import net.passerines.finch.util.Util;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.Prefix;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerData {

    private Player player;
    private PlayerConfig playerConfig;
    private TrinketMenu trinketMenu;
    private ReforgeMenu reforgeMenu;

    private double health;
    private double healthMax;
    private int defense;
    private int mana;
    private int manaMax;
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
    private ItemStack oldItem;

    private ItemStack[] oldTrinkets = new ItemStack[3];


    public PlayerData(Player player){
        this.player = player;
        playerConfig = new PlayerConfig(this);
        player.setHealthScaled(true);
        defense = 10;
        reset();
        /*ItemStack helmet = player.getInventory().getHelmet();
        calculate(helmet);
        ItemStack chestplate = player.getInventory().getChestplate();
        calculate(chestplate);
        ItemStack leggings = player.getInventory().getLeggings();
        calculate(leggings);
        ItemStack boots = player.getInventory().getBoots();
        calculate(boots);
        */
        trinketMenu = new TrinketMenu();

        for(int i = 0; i < 3; i++) {
            String path = "Player.Menu.Trinket" + i;
            trinketMenu.getMenu().setItem(i + 3, playerConfig.getConfig().getItemStack(path));
            calculateAccessory(trinketMenu.getMenu().getItem(i+3), i);
        }

        oldItem = player.getInventory().getItemInMainHand().clone();
        if(ItemManager.ITEM_HASH_MAP.get(Util.getId(oldItem)) instanceof FinchWeapon) {
            calculate(oldItem);
        }
        oldTrinkets[0] = trinketMenu.getMenu().getItem(3);
        oldTrinkets[1] = trinketMenu.getMenu().getItem(4);
        oldTrinkets[2] = trinketMenu.getMenu().getItem(5);
        health = playerConfig.getConfig().getDouble("Player.Health", 100);
        mana = playerConfig.getConfig().getInt("Player.Mana", 100);
    }

    public void calculatePiece(ItemStack oldItem, ItemStack newItem) {
        uncalculate(oldItem);
        calculate(newItem);
        HealthDisplay.updateActionBar(player);
    }

    public void calculateHand(ItemStack newItem) {
        if(ItemManager.ITEM_HASH_MAP.get(Util.getId(oldItem)) instanceof FinchWeapon){
            uncalculate(oldItem);
        }
        if (ItemManager.ITEM_HASH_MAP.get(Util.getId(newItem)) instanceof FinchWeapon){
            calculate(newItem);
        }
        oldItem = newItem!=null ? newItem.clone() : null;
    }
    public void calculateAccessory(ItemStack newItem, int index){
        if(oldTrinkets[index] != null && ItemManager.ITEM_HASH_MAP.get(Util.getId(oldTrinkets[index])) instanceof FinchTrinkets) {
            uncalculate(oldTrinkets[index]);
        }
        if(newItem != null && ItemManager.ITEM_HASH_MAP.get(Util.getId(newItem)) instanceof FinchTrinkets) {
            calculate(newItem);
        }
        HealthDisplay.updateActionBar(player);
        oldTrinkets[index] = newItem;
    }

    //Calculate individual armor/trinket pieces
    private void calculate(ItemStack item) {
        String id = Util.getId(item);
        String prefix = Util.getPrefix(item);
        if(!Util.getItemEnchants(item).isEmpty()){
            HashMap<ItemEnchant, Integer> enchantHashMap = Util.getItemEnchants(item);
            for(ItemEnchant enchant : enchantHashMap.keySet()){
                setDefense((defense + enchant.getDefense(enchantHashMap.get(enchant))));
                setHealthMax(healthMax + enchant.getHealth(enchantHashMap.get(enchant)));
                setAttack((attack + enchant.getAttack(enchantHashMap.get(enchant))));
                setStrength((strength + enchant.getStrength(enchantHashMap.get(enchant))));
                setCritChance((critChance + enchant.getCritChance(enchantHashMap.get(enchant))));
                setHealthRegen((healthRegen + enchant.getHealthRegen(enchantHashMap.get(enchant))));
                setManaRegen((manaRegen + enchant.getManaRegen(enchantHashMap.get(enchant))));
                setBowDamage((bowDamage + enchant.getBowDamage(enchantHashMap.get(enchant))));
                setDexterity((dexterity + enchant.getDexterity(enchantHashMap.get(enchant))));
                setManaMax((manaMax + enchant.getMana(enchantHashMap.get(enchant))));
                setDarknessProf(darknessProf + enchant.getDarknessProf(enchantHashMap.get(enchant)));
                setEarthProf(earthProf + enchant.getEarthProf(enchantHashMap.get(enchant)));
                setElectroProf(electroProf + enchant.getElectroProf(enchantHashMap.get(enchant)));
                setFireProf(fireProf + enchant.getFireProf(enchantHashMap.get(enchant)));
                setWaterProf(waterProf + enchant.getWaterProf(enchantHashMap.get(enchant)));
                setLightProf(lightProf + enchant.getLightProf(enchantHashMap.get(enchant)));
                setWindProf(windProf + enchant.getWindProf(enchantHashMap.get(enchant)));
            }
        }
        if (PrefixManager.PREFIX_HASH_MAP.containsKey(prefix)) {
            ItemPrefix itemPrefix = PrefixManager.PREFIX_HASH_MAP.get(prefix);
            setDefense(defense + itemPrefix.getDefense());
            setHealthMax(healthMax + itemPrefix.getHealth());
            setAttack(attack + itemPrefix.getAttack());
            setStrength(strength + itemPrefix.getStrength());
            setCritChance(critChance + itemPrefix.getCritChance());
            setHealthRegen(healthRegen + itemPrefix.getHealthRegen());
            setManaRegen(manaRegen + itemPrefix.getManaRegen());
            setBowDamage(bowDamage + itemPrefix.getBowDamage());
            setDexterity(dexterity + itemPrefix.getDexterity());
            setManaMax(manaMax + itemPrefix.getMana());
            setDarknessProf(darknessProf + itemPrefix.getDarknessProf());
            setEarthProf(earthProf + itemPrefix.getEarthProf());
            setElectroProf(electroProf + itemPrefix.getElectroProf());
            setFireProf(fireProf + itemPrefix.getFireProf());
            setWaterProf(waterProf + itemPrefix.getWaterProf());
            setLightProf(lightProf + itemPrefix.getLightProf());
            setWindProf(windProf + itemPrefix.getWindProf());
        }
        if (ItemManager.ITEM_HASH_MAP.containsKey(id)) {
            FinchItem finchItem = ItemManager.ITEM_HASH_MAP.get(id);
            if (finchItem instanceof FinchArmor finchArmor) {
                setDefense(defense + finchArmor.getDefense());
                setHealthMax(healthMax + finchArmor.getHealth());
                setAttack(attack + finchArmor.getAttack());
                setStrength(strength + finchArmor.getStrength());
                setCritChance(critChance + finchArmor.getCritChance());
                setHealthRegen(healthRegen + finchArmor.getHealthRegen());
                setManaRegen(manaRegen + finchArmor.getManaRegen());
                setBowDamage(bowDamage + finchArmor.getBowDamage());
                setDexterity(dexterity + finchArmor.getDexterity());
                setManaMax(manaMax + finchArmor.getMana());
                setElectroProf(electroProf + finchArmor.getElectro());
                setFireProf(fireProf + finchArmor.getFire());
                setWaterProf(waterProf + finchArmor.getWater());
                setEarthProf(earthProf + finchArmor.getEarth());
                setDarknessProf(darknessProf + finchArmor.getDark());
                setLightProf(lightProf + finchArmor.getLight());
                setWindProf(windProf + finchArmor.getWind());
            } else if (finchItem instanceof FinchWeapon finchWeapon) {
                setDefense(defense + finchWeapon.getDefense());
                setHealthMax(healthMax + finchWeapon.getHealth());
                setAttack(attack + finchWeapon.getAttack());
                setStrength(strength + finchWeapon.getStrength());
                setCritChance(critChance + finchWeapon.getCritChance());
                setHealthRegen(healthRegen + finchWeapon.getHealthRegen());
                setManaRegen(manaRegen + finchWeapon.getManaRegen());
                setDexterity(dexterity + finchWeapon.getDexterity());
                setManaMax(manaMax + finchWeapon.getMana());
                setElectroProf(electroProf + finchWeapon.getElectro());
                setFireProf(fireProf + finchWeapon.getFire());
                setWaterProf(waterProf + finchWeapon.getWater());
                setEarthProf(earthProf + finchWeapon.getEarth());
                setDarknessProf(darknessProf + finchWeapon.getDark());
                setLightProf(lightProf + finchWeapon.getLight());
                setWindProf(windProf + finchWeapon.getWind());
            } else if (finchItem instanceof FinchTrinkets finchTrinkets) {
                setDefense(defense + finchTrinkets.getDefense());
                setHealthMax(healthMax + finchTrinkets.getHealth());
                setAttack(attack + finchTrinkets.getAttack());
                setStrength(strength + finchTrinkets.getStrength());
                setCritChance(critChance + finchTrinkets.getCritChance());
                setHealthRegen(healthRegen + finchTrinkets.getHealthRegen());
                setManaRegen(manaRegen + finchTrinkets.getManaRegen());
                setBowDamage(bowDamage + finchTrinkets.getBowDamage());
                setDexterity(dexterity + finchTrinkets.getDexterity());
                setManaMax(manaMax + finchTrinkets.getMana());
                setElectroProf(electroProf + finchTrinkets.getElectro());
                setFireProf(fireProf + finchTrinkets.getFire());
                setWaterProf(waterProf + finchTrinkets.getWater());
                setEarthProf(earthProf + finchTrinkets.getEarth());
                setDarknessProf(darknessProf + finchTrinkets.getDark());
                setLightProf(lightProf + finchTrinkets.getLight());
                setWindProf(windProf + finchTrinkets.getWind());
            }
        }
    }
    private void uncalculate(ItemStack item) {
        String id = Util.getId(item);
        String prefix = Util.getPrefix(item);
        if(!Util.getItemEnchants(item).isEmpty()){
            HashMap<ItemEnchant, Integer> enchantHashMap = Util.getItemEnchants(item);
            for(ItemEnchant enchant : enchantHashMap.keySet()){
                setDefense((defense - enchant.getDefense(enchantHashMap.get(enchant))));
                setHealthMax(healthMax - enchant.getHealth(enchantHashMap.get(enchant)));
                setAttack((attack - enchant.getAttack(enchantHashMap.get(enchant))));
                setStrength((strength - enchant.getStrength(enchantHashMap.get(enchant))));
                setCritChance((critChance - enchant.getCritChance(enchantHashMap.get(enchant))));
                setHealthRegen((healthRegen - enchant.getHealthRegen(enchantHashMap.get(enchant))));
                setManaRegen((manaRegen - enchant.getManaRegen(enchantHashMap.get(enchant))));
                setBowDamage((bowDamage - enchant.getBowDamage(enchantHashMap.get(enchant))));
                setDexterity((dexterity - enchant.getDexterity(enchantHashMap.get(enchant))));
                setManaMax((manaMax - enchant.getMana(enchantHashMap.get(enchant))));
                setDarknessProf(darknessProf - enchant.getDarknessProf(enchantHashMap.get(enchant)));
                setEarthProf(earthProf - enchant.getEarthProf(enchantHashMap.get(enchant)));
                setElectroProf(electroProf - enchant.getElectroProf(enchantHashMap.get(enchant)));
                setFireProf(fireProf - enchant.getFireProf(enchantHashMap.get(enchant)));
                setWaterProf(waterProf - enchant.getWaterProf(enchantHashMap.get(enchant)));
                setLightProf(lightProf - enchant.getLightProf(enchantHashMap.get(enchant)));
                setWindProf(windProf - enchant.getWindProf(enchantHashMap.get(enchant)));
            }
        }
        if (PrefixManager.PREFIX_HASH_MAP.containsKey(prefix)) {
            ItemPrefix itemPrefix = PrefixManager.PREFIX_HASH_MAP.get(prefix);
            setDefense(defense - itemPrefix.getDefense());
            setHealthMax(healthMax - itemPrefix.getHealth());
            setAttack(attack - itemPrefix.getAttack());
            setStrength(strength - itemPrefix.getStrength());
            setCritChance(critChance - itemPrefix.getCritChance());
            setHealthRegen(healthRegen - itemPrefix.getHealthRegen());
            setManaRegen(manaRegen - itemPrefix.getManaRegen());
            setBowDamage(bowDamage - itemPrefix.getBowDamage());
            setDexterity(dexterity - itemPrefix.getDexterity());
            setManaMax(manaMax - itemPrefix.getMana());
            setDarknessProf(darknessProf - itemPrefix.getDarknessProf());
            setEarthProf(earthProf - itemPrefix.getEarthProf());
            setElectroProf(electroProf - itemPrefix.getElectroProf());
            setFireProf(fireProf - itemPrefix.getFireProf());
            setWaterProf(waterProf - itemPrefix.getWaterProf());
            setLightProf(lightProf - itemPrefix.getLightProf());
            setWindProf(windProf - itemPrefix.getWindProf());
        }
        if (ItemManager.ITEM_HASH_MAP.containsKey(id)) {
            FinchItem finchItem = ItemManager.ITEM_HASH_MAP.get(id);
            if (finchItem instanceof FinchArmor finchArmor) {
                setDefense(defense - finchArmor.getDefense());
                setHealthMax(healthMax - finchArmor.getHealth());
                setAttack(attack - finchArmor.getAttack());
                setStrength(strength - finchArmor.getStrength());
                setCritChance(critChance - finchArmor.getCritChance());
                setHealthRegen(healthRegen - finchArmor.getHealthRegen());
                setManaRegen(manaRegen - finchArmor.getManaRegen());
                setBowDamage(bowDamage - finchArmor.getBowDamage());
                setDexterity(dexterity - finchArmor.getDexterity());
                setManaMax(manaMax - finchArmor.getMana());
                setElectroProf(electroProf - finchArmor.getElectro());
                setFireProf(fireProf - finchArmor.getFire());
                setWaterProf(waterProf - finchArmor.getWater());
                setEarthProf(earthProf - finchArmor.getEarth());
                setDarknessProf(darknessProf - finchArmor.getDark());
                setLightProf(lightProf - finchArmor.getLight());
                setWindProf(windProf - finchArmor.getWind());
            } else if (finchItem instanceof FinchWeapon finchWeapon) {
                setDefense(defense - finchWeapon.getDefense());
                setHealthMax(healthMax - finchWeapon.getHealth());
                setAttack(attack - finchWeapon.getAttack());
                setStrength(strength - finchWeapon.getStrength());
                setCritChance(critChance - finchWeapon.getCritChance());
                setHealthRegen(healthRegen - finchWeapon.getHealthRegen());
                setManaRegen(manaRegen - finchWeapon.getManaRegen());
                setDexterity(dexterity - finchWeapon.getDexterity());
                setManaMax(manaMax - finchWeapon.getMana());
                setElectroProf(electroProf - finchWeapon.getElectro());
                setFireProf(fireProf - finchWeapon.getFire());
                setWaterProf(waterProf - finchWeapon.getWater());
                setEarthProf(earthProf - finchWeapon.getEarth());
                setDarknessProf(darknessProf - finchWeapon.getDark());
                setLightProf(lightProf - finchWeapon.getLight());
                setWindProf(windProf - finchWeapon.getWind());
            } else if (finchItem instanceof FinchTrinkets finchTrinkets) {
                setDefense(defense - finchTrinkets.getDefense());
                setHealthMax(healthMax - finchTrinkets.getHealth());
                setAttack(attack - finchTrinkets.getAttack());
                setStrength(strength - finchTrinkets.getStrength());
                setCritChance(critChance - finchTrinkets.getCritChance());
                setHealthRegen(healthRegen - finchTrinkets.getHealthRegen());
                setManaRegen(manaRegen - finchTrinkets.getManaRegen());
                setBowDamage(bowDamage - finchTrinkets.getBowDamage());
                setDexterity(dexterity - finchTrinkets.getDexterity());
                setManaMax(manaMax - finchTrinkets.getMana());
                setElectroProf(electroProf - finchTrinkets.getElectro());
                setFireProf(fireProf - finchTrinkets.getFire());
                setWaterProf(waterProf - finchTrinkets.getWater());
                setEarthProf(earthProf - finchTrinkets.getEarth());
                setDarknessProf(darknessProf - finchTrinkets.getDark());
                setLightProf(lightProf - finchTrinkets.getLight());
                setWindProf(windProf - finchTrinkets.getWind());
            }
        }
        int halfHearts = 20;
        //21 vanilla health = 150 finch health
        //double increment = 0.02; // 1/50
        halfHearts += (int)((healthMax-100)/50);
        player.setHealthScale(Math.min(halfHearts, 40));
        HealthDisplay.updateActionBar(player);
    }

    public void reset() {
        setHealthMax(100);
        setDefense(10);
        setManaMax(100);
        setAttack(10);
        setStrength(10);
        setCritChance(0);
        setManaRegen(100);
        setHealthRegen(100);
        setBowDamage(10);
        setDexterity(10);
        setFireProf(0);
        setWaterProf(0);
        setEarthProf(0);
        setWindProf(0);
        setElectroProf(0);
        setLightProf(0);
        setDarknessProf(0);

    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealthMax() {
        return healthMax;
    }

    public void setHealthMax(double healthMax) {
        //this.healthmax to the greater value between healthmax and 1
        this.healthMax = Math.max(healthMax, 1);
        this.health = Math.min(healthMax, health);
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getManaMax() {
        return manaMax;
    }

    public void setManaMax(int manaMax) {
        this.manaMax = manaMax;
    }
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public int getHealthRegen() {
        return healthRegen;
    }

    public void setHealthRegen(int healthRegen) {
        this.healthRegen = healthRegen;
    }

    public int getManaRegen() {
        return manaRegen;
    }

    public void setManaRegen(int manaRegen) {
        this.manaRegen = manaRegen;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getBowDamage() {
        return bowDamage;
    }

    public void setBowDamage(int bowDamage) {
        this.bowDamage = bowDamage;
    }

    public double getFireProf() {
        return fireProf;
    }

    public void setFireProf(double fireProf) {
        this.fireProf = fireProf;
    }

    public double getWaterProf() {
        return waterProf;
    }

    public void setWaterProf(double waterProf) {
        this.waterProf = waterProf;
    }

    public double getEarthProf() {
        return earthProf;
    }

    public void setEarthProf(double earthProf) {
        this.earthProf = earthProf;
    }

    public double getWindProf() {
        return windProf;
    }

    public void setWindProf(double windProf) {
        this.windProf = windProf;
    }

    public double getElectroProf() {
        return electroProf;
    }

    public void setElectroProf(double electroProf) {
        this.electroProf = electroProf;
    }

    public double getLightProf() {
        return lightProf;
    }

    public void setLightProf(double lightProf) {
        this.lightProf = lightProf;
    }

    public double getDarknessProf() {
        return darknessProf;
    }

    public void setDarknessProf(double darknessProf) {
        this.darknessProf = darknessProf;
    }

    public Player getPlayer() {
        return player;
    }
    public ItemStack getOldItem(){
        return oldItem;
    }
    public ItemStack[] getOldTrinkets(){
        return oldTrinkets;
    }

    public TrinketMenu getTrinketMenu(){return trinketMenu;}
    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }

    //Called before the player is removed from the PLAYERS hash map for quitting
    public void dispose() {
        playerConfig.save();
    }
}
