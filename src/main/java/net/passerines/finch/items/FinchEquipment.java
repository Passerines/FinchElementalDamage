package net.passerines.finch.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.reforge.ItemPrefix;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

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
        itemMeta.displayName(prefix!=null?prefix.getDisplayName().append(Chat.formatC(" ")).append(displayName):displayName);
        for(Component line : lore){
            if(Chat.asPlainText(line).contains(STATS)) {
                parseStat(newLore, "&bHealth: &7", health, prefix==null?0:prefix.getHealth());
                parseStat(newLore, "&bDefense: &7", defense, prefix==null?0:prefix.getDefense());
                parseStat(newLore, "&bDamage: &7", attack, prefix==null?0:prefix.getAttack());
                parseStat(newLore, "&bBow Damage: &7", bowDamage, 0);
                parseStat(newLore, "&bMana: &7", mana, prefix==null?0:prefix.getMana());
                if(fire!=0 || water!=0 || earth!=0 || wind!=0 || electro!=0 || light!=0 || dark!=0) {
                    newLore.add(Chat.formatC("&bElemental Proficiencies:"));
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.FIRE.getColor() + "Fire: &7", fire, 0);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.WATER.getColor() + "Water: &7", water, 0);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.EARTH.getColor() + "Earth: &7", earth, 0);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.WIND.getColor() + "Wind: &7", wind, 0);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.ELECTRO.getColor() + "Electro: &7", electro, 0);
                    parseStat(newLore, "  " + ElementalDamageEvent.Element.DARK.getColor() + "Dark: &7", dark, 0);
                }
            } else if(Chat.asPlainText(line).contains(STATS)) {
                HashMap<String, Integer> enchants = Util.getEnchants(item);
                for(String enchant : enchants.keySet()) {
                    newLore.add(Chat.formatC("&b" + enchant + " &7" + enchants.get(enchant)));
                }
            } else {
                newLore.add(line);
            }
        }
        itemMeta.lore(newLore);

        item.setItemMeta(itemMeta);
        return item;
    }

    public void parseStat(ArrayList<Component> lore, String line, double baseAmount, double prefixAmount) {
        if(baseAmount!=0 || prefixAmount!=0) {
            Component component = Chat.formatC(line).append(Chat.formatC((baseAmount>0?"&a":"&c") + (int)baseAmount));
            if(prefixAmount!=0) {
                component = component.append(Chat.formatC(" " + (prefixAmount>0?"&a":"&c") + "(" + (int)prefixAmount + ")"));
            }
            lore.add(component);
        }
    }
}
