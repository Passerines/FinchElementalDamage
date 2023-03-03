package net.passerines.finch;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectManager;
import net.milkbowl.vault.economy.Economy;
import net.passerines.finch.cmds.*;
import net.passerines.finch.enchants.EnchantManager;
import net.passerines.finch.enchants.EnchantShop;
import net.passerines.finch.entity.EntityMap;
import net.passerines.finch.events.HealthDisplay;
import net.passerines.finch.events.NaturalHealthRegen;
import net.passerines.finch.events.handler.*;
import net.passerines.finch.integrations.MythicMobsBridge;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.InteractDetector;
import net.passerines.finch.recipebook.RecipeBookDefault;
import net.passerines.finch.reforge.OpenReforgeMenu;
import net.passerines.finch.reforge.PrefixManager;
import net.passerines.finch.reforge.ReforgeMenu;
import net.passerines.finch.trinkets.OpenTrinketMenu;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Util;
import org.bukkit.entity.Arrow;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.Prefix;

import java.util.logging.Logger;

public class FinchElementalDamage extends JavaPlugin {
    public static Economy ECON = null;


    @Override
    public void onLoad() {
        Util.log("&bFinchElementalDamage is now loading!");
        super.onLoad();

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (!setupEconomy() ) {
            Util.log(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new DamageDisplayer();
        new EntityMap();
        new PlayerMap();
        new GetPlayersCmd();
        new ReforgeCommand();
        new EnchantCommand();
        new EnchantManager();
        new EnchantShop();
        new GetEffectiveHealthCmd();
        new NaturalHealthRegen();
        new HealthDisplay();
        new FinchAttributesCmd();
        new ItemGiveCommand();
        new PrefixManager();
        ItemManager.reload();
        new DamageHandler();
        new EquipmentChangeHandler();
        new MythicMobsBridge();
        new OpenTrinketMenu();
        new OpenReforgeMenu();
        new DebugMessages();
        new ArrowHandler();
        new RecipeBookDefault();
        new InteractDetector();
        new DashHandler();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        ECON = rsp.getProvider();
        return ECON != null;
    }

    public static FinchElementalDamage inst() {
        return FinchElementalDamage.getPlugin(FinchElementalDamage.class);
    }
}
