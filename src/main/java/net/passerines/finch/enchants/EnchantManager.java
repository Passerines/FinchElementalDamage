package net.passerines.finch.enchants;


import java.util.ArrayList;
import java.util.HashMap;

public class EnchantManager {
    public static final HashMap<String, ItemEnchants> ENCHANTS_HASH_MAP = new HashMap<>();

    public EnchantManager(){
        registerEnchants();
    }

    public void registerEnchants(){

    }

    public static ArrayList<ItemEnchants> getPrefixes(ItemEnchants.EnchantmentType type, int tier){
        ArrayList<ItemEnchants> prefixList = new ArrayList<>(EnchantManager.ENCHANTS_HASH_MAP.values());
        ArrayList<ItemEnchants> itemEnchantList = new ArrayList<>();
        for(int i = 0; i < prefixList.size(); i++){
            if(itemEnchantList.get(i).getType() == type && itemEnchantList.get(i).getTier() == tier){
                itemEnchantList.add(prefixList.get(i));
            }
        }
        return itemEnchantList;
    }
}
