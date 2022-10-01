package net.passerines.finch.items;

import net.passerines.finch.events.ElementalDamageEvent;
import org.bukkit.inventory.ItemStack;

public abstract class FinchTrinkets extends FinchItem {

    protected double health;
    protected int defense;
    protected int damage;
    protected int mana;
    protected double fire;
    protected double water;
    protected double earth;
    protected double wind;
    protected int electro;
    protected int light;
    protected int dark;
    protected int durability;
    protected ElementalDamageEvent.Element element;

    public FinchTrinkets(String id) {
        super(id);
        this.durability = 0;
        this.element = ElementalDamageEvent.Element.FIRE;
        this.health = 0;
        this.defense = 0;
        this.damage = 0;
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
    public int getDamage(){
        return damage;
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
    public int getElectro(){
        return electro;
    }
    public int getLight(){
        return light;
    }
    public int getDark(){
        return dark;
    }
    public int getDurability(){return durability;}
}
