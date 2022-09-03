package net.passerines.finch.items;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.armor.*;
import net.passerines.finch.items.weapons.mana.FireBallWand;
import net.passerines.finch.items.weapons.mana.PooLauncher;
import net.passerines.finch.items.weapons.melee.SharpStabber;
import net.passerines.finch.items.weapons.ranged.MachineGunBow;
import net.passerines.finch.items.weapons.ranged.Terminor;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class ItemManager {
    public static final HashMap<String, FinchItem> ITEM_HASH_MAP = new HashMap<>();

    public static void reload() {
        //If the item handles events, unregister them before we add them again
        for(FinchItem item : ITEM_HASH_MAP.values()) {
            if(item instanceof Listener listener) {
                HandlerList.unregisterAll(listener);
            }
        }
        ITEM_HASH_MAP.clear();
        registerAll();
    }

    private static void registerAll() {
        new BigBrainHat();
        new BloodHelmet();
        new BloodChestplate();
        new BloodLeggings();
        new BloodBoots();
        new FireBallWand();
        new SharpStabber();
        new MachineGunBow();
        new PooLauncher();
        new Terminor();
    }

}
