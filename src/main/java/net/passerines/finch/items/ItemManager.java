package net.passerines.finch.items;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.armor.BloodHelmet;

import java.util.HashMap;

public class ItemManager {
    public static final HashMap<String, FinchItem> ITEM_HASH_MAP = new HashMap<>();
    public ItemManager() {
        ITEM_HASH_MAP.put("BloodHelmet", new BloodHelmet());
    }
}
