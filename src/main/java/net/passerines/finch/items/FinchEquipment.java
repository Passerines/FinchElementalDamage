package net.passerines.finch.items;

import net.kyori.adventure.text.Component;
import net.passerines.finch.enchants.ItemEnchant;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.reforge.ItemPrefix;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public abstract class FinchEquipment extends FinchItem {

    public static final String STATS = "<STATS>";
    public static final String ENCHANTS = "<ENCHANTS>";

    protected double health;
    protected int defense;
    protected int attack;
    protected int strength;
    protected int critChance;
    protected int healthRegen;
    protected int manaRegen;
    protected int dexterity;
    protected int bowDamage;
    protected int mana;
    protected double fire;
    protected double water;
    protected double earth;
    protected double wind;
    protected double electro;
    protected double light;
    protected double dark;

    public FinchEquipment(String id, int rarity) {
        super(id, rarity);
        this.health = 0;
        this.defense = 0;
        this.attack = 0;
        this.strength = 0;
        this.critChance = 0;
        this.healthRegen = 0;
        this.manaRegen = 0;
        this.dexterity = 0;
        this.bowDamage = 0;
        this.mana = 0;
        this.fire = 0;
        this.water = 0;
        this.earth = 0;
        this.wind = 0;
        this.electro = 0;
        this.light = 0;
        this.dark = 0;
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
    public int getBowDamage(){return bowDamage;}
    public int getMana(){
        return mana;
    }
    public double getFire(){
        return fire;
    }
    public double getWater(){
        return water;
    }
    public double getEarth(){
        return earth;
    }
    public double getWind(){
        return wind;
    }
    public double getElectro(){
        return electro;
    }
    public double getLight(){
        return light;
    }
    public double getDark(){
        return dark;
    }

    @Override
    public ItemStack format(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<Component> newLore = new ArrayList<>();
        ItemPrefix prefix = Util.getItemPrefix(item);
        HashMap<ItemEnchant, Integer> enchants = Util.getItemEnchants(item);
        itemMeta.displayName(prefix!=null?prefix.getDisplayName().append(Chat.formatC(" ")).append(displayName):displayName);
        for(Component line : lore){
            if(Chat.asPlainText(line).contains(STATS)) {
                StringBuilder s;
                switch (rarity){
                    default -> s = new StringBuilder("&7Teir " + rarity + " ");
                    case 2 -> s = new StringBuilder("&2Teir 2 ");
                    case 3 -> s = new StringBuilder("&9Teir 3 ");
                    case 4 -> s = new StringBuilder("&5Teir 4 ");
                    case 5 -> s = new StringBuilder("&6Teir 5 ");
                    case 6 -> s = new StringBuilder("&dTeir 6 ");
                    case 7 -> s = new StringBuilder("&4Teir 7 ");
                }
                if(this instanceof FinchWeapon){
                    s.append("Weapon");
                }
                else if(this instanceof FinchArmor){
                    s.append("Armor");
                }
                else if (this instanceof FinchTrinkets){
                    s.append("Trinket");
                }
                newLore.add(Chat.formatC(s.toString()));
                newLore.add(Chat.formatC(" "));
                int bonusHealth = prefix==null?0:prefix.getHealth();
                for(ItemEnchant enchant : enchants.keySet()) bonusHealth += enchant.getHealth(enchants.get(enchant));
                parseStat(newLore, "&cHealth: &7", health, bonusHealth);
                int bonusHealthRegen = prefix==null?0:prefix.getHealthRegen();
                for(ItemEnchant enchant : enchants.keySet()) bonusHealthRegen += enchant.getHealthRegen(enchants.get(enchant));
                parseStat(newLore, "&cHealth Regen: &7", healthRegen, bonusHealthRegen);
                int bonusMana = prefix==null?0:prefix.getMana();
                for(ItemEnchant enchant : enchants.keySet()) bonusMana += enchant.getMana(enchants.get(enchant));
                parseStat(newLore, "&bMana: &7", mana, bonusMana);
                int bonusManaRegen = prefix==null?0:prefix.getManaRegen();
                for(ItemEnchant enchant : enchants.keySet()) bonusManaRegen += enchant.getManaRegen(enchants.get(enchant));
                parseStat(newLore, "&cHealth Regen: &7", healthRegen, bonusManaRegen);
                int bonusDefense = prefix==null?0:prefix.getDefense();
                for(ItemEnchant enchant : enchants.keySet()) bonusDefense += enchant.getDefense(enchants.get(enchant));
                parseStat(newLore, "&aDefense: &7", defense, bonusDefense);
                int bonusCritChance = prefix==null?0:prefix.getCritChance();
                for(ItemEnchant enchant : enchants.keySet()) bonusCritChance += enchant.getCritChance(enchants.get(enchant));
                parseStat(newLore, "&eCrit Chance: &7", critChance, bonusCritChance, true);
                int bonusAttack = prefix==null?0:prefix.getAttack();
                for(ItemEnchant enchant : enchants.keySet()) bonusAttack += enchant.getAttack(enchants.get(enchant));
                parseStat(newLore, "&bAttack: &7", attack, bonusAttack);
                int bonusRangedDamage = prefix==null?0:prefix.getBowDamage();
                for(ItemEnchant enchant : enchants.keySet()) bonusRangedDamage += enchant.getBowDamage(enchants.get(enchant));
                parseStat(newLore, "&6Ranged Damage: &7", bowDamage, bonusRangedDamage);
                int bonusStrength = prefix==null?0:prefix.getStrength();
                for(ItemEnchant enchant : enchants.keySet()) bonusStrength += enchant.getStrength(enchants.get(enchant));
                parseStat(newLore, "&cStrength: &7", strength, bonusStrength);
                int bonusDexterity = prefix==null?0:prefix.getDexterity();
                for(ItemEnchant enchant : enchants.keySet()) bonusDexterity += enchant.getDexterity(enchants.get(enchant));
                parseStat(newLore, "&6Dexterity: &7", dexterity, bonusDexterity);
                double bonusFire = prefix==null?0:prefix.getFireProf();
                for(ItemEnchant enchant : enchants.keySet()) bonusFire += enchant.getFireProf(enchants.get(enchant));
                double bonusWater = prefix==null?0:prefix.getWaterProf();
                for(ItemEnchant enchant : enchants.keySet()) bonusWater += enchant.getWaterProf(enchants.get(enchant));
                double bonusEarth = prefix==null?0:prefix.getEarthProf();
                for(ItemEnchant enchant : enchants.keySet()) bonusEarth += enchant.getEarthProf(enchants.get(enchant));
                double bonusWind = prefix==null?0:prefix.getWindProf();
                for(ItemEnchant enchant : enchants.keySet()) bonusWind += enchant.getWindProf(enchants.get(enchant));
                double bonusElectro = prefix==null?0:prefix.getElectroProf();
                for(ItemEnchant enchant : enchants.keySet()) bonusElectro += enchant.getElectroProf(enchants.get(enchant));
                double bonusDark = prefix==null?0:prefix.getDarknessProf();
                for(ItemEnchant enchant : enchants.keySet()) bonusDark += enchant.getDarknessProf(enchants.get(enchant));
                double bonusLight = prefix==null?0:prefix.getLightProf();
                for(ItemEnchant enchant : enchants.keySet()) bonusLight += enchant.getLightProf(enchants.get(enchant));
                if(fire!=0 || water!=0 || earth!=0 || wind!=0 || electro!=0 || light!=0 || dark!=0
                        || bonusFire!=0 || bonusWater!=0 || bonusEarth!=0 || bonusWind!=0 || bonusElectro!=0 || bonusLight!=0 || bonusDark!=0) {
                    newLore.add(Chat.formatC("&bElemental Proficiencies:"));
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.FIRE.getColor() + "Fire: &7", fire, bonusFire);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.WATER.getColor() + "Water: &7", water, bonusWater);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.EARTH.getColor() + "Earth: &7", earth, bonusEarth);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.WIND.getColor() + "Wind: &7", wind, bonusWind);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.ELECTRO.getColor() + "Electro: &7", electro, bonusElectro);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.LIGHT.getColor() + "Light: &7", dark, bonusLight);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.DARK.getColor() + "Dark: &7", dark, bonusDark);
                }
            } else if(Chat.asPlainText(line).contains(ENCHANTS)) {
                for(ItemEnchant enchant : enchants.keySet()) {
                    newLore.add(enchant.getDisplayName().append(Chat.formatC( " &7" + enchants.get(enchant) + Util.romanNumberConverter(enchants.get(enchant)))));
                }
                if(enchants.size()>0) newLore.add(Chat.formatC(""));
            } else {
                newLore.add(line);
            }
        }
        itemMeta.lore(newLore);

        item.setItemMeta(itemMeta);
        return item;
    }

    public void parseStat(ArrayList<Component> lore, String line, double baseAmount, double bonusAmount) {
        parseStat(lore, line, baseAmount, bonusAmount, false);
    }
    public void parseStat(ArrayList<Component> lore, String line, double baseAmount, double bonusAmount, boolean percentage) {
        if(baseAmount!=0 || bonusAmount!=0) {
            Component component = Chat.formatC(line).append(Chat.formatC((baseAmount>0?"&a":baseAmount<0?"&c":"&7") + (int)baseAmount + (percentage?"%":"")));
            if(bonusAmount!=0) {
                component = component.append(Chat.formatC(" " + (bonusAmount>0?"&a":"&c") + "(" + (bonusAmount>0?"+":"") + (int)bonusAmount + (percentage?"%":"") + ")"));
            }
            lore.add(component);
        }
    }
}
