package net.passerines.finch.itemmanaging;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.aItems.armor.dracoanian.*;
import net.passerines.finch.aItems.armor.vanillareplacements.DiamondBoots;
import net.passerines.finch.aItems.armor.vanillareplacements.DiamondChestplate;
import net.passerines.finch.aItems.armor.vanillareplacements.DiamondHelmet;
import net.passerines.finch.aItems.armor.vanillareplacements.DiamondLeggings;
import net.passerines.finch.aItems.trinkets.TotemOfDefence;
import net.passerines.finch.aItems.trinkets.TotemOfMana;
import net.passerines.finch.aItems.trinkets.TotemOfStrength;
import net.passerines.finch.aItems.weapons.melee.katanas.t4.Ircosis;
import net.passerines.finch.aItems.weapons.melee.katanas.t5.Shriex;
import net.passerines.finch.itemmanaging.recipeitems.*;
import net.passerines.finch.items.FinchItem;
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
import net.passerines.finch.aItems.armor.impierceitems.impiercemage.ImpierceLeggings;
import net.passerines.finch.aItems.armor.impierceitems.impiercemage.ImpierceRobe;
import net.passerines.finch.aItems.armor.impierceitems.impiercemage.ImpierceShoes;
import net.passerines.finch.aItems.armor.impierceitems.impiercemage.ImpierceWisdom;
import net.passerines.finch.aItems.armor.impierceitems.impiercewarrior.ImpierceAegis;
import net.passerines.finch.aItems.armor.impierceitems.impiercewarrior.ImpierceCrown;
import net.passerines.finch.aItems.armor.impierceitems.impiercewarrior.ImpierceGaloshes;
import net.passerines.finch.aItems.armor.impierceitems.impiercewarrior.ImpierceKneeplates;
import net.passerines.finch.aItems.armor.vanillareplacements.reinforcedleather.ReinforcedLeatherBoots;
import net.passerines.finch.aItems.armor.vanillareplacements.reinforcedleather.ReinforcedLeatherCap;
import net.passerines.finch.aItems.armor.vanillareplacements.reinforcedleather.ReinforcedLeatherLeggings;
import net.passerines.finch.aItems.armor.vanillareplacements.reinforcedleather.ReinforcedLeatherTunic;
import net.passerines.finch.items.armor.*;
import net.passerines.finch.items.weapons.mana.*;
import net.passerines.finch.items.weapons.melee.*;
import net.passerines.finch.items.weapons.mana.ManaKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t2.DiamondKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t1.GoldenKatana;
import net.passerines.finch.aItems.weapons.melee.katanas.t1.SteelKatana;
import net.passerines.finch.aItems.weapons.melee.spears.t5.EngulfingLightning;
import net.passerines.finch.aItems.weapons.melee.spears.t4.SoulKnightsSpear;
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
        new IntergalacticRocket();
        new SniperHelmet();
        new LifeStone();
        new Valkyre();
        new Vescwrith();
        new RitualBlade();
        new ManaKatana();
        new ImpierceCrown();
        new ImpierceAegis();
        new ImpierceGaloshes();
        new ImpierceKneeplates();
        new ImpierceLeggings();
        new ImpierceRobe();
        new ImpierceShoes();
        new ImpierceWisdom();
        new ImpierceShoes();
        new CrescentBlades();
        new Comet();
        new BloodIron();
        new MagicBloodIron();
        new ReinforcedLeather();
        new ReinforcedLeatherBoots();
        new ReinforcedLeatherCap();
        new ReinforcedLeatherLeggings();
        new ReinforcedLeatherTunic();
        new SteelKatana();
        new GoldenKatana();
        new DiamondKatana();
        new SoulKnightsSpear();
        new EngulfingLightning();
        new DraconianAegis();
        new DraconianBoots();
        new DraconianCuirass();
        new DraconianHeadgear();
        new DraconianLeggings();
        new ElectroVision();
        new CarbonFiber();
        new Ircosis();
        new TotemOfStrength();
        new TotemOfDefence();
        new TotemOfMana();
        new DragonScale();
        new AngelicEssence();
        new Shriex();
        new DiamondLeggings();
        new DiamondChestplate();
        new DiamondBoots();
        new DiamondHelmet();
        new CondensedDiamond();
        new CondensedEnergy();
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
    }
}
