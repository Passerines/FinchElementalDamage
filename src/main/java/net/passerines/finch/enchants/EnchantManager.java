package net.passerines.finch.enchants;


import net.passerines.finch.util.Chat;

import java.util.ArrayList;
import java.util.HashMap;

public class EnchantManager {
    public static final HashMap<String, ItemEnchants> ENCHANTS_HASH_MAP = new HashMap<>();

    public EnchantManager(){
        registerEnchants();
    }

    public void registerEnchants(){
        ItemEnchants FinchSharpness = new ItemEnchants("SwordSharpness", Chat.formatC("Sharpness"), ItemEnchants.EnchantmentType.WEAPON, 0, 10, 0, 0);
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
