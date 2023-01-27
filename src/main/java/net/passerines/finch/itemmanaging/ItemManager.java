package net.passerines.finch.itemmanaging;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.aItems.armor.bloodItems.mage.BloodMageBoots;
import net.passerines.finch.aItems.armor.bloodItems.mage.BloodMageChestplate;
import net.passerines.finch.aItems.armor.bloodItems.mage.BloodMageHelmet;
import net.passerines.finch.aItems.armor.bloodItems.mage.BloodMageLeggings;
import net.passerines.finch.aItems.armor.bloodItems.warrior.BloodBoots;
import net.passerines.finch.aItems.armor.bloodItems.warrior.BloodChestplate;
import net.passerines.finch.aItems.armor.bloodItems.warrior.BloodHelmet;
import net.passerines.finch.aItems.armor.bloodItems.warrior.BloodLeggings;
import net.passerines.finch.aItems.armor.carbonfiberItems.CarbonFiberBoots;
import net.passerines.finch.aItems.armor.carbonfiberItems.CarbonFiberChestplate;
import net.passerines.finch.aItems.armor.carbonfiberItems.CarbonFiberHelmet;
import net.passerines.finch.aItems.armor.carbonfiberItems.CarbonFiberLeggings;
import net.passerines.finch.aItems.armor.dracoanian.*;
import net.passerines.finch.aItems.armor.dracoanian.marksman.DraconianMarksmanBoots;
import net.passerines.finch.aItems.armor.dracoanian.marksman.DraconianMarksmanHood;
import net.passerines.finch.aItems.armor.dracoanian.marksman.DraconianMarksmanVest;
import net.passerines.finch.aItems.armor.impierceitems.impiercemage.ImpierceRobe;
import net.passerines.finch.aItems.armor.impierceitems.impiercemage.ImpierceShoes;
import net.passerines.finch.aItems.armor.impierceitems.impiercemage.ImpierceWisdom;
import net.passerines.finch.aItems.armor.impierceitems.impiercewarrior.ImpierceAegis;
import net.passerines.finch.aItems.armor.impierceitems.impiercewarrior.ImpierceCrown;
import net.passerines.finch.aItems.armor.impierceitems.impiercewarrior.ImpierceGaloshes;
import net.passerines.finch.aItems.armor.impierceitems.impiercewarrior.ImpierceKneeplates;
import net.passerines.finch.aItems.insects.MarkXXCombatDrone;
import net.passerines.finch.aItems.insects.Monarch;
import net.passerines.finch.aItems.trinkets.*;
import net.passerines.finch.aItems.utilities.FinchBookOfRecipes;
import net.passerines.finch.aItems.utilities.LifeCrystal;
import net.passerines.finch.aItems.weapons.melee.katanas.t1.GoldenKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t1.LuckyKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t2.DiamondKatana;
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
import net.passerines.finch.aItems.weapons.melee.spears.t4.SoulKnightsSpear;
import net.passerines.finch.aItems.weapons.melee.spears.t5.EngulfingLightning;
import net.passerines.finch.aItems.weapons.melee.spears.t5.Evelon;
import net.passerines.finch.aItems.weapons.ranged.t5.FateCrossbow;
import net.passerines.finch.itemmanaging.recipeitems.*;
import net.passerines.finch.items.FinchItem;
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
        new FateCrossbow();
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
        new SoulKnightsSpear();
        new EngulfingLightning();
        new Evelon();
        //Armor
        new CarbonFiberHelmet();
        new CarbonFiberChestplate();
        new CarbonFiberLeggings();
        new CarbonFiberBoots();
        new BloodHelmet();
        new BloodChestplate();
        new BloodLeggings();
        new BloodBoots();
        new BloodMageHelmet();
        new BloodMageChestplate();
        new BloodMageLeggings();
        new BloodMageBoots();
        new ImpierceCrown();
        new ImpierceAegis();
        new ImpierceKneeplates();
        new ImpierceGaloshes();
        new ImpierceWisdom();
        new ImpierceRobe();
        new ImpierceShoes();
        new DraconianMarksmanHood();
        new DraconianMarksmanVest();
        new DraconianLeggings();
        new DraconianMarksmanBoots();
        new DraconianHeadgear();
        new DraconianCuirass();
        new DraconianAegis();
        new DraconianPlate();
        new DraconianBoots();
        //Trinkets
        new FinchBookOfRecipes();
        new TotemOfStrength();
        new TotemOfDefence();
        new TotemOfMana();
        new TotemOfHealth();
        new Revitalizer();
        new RingOfFate();
        //Insects
        new Monarch();
        new MarkXXCombatDrone();
        //Equipment
        new LifeCrystal();
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
