package net.passerines.finch.enchants;


import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.enchants.weaponenchants.BloodLustEnchant;
import net.passerines.finch.enchants.weaponenchants.FireAspectEnchant;
import net.passerines.finch.enchants.weaponenchants.LifeDrainCurse;
import net.passerines.finch.enchants.weaponenchants.LifestealEnchant;
import net.passerines.finch.events.CustomEntityDeathEvent;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnchantManager implements Listener {
    public static final HashMap<String, ItemEnchant> ENCHANTS_HASH_MAP = new HashMap<>();

    public EnchantManager(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        registerEnchants();
    }

    public void registerEnchants(){
        new ItemEnchant("SwordSharpness", Chat.formatC("Sharpness"), ItemEnchant.EnchantmentType.WEAPON).setMaxLevel(5).setAttack(10).setStrength(5);
        new FireAspectEnchant().setMaxLevel(10);
        new BloodLustEnchant().setMaxLevel(7);
        new LifeDrainCurse().setMaxLevel(3);
        new ItemEnchant("TrinketBlessed", Chat.formatC("Blessed"), ItemEnchant.EnchantmentType.TRINKET).setMaxLevel(3).setLightProf(3).setHealth(100);
        new ItemEnchant("ArmorProtection", Chat.formatC("Protection"), ItemEnchant.EnchantmentType.WEAPON).setMaxLevel(10).setDefense(50);
        new ItemEnchant("ArmorHealthy", Chat.formatC("Healthy"), ItemEnchant.EnchantmentType.WEAPON).setMaxLevel(10).setHealth(75);
        new ItemEnchant("SwordHarmony", Component.text("Harmony").color(TextColor.color(52, 76, 235)), ItemEnchant.EnchantmentType.WEAPON)
                .setMaxLevel(5).setDarknessProf(1).setEarthProf(1).setElectroProf(1).setWindProf(1).setFireProf(1).setLightProf(1);
        new LifestealEnchant().setMaxLevel(7);
    }

    //A hashmap cannot contain 2 of the same keys, but 2 keys can have the same value
    //This means that 2 enchants can have the same level
    @EventHandler
    public void onDamageEntity(ElementalDamageEvent event){
        if(event.getAttacker() instanceof Player){
            if(event.getWeapon()!=null) {
                HashMap<ItemEnchant, Integer> enchantMap = Util.getItemEnchants(event.getWeapon());
                for (ItemEnchant enchant : enchantMap.keySet()) {
                    enchant.onElementalDamage(event, enchantMap.get(enchant));
                }
            }
        }
    }

    public static ItemEnchant.EnchantmentType getType(FinchItem item){
        if(item instanceof FinchWeapon){
            return ItemEnchant.EnchantmentType.WEAPON;
        }
        if(item instanceof FinchArmor){
            return ItemEnchant.EnchantmentType.ARMOR;
        }
        if(item instanceof FinchTrinkets){
            return ItemEnchant.EnchantmentType.TRINKET;
        }
        return ItemEnchant.EnchantmentType.NONE;
    }

    public static ItemEnchant getEnchant(String id){
        return ENCHANTS_HASH_MAP.get(id);
    }

    public static ItemEnchant randomEnchant(){
        int randomValue = Util.rand(0, ENCHANTS_HASH_MAP.values().size()-1);
        ArrayList<ItemEnchant> list = new ArrayList<>(ENCHANTS_HASH_MAP.values());
        return list.get(randomValue);
    }

    public static ArrayList<String> getOkEnchants(ItemStack itemstack){
        ItemEnchant.EnchantmentType type = getType(Util.getFinchItem(itemstack));
        ArrayList<String> enchants = new ArrayList<>();
        ArrayList<ItemEnchant> enchantArrayList = new ArrayList<>(EnchantManager.ENCHANTS_HASH_MAP.values());
        for (ItemEnchant itemEnchant : enchantArrayList) {
            if (type == itemEnchant.getType()) {
                enchants.add(itemEnchant.getId());
            }
        }
        return enchants;
    }

    public static ArrayList<ItemEnchant> getEnchants(ItemEnchant.EnchantmentType type, int tier){
        ArrayList<ItemEnchant> prefixList = new ArrayList<>(EnchantManager.ENCHANTS_HASH_MAP.values());
        ArrayList<ItemEnchant> itemEnchantList = new ArrayList<>();
        for(int i = 0; i < prefixList.size(); i++){
            //This is to apply the enchant by getting the enchants, then putting it on the weapons.
            //ToDo:
            //if(itemEnchantList.get(i).getType() == type && itemEnchantList.get(i).getTier() == tier){
            //  itemEnchantList.add(prefixList.get(i));
            //}

        }
        return itemEnchantList;
    }
}
