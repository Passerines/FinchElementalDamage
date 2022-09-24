package net.passerines.finch.itemmanaging;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.recipeitems.BloodIron;
import net.passerines.finch.itemmanaging.recipeitems.MagicBloodIron;
import net.passerines.finch.items.FinchItem;
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
import net.passerines.finch.items.armor.impierceitems.ImpierceAegis;
import net.passerines.finch.items.armor.impierceitems.ImpierceCrown;
import net.passerines.finch.items.armor.impierceitems.ImpierceGaloshes;
import net.passerines.finch.items.armor.impierceitems.ImpierceLeggings;
import net.passerines.finch.items.weapons.mana.*;
import net.passerines.finch.items.weapons.melee.*;
import net.passerines.finch.items.weapons.mana.ManaKatana;
import net.passerines.finch.items.weapons.ranged.*;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
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
        registerRecipe();
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
        new Comet();
        new DevourScythe();
        new CrescentKnives();
        new FireBallWand();
        new SharpStabber();
        new HellfireCrossbow();
        new PooLauncher();
        new Tsunami();
        new StronkBoots();
        new Teleporter();
        new StronkLeggings();
        new IntergalacticDagger();
        new IntergalacticSniper();
        new SniperHelmet();
        new LifeStone();
        new Valkyre();
        new Vescwrith();
        new RitualBlade();
        new ManaKatana();
        new ImpierceCrown();
        new ImpierceAegis();
        new ImpierceGaloshes();
        new ImpierceLeggings();
        new CrescentBlades();
        new Comet();
        new BloodIron();
        new MagicBloodIron();
    }
    public static void registerRecipe(){
        for(FinchItem finchItem : ITEM_HASH_MAP.values()){
            if(finchItem instanceof FinchCraftableItem finchCraftableItem){
                finchCraftableItem.registerRecipe();
            }
        }
        Bukkit.removeRecipe(NamespacedKey.minecraft("iron_helmet"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("iron_chestplate"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("iron_leggings"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("iron_boots"));
    }
}
