package net.passerines.finch.reforge;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.HSVLike;
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
        // new ItemPrefix("test", "test", test ,0, 0, 0, 0, 0);
        new ItemPrefix("armorbrittle", "&7Brittle", ItemPrefix.Type.ARMOR,1, 0,0, 0, -35, 0);
        new ItemPrefix("armorhard", "&8Hard", ItemPrefix.Type.ARMOR, 1, 0,0, 0, 50, 0);
        new ItemPrefix("armorreinforced", "&8Reinforced", ItemPrefix.Type.ARMOR ,2, 0,0, 0, 150, 0);
        new ItemPrefix("armorunyielding", "&dUnyielding", ItemPrefix.Type.ARMOR ,3, 200,0, 0, 175, 0);
        // ^armor
        new ItemPrefix("weaponbrittle", "&7Brittle", ItemPrefix.Type.WEAPON,1, 0,0, -10, 0, 0);
        new ItemPrefix("weaponsharp", "&bSharp", ItemPrefix.Type.WEAPON,1, 0,0, 5, 0, 0);
        new ItemPrefix("weaponcrude", "&7Crude", ItemPrefix.Type.WEAPON ,1, 0,0, 10, 0, -10);
        new ItemPrefix("weaponinfused", "&5Infused", ItemPrefix.Type.WEAPON ,2, 0,0, 0, 0, 350);
        new ItemPrefix("weaponatomizing", "&cAtomizing", ItemPrefix.Type.WEAPON, 3, 0,0, 250, 0, 0);
        // ^weapon
        new ItemPrefix("trinketancient", "&4Ancient", ItemPrefix.Type.TRINKET, 2, 0, 20,0, 0, 200);
        new ItemPrefix("trinketenhanced", "&bEnhanced", ItemPrefix.Type.TRINKET, 1, 0, 0,0, 0, 300);
        new ItemPrefix("trinketmystical", Component.text("Mystical").color(TextColor.color(52, 76, 235)), ItemPrefix.Type.TRINKET, 3, 100,0, 0, 10, 1000);
        // ^trinket
    }

    public static ArrayList<ItemPrefix> getPrefixes(ItemPrefix.Type type, int tier){
        ArrayList<ItemPrefix> prefixList = new ArrayList<>(PrefixManager.PREFIX_HASH_MAP.values());
        ArrayList<ItemPrefix> itemPrefixList = new ArrayList<>();
        for(int i = 0; i < prefixList.size(); i++){
            if(prefixList.get(i).getType() == type && prefixList.get(i).getTier() == tier){
                itemPrefixList.add(prefixList.get(i));
            }
        }
        return itemPrefixList;
    }
}
