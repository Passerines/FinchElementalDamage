package net.passerines.finch.players;

import net.passerines.finch.events.HealthDisplay;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.ItemManager;
import net.passerines.finch.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerData {

    private Player player;
    private int health;
    private int healthMax;
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
        health = 100;
        defense = 10;
        mana = 100;

        calculate();
    }

    public void calculate() {
        reset();
        //Calculate the helmet
        ItemStack helmet = player.getInventory().getHelmet();
        calculate(helmet);

        //Update player hotbar after updating their data
        HealthDisplay.displayHealth(player);
    }

    //Calculate individual armor/trinket pieces
    private void calculate(ItemStack item) {
        String id = Util.getId(item);
        if(ItemManager.ITEM_HASH_MAP.containsKey(id)) {
            FinchItem finchItem = ItemManager.ITEM_HASH_MAP.get(id);
            if(finchItem instanceof FinchArmor finchArmor) {
                setDefense(defense + finchArmor.getDefense());
                setHealthMax(healthMax + finchArmor.getHealth());
            }
        }
    }

    public void reset() {
        setHealthMax(100);
        setDefense(10);
        setManaMax(100);
        setDamage(15);
        setFireProf(0);
        setWaterProf(0);
        setEarthProf(0);
        setWindProf(0);
        setElectroProf(0);
        setLightProf(0);
        setDarknessProf(0);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthMax() {
        return healthMax;
    }

    public void setHealthMax(int healthMax) {
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
}
