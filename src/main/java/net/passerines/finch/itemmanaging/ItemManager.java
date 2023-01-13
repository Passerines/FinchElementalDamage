package net.passerines.finch.itemmanaging;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.aItems.insects.Monarch;
import net.passerines.finch.aItems.utilities.FinchBookOfRecipes;
import net.passerines.finch.aItems.weapons.melee.katanas.t1.LuckyKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t2.FortunateKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t3.DestinyKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t3.GlacialKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t4.ProsperityKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t5.KatanaOfFate;
import net.passerines.finch.aItems.weapons.melee.katanas.t5.Shriex;
import net.passerines.finch.aItems.weapons.melee.spears.t1.Spear;
import net.passerines.finch.aItems.weapons.melee.spears.t2.AmythestSpear;
import net.passerines.finch.aItems.weapons.melee.spears.t2.EmeraldSpear;
import net.passerines.finch.aItems.weapons.melee.spears.t3.ElectrifiedSpear;
import net.passerines.finch.aItems.weapons.melee.spears.t3.PoisonedSpear;
import net.passerines.finch.itemmanaging.recipeitems.*;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.aItems.armor.carbonfiberItems.CarbonFiberBoots;
import net.passerines.finch.aItems.armor.carbonfiberItems.CarbonFiberChestplate;
import net.passerines.finch.aItems.armor.carbonfiberItems.CarbonFiberHelmet;
import net.passerines.finch.aItems.armor.carbonfiberItems.CarbonFiberLeggings;
import net.passerines.finch.aItems.weapons.melee.katanas.t2.DiamondKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t1.GoldenKatana;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.LinkedHashMap;

public class ItemManager {
    public static final LinkedHashMap<String, FinchItem> ITEM_HASH_MAP = new LinkedHashMap<>();

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
        //No Recipe Items
        new Shriex();
        //Essence
        new RitualEssence();
        new GoldEssence();
        new LuckEssence();
        new AngelicEssence();
        //Condensed
        new CondensedEnergy();
        new CondensedDiamond();
        //Craftable Ingredients (Regular)
        new KatanaHandle();
        new BloodIron();
        new MagicBloodIron();
        new ReinforcedLeather();
        new CarbonFiber();
        new GlacialGem();
        //Mob Drops
        new MahiMahiScale();
        new DragonSkin();
        new DragonScale();
        new Fate();
        //Katanas
        new GoldenKatana();
        new LuckyKatana();
        new FortunateKatana();
        new DestinyKatana();
        new ProsperityKatana();
        new KatanaOfFate();
        new DiamondKatana() ;
        new GlacialKatana();
        //Spears
        new Spear();
        new AmythestSpear();
        new EmeraldSpear();
        new ElectrifiedSpear();
        new PoisonedSpear();
        //Armor
        new CarbonFiberHelmet();
        new CarbonFiberChestplate();
        new CarbonFiberLeggings();
        new CarbonFiberBoots();
        //Trinkets
        new FinchBookOfRecipes();
        //Insects
        new Monarch();

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
        Bukkit.removeRecipe(NamespacedKey.minecraft("leather_boots"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("leather_chestplate"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("leather_leggings"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("leather_helmet"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("diamond_helmet"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("diamond_chestplate"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("diamond_leggings"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("diamond_boots"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("golden_helmet"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("golden_chestplate"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("golden_leggings"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("golden_boots"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("wooden_sword"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("golden_sword"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("iron_sword"));
        Bukkit.removeRecipe(NamespacedKey.minecraft("diamond_sword"));
    }
}
