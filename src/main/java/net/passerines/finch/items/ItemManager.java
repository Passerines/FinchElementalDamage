package net.passerines.finch.items;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.armor.BloodHelmet;
import net.passerines.finch.util.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;

import java.util.HashMap;

public class ItemManager {
    public static final HashMap<String, FinchItem> ITEM_HASH_MAP = new HashMap<>();
    public ItemManager() {
        ITEM_HASH_MAP.put("bloodhelmet", new BloodHelmet());
    }

}
