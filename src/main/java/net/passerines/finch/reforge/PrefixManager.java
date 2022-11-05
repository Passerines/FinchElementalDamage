package net.passerines.finch.reforge;

import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;

import java.util.HashMap;

public class PrefixManager {
    public static final HashMap<String, ItemPrefix> PREFIX_HASH_MAP = new HashMap<>();

    public PrefixManager(){
        registerPrefix();
    }

    public void registerPrefix(){
        new ItemPrefix("armorbrittle", "&7Brittle", "armor",1, 0, 0, -35, 0);
    }
}
