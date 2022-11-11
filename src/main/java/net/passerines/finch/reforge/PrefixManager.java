package net.passerines.finch.reforge;

import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import org.bukkit.entity.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class PrefixManager {
    public static final HashMap<String, ItemPrefix> PREFIX_HASH_MAP = new HashMap<>();

    public PrefixManager(){
        registerPrefix();
    }

    public void registerPrefix(){
        new ItemPrefix("armorbrittle", "&7Brittle", ItemPrefix.Type.ARMOR,1, 0, 0, -35, 0);
        new ItemPrefix("armorhard", "&8Hard", ItemPrefix.Type.ARMOR, 1, 0, 0, 150, 0);
        // ^armor
        new ItemPrefix("weaponbrittle", "&7Brittle", ItemPrefix.Type.WEAPON,1, 0, -10, 0, 0);
        new ItemPrefix("weaponsharp", "&bSharp", ItemPrefix.Type.WEAPON,1, 0, 5, 0, 0);
        new ItemPrefix("weaponatomizing", "&cAtomizing", ItemPrefix.Type.WEAPON, 5, 0, 250, 0, 0);
        // ^weapon
        new ItemPrefix("trinketancient", "&4Ancient", ItemPrefix.Type.TRINKET, 2, 0, 20, 0, 200);
        new ItemPrefix("trinketenhanced", "&bEnhanced", ItemPrefix.Type.TRINKET, 1, 0, 0, 0, 300);
        // ^trinket
    }

    public static ArrayList<ItemPrefix> getPrefixes(ItemPrefix.Type type){
        ArrayList<ItemPrefix> prefixList = new ArrayList<>(PrefixManager.PREFIX_HASH_MAP.values());
        ArrayList<ItemPrefix> itemPrefixList = new ArrayList<>();
        for(int i = 0; i < prefixList.size(); i++){
            if(prefixList.get(i).getType() == type){
                itemPrefixList.add(prefixList.get(i));
            }
        }
        return itemPrefixList;
    }
}
