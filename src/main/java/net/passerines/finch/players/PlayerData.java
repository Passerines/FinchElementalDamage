package net.passerines.finch.players;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.enchants.ItemEnchant;
import net.passerines.finch.events.HealthDisplay;
import net.passerines.finch.items.*;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.reforge.ItemPrefix;
import net.passerines.finch.reforge.PrefixManager;
import net.passerines.finch.trinkets.TrinketMenu;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerData {

    private Player player;
    private PlayerConfig playerConfig;
    private TrinketMenu trinketMenu;
    private boolean isPlayerInsectBuffed;
    private HashMap<String, Integer> armorBonus = new HashMap<>();
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
    private int insectBuffTask;



    private FinchInsect finchInsect;

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
    public void calculate(ItemStack item) {
        String id = Util.getId(item);
        String prefix = Util.getPrefix(item);
        if(Util.getItemEnchants(item) != null && !Util.getItemEnchants(item).isEmpty()){
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
            if (ItemManager.ITEM_HASH_MAP.get(id) instanceof FinchEquipment finchEquipment) {
                //player.sendMessage(Chat.formatC("Uncalculated"));
                setDefense(defense + finchEquipment.getDefense());
                setHealthMax(healthMax + finchEquipment.getHealth());
                setAttack(attack + finchEquipment.getAttack());
                setStrength(strength + finchEquipment.getStrength());
                setCritChance(critChance + finchEquipment.getCritChance());
                setHealthRegen(healthRegen + finchEquipment.getHealthRegen());
                setManaRegen(manaRegen + finchEquipment.getManaRegen());
                setBowDamage(bowDamage + finchEquipment.getBowDamage());
                setDexterity(dexterity + finchEquipment.getDexterity());
                setManaMax(manaMax + finchEquipment.getMana());
                setElectroProf(electroProf + finchEquipment.getElectro());
                setFireProf(fireProf + finchEquipment.getFire());
                setWaterProf(waterProf + finchEquipment.getWater());
                setEarthProf(earthProf + finchEquipment.getEarth());
                setDarknessProf(darknessProf + finchEquipment.getDark());
                setLightProf(lightProf + finchEquipment.getLight());
                setWindProf(windProf + finchEquipment.getWind());
            }
        }
    }
    public void uncalculate(ItemStack item) {
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
            if (ItemManager.ITEM_HASH_MAP.get(id) instanceof FinchEquipment finchEquipment) {
                //player.sendMessage(Chat.formatC("Uncalculated"));
                setDefense(defense - finchEquipment.getDefense());
                setHealthMax(healthMax - finchEquipment.getHealth());
                setAttack(attack - finchEquipment.getAttack());
                setStrength(strength - finchEquipment.getStrength());
                setCritChance(critChance - finchEquipment.getCritChance());
                setHealthRegen(healthRegen - finchEquipment.getHealthRegen());
                setManaRegen(manaRegen - finchEquipment.getManaRegen());
                setBowDamage(bowDamage - finchEquipment.getBowDamage());
                setDexterity(dexterity - finchEquipment.getDexterity());
                setManaMax(manaMax - finchEquipment.getMana());
                setElectroProf(electroProf - finchEquipment.getElectro());
                setFireProf(fireProf - finchEquipment.getFire());
                setWaterProf(waterProf - finchEquipment.getWater());
                setEarthProf(earthProf - finchEquipment.getEarth());
                setDarknessProf(darknessProf - finchEquipment.getDark());
                setLightProf(lightProf - finchEquipment.getLight());
                setWindProf(windProf - finchEquipment.getWind());
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
        this.health = Math.min(healthMax, health);
        HealthDisplay.updateActionBar(player);
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
        this.mana = Math.min(manaMax, Math.max(0, mana));
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
    public boolean isPlayerInsectBuffed(){
        return isPlayerInsectBuffed;
    }
    public void setPlayerInsectBuffed(boolean b){
        isPlayerInsectBuffed = b;
    }
    public HashMap<String, Integer> getArmorBonus() {
        return armorBonus;
    }

    public void setArmorBonus(String armorSetName, int amount) {
        armorBonus.put(armorSetName,amount);
    }
    public void removeArmorBonus(String armorSetName){
        armorBonus.remove(armorSetName);
    }
    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }
    public void heal(int i){
        setHealth(Math.min(health + i, healthMax));
    }

    public void applyInsectBuff(FinchInsect finchInsect, Entity e) {
        if (e != null) {
            if (isPlayerInsectBuffed) {
                Bukkit.getScheduler().cancelTask(insectBuffTask);
            }
            else {
                calculate(finchInsect.getItem());
                this.finchInsect = finchInsect;
                setPlayerInsectBuffed(true);
            }
            insectBuffTask = Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), () -> {
                uncalculate(this.finchInsect.getItem());
                setPlayerInsectBuffed(false);
            }, 300);
        }
    }
    //Called before the player is removed from the PLAYERS hash map for quitting
    public void dispose() {
        playerConfig.save();
    }
}
