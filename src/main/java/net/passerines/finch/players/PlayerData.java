package net.passerines.finch.players;

import net.passerines.finch.events.HealthDisplay;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerData {

    private Player player;
    private PlayerConfig playerConfig;
    private TrinketMenu trinketMenu;

    private double health;
    private double healthMax;
    private int defense;
    private int mana;
    private int manaMax;
    private int damage;
    private double fireProf;
    private double waterProf;
    private double earthProf;
    private double windProf;
    private double electroProf;
    private double lightProf;
    private double darknessProf;

    public PlayerData(Player player){
        this.player = player;
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
        ItemStack mainhand = player.getInventory().getItemInMainHand();
        calculate(mainhand);
        trinketMenu = new TrinketMenu();
        playerConfig = new PlayerConfig(this);
        health = playerConfig.getConfig().getDouble("Player.Health", 100);
        mana = playerConfig.getConfig().getInt("Player.Mana", 100);
    }

    public void calculatePiece(ItemStack oldHelmet, ItemStack newHelmet) {
        uncalculate(oldHelmet);
        calculate(newHelmet);
        HealthDisplay.updateActionBar(player);
    }

    public void calculateHand(ItemStack oldItem, ItemStack newItem){
        uncalculate(oldItem);
        calculate(newItem);
        HealthDisplay.updateActionBar(player);
    }
    public void calculateTrinket(){

    }

    //Calculate individual armor/trinket pieces
    private void uncalculate(ItemStack item){
        String id = Util.getId(item);
        if(ItemManager.ITEM_HASH_MAP.containsKey(id)) {
            FinchItem finchItem = ItemManager.ITEM_HASH_MAP.get(id);
            if(finchItem instanceof FinchArmor finchArmor) {
                setDefense(defense - finchArmor.getDefense());
                setHealthMax(healthMax - finchArmor.getHealth());
                setDamage(damage - finchArmor.getDamage());
                setManaMax(manaMax - finchArmor.getMana());
            }
            else if(finchItem instanceof FinchWeapon finchWeapon) {
                setDefense(defense - finchWeapon.getDefense());
                setHealthMax(healthMax - finchWeapon.getHealth());
                setDamage(damage - finchWeapon.getDamage());
                setManaMax(manaMax - finchWeapon.getMana());
            }
            else if(finchItem instanceof FinchTrinkets finchTrinkets){
                setDefense(defense - finchTrinkets.getDefense());
                setHealthMax(healthMax - finchTrinkets.getHealth());
                setDamage(damage - finchTrinkets.getDamage());
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
    }
    private void calculate(ItemStack item) {
        String id = Util.getId(item);
        if(ItemManager.ITEM_HASH_MAP.containsKey(id)) {
            FinchItem finchItem = ItemManager.ITEM_HASH_MAP.get(id);
            if(finchItem instanceof FinchArmor finchArmor) {
                setDefense(defense + finchArmor.getDefense());
                setHealthMax(healthMax + finchArmor.getHealth());
                setDamage(damage + finchArmor.getDamage());
                setManaMax(manaMax + finchArmor.getMana());
            }
            else if (finchItem instanceof FinchWeapon finchWeapon) {
                setDefense(defense + finchWeapon.getDefense());
                setHealthMax(healthMax + finchWeapon.getHealth());
                setDamage(damage + finchWeapon.getDamage());
                setManaMax(manaMax + finchWeapon.getMana());
            }
            else if(finchItem instanceof FinchTrinkets finchTrinkets){
                setDefense(defense + finchTrinkets.getDefense());
                setHealthMax(healthMax + finchTrinkets.getHealth());
                setDamage(damage + finchTrinkets.getDamage());
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

    public void reset() {
        setHealthMax(100);
        setDefense(10);
        setManaMax(100);
        setDamage(3);
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
        this.healthMax = healthMax;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
    public TrinketMenu getTrinketMenu(){return trinketMenu;}

    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }

    //Called before the player is removed from the PLAYERS hash map for quitting
    public void dispose() {
        playerConfig.save();
    }
}
