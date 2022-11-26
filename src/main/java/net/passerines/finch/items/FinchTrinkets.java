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

public abstract class FinchTrinkets extends FinchEquipment {

    protected int type;
    protected ElementalDamageEvent.Element element;

    public FinchTrinkets(String id) {
        this(id, 0);
    }
    public FinchTrinkets(String id, int rarity) {
        super(id, rarity);
        this.element = ElementalDamageEvent.Element.FIRE;
        this.type = 1;
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
    public int getBowDamage(){
        return bowDamage;
    }

    public int getMana(){
        return mana;
    }
    public ElementalDamageEvent.Element getElement(){
        return element;
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
    public int getRarity(){return rarity;}
    public int getType(){return type;}
}
