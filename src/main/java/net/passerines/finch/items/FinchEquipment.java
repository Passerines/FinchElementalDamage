package net.passerines.finch.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.passerines.finch.reforge.ItemPrefix;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public abstract class FinchEquipment extends FinchItem {

    public static final String DAMAGE = "<DAMAGE>";
    public static final String MANA = "<MANA>";
    public static final String DEFENSE = "<DEFENSE>";
    public static final String HEALTH = "<HEALTH>";
    public static final String BOW_DAMAGE = "<BOW_DAMAGE>";

    protected double health;
    protected int defense;
    protected int damage;
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
        this.damage = 0;
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
    public int getDamage(){
        return damage;
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
            String damageString = damage+(prefix!=null && prefix.getDamage()!=0?" &b" + (prefix.getDamage()>0?"+":"-") + "["+Math.abs(prefix.getDamage())+"]&r":"");
            line = line.replaceText(TextReplacementConfig.builder().matchLiteral(DAMAGE).replacement(Chat.formatC(damageString)).build());
            String manaString = mana+(prefix!=null && prefix.getMana()!=0?" &b" + (prefix.getMana()>0?"+":"-") + "["+Math.abs(prefix.getMana())+"]&r":"");
            line = line.replaceText(TextReplacementConfig.builder().matchLiteral(MANA).replacement(Chat.formatC(manaString)).build());
            String defenseString = defense+(prefix!=null && prefix.getDefense()!=0?" &b" + (prefix.getDefense()>0?"+":"-") + "["+Math.abs(prefix.getDefense())+"]&r":"");
            line = line.replaceText(TextReplacementConfig.builder().matchLiteral(DEFENSE).replacement(Chat.formatC(defenseString)).build());
            String healthString = health+(prefix!=null && prefix.getHealth()!=0?" &b" + (prefix.getHealth()>0?"+":"-") + "["+Math.abs(prefix.getHealth())+"]&r":"");
            line = line.replaceText(TextReplacementConfig.builder().matchLiteral(HEALTH).replacement(Chat.formatC(healthString)).build());
            line = line.replaceText(TextReplacementConfig.builder().matchLiteral(BOW_DAMAGE).replacement(damage+"").build());
            //line = line.replaceText(TextReplacementConfig.builder().matchLiteral(BOW_DAMAGE).replacement(damage+(prefix!=null?"+"+prefix.getDamage():"")).build());
            newLore.add(line);
        }
        itemMeta.lore(newLore);

        item.setItemMeta(itemMeta);
        return item;
    }
}
