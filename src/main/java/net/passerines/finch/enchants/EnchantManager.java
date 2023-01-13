package net.passerines.finch.enchants;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.enchants.weaponenchants.BloodLustEnchant;
import net.passerines.finch.enchants.weaponenchants.FireAspectEnchant;
import net.passerines.finch.enchants.weaponenchants.LifeDrainCurse;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;

public class EnchantManager implements Listener {
    public static final HashMap<String, ItemEnchant> ENCHANTS_HASH_MAP = new HashMap<>();

    public EnchantManager(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        registerEnchants();
    }

    public void registerEnchants(){
        new ItemEnchant("SwordSharpness", Chat.formatC("Sharpness"), ItemEnchant.EnchantmentType.WEAPON)
                .setMaxLevel(5).setAttack(10).setStrength(5);
        new FireAspectEnchant().setMaxLevel(10);
        new BloodLustEnchant().setMaxLevel(7);
        new LifeDrainCurse().setMaxLevel(3);
        new ItemEnchant("ArmorProtection", Chat.formatC("Protection"), ItemEnchant.EnchantmentType.WEAPON).setMaxLevel(10).setDefense(50);
        new ItemEnchant("SwordHarmony", Component.text("Harmony").color(TextColor.color(52, 76, 235)), ItemEnchant.EnchantmentType.WEAPON)
                .setMaxLevel(5).setDarknessProf(1).setEarthProf(1).setElectroProf(1).setWindProf(1).setFireProf(1).setLightProf(1);
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
