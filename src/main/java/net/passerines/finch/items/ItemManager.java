package net.passerines.finch.items;

import net.passerines.finch.items.armor.*;
import net.passerines.finch.items.armor.bloodItems.mage.BloodMageBoots;
import net.passerines.finch.items.armor.bloodItems.mage.BloodMageChestplate;
import net.passerines.finch.items.armor.bloodItems.mage.BloodMageHelmet;
import net.passerines.finch.items.armor.bloodItems.mage.BloodMageLeggings;
import net.passerines.finch.items.armor.bloodItems.warrior.BloodBoots;
import net.passerines.finch.items.armor.bloodItems.warrior.BloodChestplate;
import net.passerines.finch.items.armor.bloodItems.warrior.BloodHelmet;
import net.passerines.finch.items.armor.bloodItems.warrior.BloodLeggings;
import net.passerines.finch.items.armor.carbonfiberItems.CarbonFiberBoots;
import net.passerines.finch.items.armor.carbonfiberItems.CarbonFiberChestplate;
import net.passerines.finch.items.armor.carbonfiberItems.CarbonFiberHelmet;
import net.passerines.finch.items.armor.carbonfiberItems.CarbonFiberLeggings;
import net.passerines.finch.items.weapons.mana.*;
import net.passerines.finch.items.weapons.melee.RitualBlade;
import net.passerines.finch.items.weapons.melee.SharpStabber;
import net.passerines.finch.items.weapons.ranged.HellfireCrossbow;
import net.passerines.finch.items.weapons.ranged.IntergalaticSniper;
import net.passerines.finch.items.weapons.ranged.Tsunami;
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
        new BloodMageBoots();
        new BloodMageChestplate();
        new BloodMageLeggings();
        new BloodMageHelmet();
        new CarbonFiberBoots();
        new CarbonFiberChestplate();
        new CarbonFiberHelmet();
        new CarbonFiberLeggings();
        new FireBallWand();
        new SharpStabber();
        new HellfireCrossbow();
        new PooLauncher();
        new Tsunami();
        new StronkBoots();
        new Teleporter();
        new StrongLeggings();
        new IntergalaticSniper();
        new SniperHelmet();
        new LifeStone();
        new Valkyre();
        new RitualBlade();
    }

}
