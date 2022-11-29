package net.passerines.finch.enchants;


import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class EnchantManager implements Listener {
    public static final HashMap<String, ItemEnchants> ENCHANTS_HASH_MAP = new HashMap<>();

    public EnchantManager(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        registerEnchants();
    }

    public void registerEnchants(){
        new ItemEnchants("SwordSharpness", Chat.formatC("Sharpness"), ItemEnchants.EnchantmentType.WEAPON, 0, 10, 0, 0);
    }

    //A hashmap cannot contain 2 of the same keys, but 2 keys can have the same value
    //This means that 2 enchants can have the same level
    //ToDo:
    @EventHandler
    public void onDamageEntity(ElementalDamageEvent event){
        if(event.getAttacker() instanceof Player){
            HashMap<String, Integer> enchantMap = Util.getEnchants(((Player) event.getAttacker()).getInventory().getItemInMainHand());
            for(String enchant : enchantMap.keySet()){

            }
        }
    }

    public static ArrayList<ItemEnchants> getEnchants(ItemEnchants.EnchantmentType type, int tier){
        ArrayList<ItemEnchants> prefixList = new ArrayList<>(EnchantManager.ENCHANTS_HASH_MAP.values());
        ArrayList<ItemEnchants> itemEnchantList = new ArrayList<>();
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
